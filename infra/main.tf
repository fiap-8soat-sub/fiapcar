############################################
# Provider + Backend (ajuste o bucket se preciso)
############################################
provider "aws" {
    region = var.aws_region
}

terraform {
    backend "s3" {
        bucket  = "terraform-backend-fiapcar-577638369685-us-east-1"
        key     = "state/fiapeats-ms-pedido-db/terraform.tfstate"
        region  = "us-east-1"
        encrypt = true
    }
}

############################################
# Variáveis (defaults práticos)
############################################
variable "aws_region"       { default = "us-east-1" }
variable "cluster_name"     { default = "cluster" }
variable "service_name"     { default = "fiapcar-service" }
variable "task_family"      { default = "fiapcar-task" }
variable "ecr_repo_name"    { default = "fiapcar" } # nome do repositório no ECR
variable "image_tag" {
    description = "Tag da imagem (ex: commit SHA). Defina via TF_VAR_image_tag no pipeline."
    type        = string
}

############################################
# Rede: VPC + Subnets Públicas + IGW + Rota
############################################
resource "aws_vpc" "app" {
    cidr_block           = "10.0.0.0/16"
    enable_dns_support   = true
    enable_dns_hostnames = true
    tags = { Name = "fiapcar-vpc" }
}

resource "aws_internet_gateway" "igw" {
    vpc_id = aws_vpc.app.id
    tags   = { Name = "fiapcar-igw" }
}

resource "aws_subnet" "public_a" {
    vpc_id                  = aws_vpc.app.id
    cidr_block              = "10.0.1.0/24"
    availability_zone       = "us-east-1a"
    map_public_ip_on_launch = true
    tags = { Name = "fiapcar-public-a" }
}

resource "aws_subnet" "public_b" {
    vpc_id                  = aws_vpc.app.id
    cidr_block              = "10.0.2.0/24"
    availability_zone       = "us-east-1b"
    map_public_ip_on_launch = true
    tags = { Name = "fiapcar-public-b" }
}

resource "aws_route_table" "public" {
    vpc_id = aws_vpc.app.id
    route {
        cidr_block = "0.0.0.0/0"
        gateway_id = aws_internet_gateway.igw.id
    }
    tags = { Name = "fiapcar-public-rt" }
}

resource "aws_route_table_association" "a" {
    route_table_id = aws_route_table.public.id
    subnet_id      = aws_subnet.public_a.id
}

resource "aws_route_table_association" "b" {
    route_table_id = aws_route_table.public.id
    subnet_id      = aws_subnet.public_b.id
}

############################################
# Security Group (abre 8086/tcp)
############################################
resource "aws_security_group" "ecs_service" {
    name        = "fiapcar-sg"
    description = "SG para fiapcar no ECS"
    vpc_id      = aws_vpc.app.id

    ingress {
        from_port   = 8086
        to_port     = 8086
        protocol    = "tcp"
        cidr_blocks = ["0.0.0.0/0"]
    }

    # Saída liberada
    egress {
        from_port   = 0
        to_port     = 0
        protocol    = "-1"
        cidr_blocks = ["0.0.0.0/0"]
    }

    tags = { Name = "fiapcar-sg" }
}

############################################
# ECR (pega a URL do repositório pelo nome)
############################################
data "aws_ecr_repository" "app" {
    name = var.ecr_repo_name
}

############################################
# CloudWatch Logs
############################################
resource "aws_cloudwatch_log_group" "ecs_log_group" {
    name              = "/ecs/${var.task_family}"
    retention_in_days = 7
}

############################################
# IAM: Exec Role (puxa imagem, envia logs) + Task Role (app)
############################################
resource "aws_iam_role" "ecs_execution_role" {
    name = "ecsTaskExecutionRole"

    assume_role_policy = jsonencode({
        Version = "2012-10-17",
        Statement = [{
            Effect = "Allow",
            Principal = { Service = "ecs-tasks.amazonaws.com" },
            Action   = "sts:AssumeRole"
        }]
    })
}

resource "aws_iam_role_policy_attachment" "ecs_execution_policy" {
    role       = aws_iam_role.ecs_execution_role.name
    policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy"
}

resource "aws_iam_role" "ecs_task_role" {
    name = "ecsTaskRole"

    assume_role_policy = jsonencode({
        Version = "2012-10-17",
        Statement = [{
            Effect = "Allow",
            Principal = { Service = "ecs-tasks.amazonaws.com" },
            Action   = "sts:AssumeRole"
        }]
    })
}

# (Opcional) Permissões de Cognito pro container (ajuste conforme necessidade real)
resource "aws_iam_policy" "cognito_policy" {
    name        = "CognitoFullPolicy"
    description = "Allows necessary access to Cognito users"
    policy      = jsonencode({
        Version = "2012-10-17",
        Statement = [{
            Action = [
                "cognito-idp:AdminGetUser",
                "cognito-idp:ListUsers",
                "cognito-idp:AdminCreateUser",
                "cognito-idp:AdminSetUserPassword"
            ],
            Effect   = "Allow",
            Resource = "*"
        }]
    })
}

resource "aws_iam_policy_attachment" "attach_cognito_policy_to_task_role" {
    name       = "AttachCognitoPolicy"
    roles      = [aws_iam_role.ecs_task_role.name]
    policy_arn = aws_iam_policy.cognito_policy.arn
}

############################################
# ECS Cluster
############################################
resource "aws_ecs_cluster" "app" {
    name = var.cluster_name

    setting {
        name  = "containerInsights"
        value = "enabled"
    }
}

############################################
# ECS Task Definition
############################################
resource "aws_ecs_task_definition" "app" {
    family                   = var.task_family
    requires_compatibilities = ["FARGATE"]
    network_mode             = "awsvpc"
    cpu                      = "256"
    memory                   = "512"
    execution_role_arn       = aws_iam_role.ecs_execution_role.arn
    task_role_arn            = aws_iam_role.ecs_task_role.arn

    container_definitions = jsonencode([
        {
            name      = "app"
            image     = "${data.aws_ecr_repository.app.repository_url}:${var.image_tag}"
            essential = true
            portMappings = [{
                containerPort = 8086
                hostPort      = 8086
                protocol      = "tcp"
            }]
            logConfiguration = {
                logDriver = "awslogs"
                options = {
                    awslogs-group         = aws_cloudwatch_log_group.ecs_log_group.name
                    awslogs-region        = var.aws_region
                    awslogs-stream-prefix = "ecs"
                }
            }
        }
    ])
}

############################################
# ECS Service (Fargate) nas subnets públicas
############################################
resource "aws_ecs_service" "app_service" {
    name            = var.service_name
    cluster         = aws_ecs_cluster.app.id
    task_definition = aws_ecs_task_definition.app.arn
    desired_count   = 1
    launch_type     = "FARGATE"

    network_configuration {
        subnets          = [aws_subnet.public_a.id, aws_subnet.public_b.id]
        security_groups  = [aws_security_group.ecs_service.id]
        assign_public_ip = true
    }

    depends_on = [
        aws_ecs_task_definition.app
    ]
}

############################################
# Outputs úteis
############################################
output "cluster_arn"        { value = aws_ecs_cluster.app.arn }
output "service_name"       { value = aws_ecs_service.app_service.name }
output "public_subnets"     { value = [aws_subnet.public_a.id, aws_subnet.public_b.id] }
output "security_group_id"  { value = aws_security_group.ecs_service.id }
output "image_uri"          { value = "${data.aws_ecr_repository.app.repository_url}:${var.image_tag}" }

provider "aws" {
    region = var.aws_region
}

terraform {
    backend "s3" {
        bucket         = "terraform-backend-fiapcar"
        key            = "state/fiapeats-ms-pedido-db/terraform.tfstate"
        region         = "us-east-1"
        encrypt        = true
    }
}

resource "aws_ecs_task_definition" "app" {
    family                   = var.task_family
    requires_compatibilities = ["FARGATE"]
    network_mode             = "awsvpc"
    cpu                      = "256"
    memory                   = "512"
    execution_role_arn       = aws_iam_role.ecs_execution_role.arn
    task_role_arn            = aws_iam_role.ecs_task_role.arn

    container_definitions = jsonencode([{
        name      = "app"
        image     = "${var.ecr_repo_url}:${var.image_tag}"
        essential = true
        portMappings = [{
            containerPort = 8086
            hostPort      = 8086
            protocol      = "tcp"
        }]
        logConfiguration = {
            logDriver = "awslogs"
            options = {
                awslogs-group         = "/ecs/${var.task_family}"
                awslogs-region        = var.aws_region
                awslogs-stream-prefix = "ecs"
            }
        }
    }])
}

resource "aws_ecs_service" "app_service" {
    name            = var.service_name
    cluster         = var.cluster_name
    task_definition = aws_ecs_task_definition.app.arn
    desired_count   = 1
    launch_type     = "FARGATE"

    network_configuration {
        subnets          = var.subnet_ids
        security_groups  = var.security_group_ids
        assign_public_ip = true
    }

    depends_on = [aws_ecs_task_definition.app]
}

resource "aws_iam_role" "ecs_execution_role" {
    name = "ecsTaskExecutionRole"

    assume_role_policy = jsonencode({
        Version = "2012-10-17",
        Statement = [{
            Effect = "Allow",
            Principal = {
                Service = "ecs-tasks.amazonaws.com"
            },
            Action = "sts:AssumeRole"
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
            Principal = {
                Service = "ecs-tasks.amazonaws.com"
            },
            Action = "sts:AssumeRole"
        }]
    })
}

resource "aws_iam_policy" "cognito_policy" {
    name        = "CognitoFullPolicy"
    description = "Allows necessary access to Cognito users"
    policy      = jsonencode({
        Version = "2012-10-17",
        Statement = [
            {
                Action = [
                    "cognito-idp:AdminGetUser",
                    "cognito-idp:ListUsers",
                    "cognito-idp:AdminCreateUser",
                    "cognito-idp:AdminSetUserPassword"
                ],
                Effect   = "Allow",
                Resource = "*"
            }
        ]
    })
}

resource "aws_iam_policy_attachment" "attach_cognito_policy_to_task_role" {
    name       = "AttachCognitoPolicy"
    roles      = [aws_iam_role.ecs_task_role.name]
    policy_arn = aws_iam_policy.cognito_policy.arn
}

resource "aws_cloudwatch_log_group" "ecs_log_group" {
    name              = "/ecs/${var.task_family}"
    retention_in_days = 7
}
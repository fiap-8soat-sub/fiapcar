variable "aws_region"       { default = "us-east-1" }
variable "cluster_name"     { default = "cluster" }
variable "service_name"     { default = "fiapcar-service" }
variable "task_family"      { default = "fiapcar-task" }
variable "ecr_repo_name"    { default = "fiapcar" } # nome do repositório no ECR
variable "image_tag" {
  description = "Tag da imagem"
  type        = string
}
variable "aws_region" {}
variable "cluster_name" {}
variable "service_name" {}
variable "task_family" {}
variable "ecr_repo_url" {}
variable "image_tag" {}
variable "subnet_ids" {
  type = list(string)
}
variable "security_group_ids" {
  type = list(string)
}
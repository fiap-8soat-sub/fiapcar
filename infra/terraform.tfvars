aws_region         = "us-east-1"
cluster_name       = "cluster"
service_name       = "fiapcar-service"
task_family        = "fiapcar-task"

ecr_repo_url       = "577638369685.dkr.ecr.us-east-1.amazonaws.com/fiapcar"
#image_tag          = "latest"

subnet_ids         = ["subnet-f00064af", "subnet-9e1c4190"]
security_group_ids = ["sg-4118314d"]
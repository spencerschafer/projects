# Requirements:
- Docker
- Minikube

# To run this program, type the following from the command line:
`cd ~/projects/hello-minikube`

`minikube start --vm-driver hyperkit && eval $(minikube docker-env)`

`docker pull spencerschafer/node`

`kubectl create -f hello-node-deployment.yaml`

`kubectl create -f hello-node-svc.yaml`

`minikube service node-service`

# To remove created files, type the following from the command line:
`minikube stop`

`minikube delete`

# Additional Commands:

Kubernetes GUI
`minikube dashboard`

See current deployments
`kubectl get deployments`

See current services
`kubectl get services`

See information relating to Kubernetes cluster
`kubectl cluster-info`

See which docker images are currently stored
`docker images`

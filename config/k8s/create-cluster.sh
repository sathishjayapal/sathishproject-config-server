#!/bin/sh

echo "Initializing Kubernetes cluster..."

kind create cluster --config kind-cluster-config.yaml

echo "\n-----------------------------------------------------\n"

echo "Installing NGINX Ingress..."

kubectl apply -f https://raw.githubusercontent.com/sathishjayapal/sathishproject-config-server/refs/heads/main/config/k8s/kind-cluster-config.yaml

echo "\n-----------------------------------------------------\n"

echo "Waiting for NGINX Ingress to be ready..."

sleep 10

kubectl wait --namespace ingress-nginx \
  --for=condition=ready pod \
  --selector=app.kubernetes.io/component=controller \
  --timeout=180s

echo "\n"

echo "Happy Sailing!"
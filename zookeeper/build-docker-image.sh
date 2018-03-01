#!/bin/bash

# ============================================================
# Set version tag number
# ============================================================
TAG=$(git rev-parse --short HEAD)

# ============================================================
# Build image
# ============================================================
echo docker build -f Dockerfile --tag foundery.azurecr.io/foundery/zookeeper:${TAG} .
docker build -f Dockerfile --tag foundery.azurecr.io/foundery/zookeeper:${TAG} .

# ============================================================
# Add tags
# Note: $BUILD_NUMBER is a TeamCity environment variable
# ============================================================
#docker tag nexus.foundery.club/foundery/zookeeper:${TAG} nexus.foundery.club/foundery/zookeeper:${PRE}$(if [[ ${BUILD_NUMBER} ]]; then echo ${BUILD_NUMBER}; else echo "local"; fi)
#docker tag nexus.foundery.club/foundery/zookeeper:${TAG} nexus.foundery.club/foundery/zookeeper:${PRE}latest
#
#docker tag nexus.foundery.club/foundery/zookeeper:${TAG} foundery.azurecr.io/foundery/zookeeper:${TAG}
#docker tag nexus.foundery.club/foundery/zookeeper:${TAG} foundery.azurecr.io/foundery/zookeeper:${PRE}$(if [[ ${BUILD_NUMBER} ]]; then echo ${BUILD_NUMBER}; else echo "local"; fi)
docker tag foundery.azurecr.io/foundery/zookeeper:${TAG} foundery.azurecr.io/foundery/zookeeper:latest
#!/bin/bash

# ============================================================
# Set version tag number
# ============================================================
TAG=$(git rev-parse --short HEAD)

# ============================================================
# Build image
# ============================================================
echo docker build -f Dockerfile --tag foundery.azurecr.io/foundery/kafka/broker:${TAG} .
docker build -f Dockerfile --tag foundery.azurecr.io/foundery/kafka/broker:${TAG} .

# ============================================================
# Add tags
# Note: $BUILD_NUMBER is a TeamCity environment variable
# ============================================================
#docker tag nexus.foundery.club/foundery/kafka/broker:${TAG} nexus.foundery.club/foundery/kafka/broker:${PRE}$(if [[ ${BUILD_NUMBER} ]]; then echo ${BUILD_NUMBER}; else echo "local"; fi)
#docker tag nexus.foundery.club/foundery/kafka/broker:${TAG} nexus.foundery.club/foundery/kafka/broker:${PRE}latest
#
#docker tag nexus.foundery.club/foundery/kafka/broker:${TAG} foundery.azurecr.io/foundery/kafka/broker:${TAG}
#docker tag nexus.foundery.club/foundery/kafka/broker:${TAG} foundery.azurecr.io/foundery/kafka/broker:${PRE}$(if [[ ${BUILD_NUMBER} ]]; then echo ${BUILD_NUMBER}; else echo "local"; fi)
docker tag foundery.azurecr.io/foundery/kafka/broker:${TAG} foundery.azurecr.io/foundery/kafka/broker:latest
#!/bin/bash
FROM_IMAGE="jenkins/jenkins"
HOST_DOCKER_GROUP_ID="$(getent group docker | cut -d: -f3)"

docker pull "${FROM_IMAGE}"
printf "\nBuilding with image '%s'\n" "${FROM_IMAGE}"
docker build --build-arg DOCKER_GROUP_ID="$HOST_DOCKER_GROUP_ID" \
  --build-arg FROM_IMAGE="${FROM_IMAGE}" \
  --tag local-jenkins-image:1.0 \
  .
docker image prune -f

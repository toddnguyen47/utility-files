#!/bin/bash
FROM_IMAGE="alpine"

docker pull "${FROM_IMAGE}"
printf "\nBuilding with image '%s'\n" "${FROM_IMAGE}"
docker build \
  --build-arg FROM_IMAGE="${FROM_IMAGE}" \
  --tag baselua:1.0 \
  .
docker image prune -f

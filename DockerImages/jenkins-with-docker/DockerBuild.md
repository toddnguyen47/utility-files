# Build Docker Image

- Ref: https://stackoverflow.com/a/41574919
- Ensure that the docker image's `docker` user has the same Group ID
  as the host's `docker` user's Group ID.
- Make sure to replace `"http://YOUR_PROXY_HERE:PORT_NUMBER"` with the correct proxy address.

```bash
HOST_DOCKER_GROUP_ID="$(getent group docker | cut -d: -f3)"
FROM_IMAGE="jenkins/jenkins"

docker pull "${FROM_IMAGE}"
docker build --build-arg DOCKER_GROUP_ID="$HOST_DOCKER_GROUP_ID" \
  --build-arg FROM_IMAGE="${FROM_IMAGE}" \
  --build-arg http_proxy="http://YOUR_PROXY_HERE:PORT_NUMBER" \
  --build-arg https_proxy="http://YOUR_PROXY_HERE:PORT_NUMBER" \
  --tag local-jenkins-image:1.0 \
  .
```

# Run Built Docker Image

```bash
docker container run --name jenkins-lua --detach --rm \
  --network jenkins-lua-network \
  --env http_proxy='http://YOUR_PROXY_HERE:PORT_NUMBER' \
  --env https_proxy='http://YOUR_PROXY_HERE:PORT_NUMBER' \
  --volume jenkins-docker-certs:/certs/client \
  --volume jenkins-lua-data:/var/jenkins_home \
  --volume "$HOME":/home \
  --volume /var/run/docker.sock:/var/run/docker.sock \
  -p 8080:8080 \
  local-jenkins-image:1.0
```

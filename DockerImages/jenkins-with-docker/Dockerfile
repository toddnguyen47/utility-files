ARG FROM_IMAGE
FROM ${FROM_IMAGE}
ARG DOCKER_GROUP_ID
ARG DOCKER_LINK=https://download.docker.com/linux/static/stable/x86_64/docker-20.10.2.tgz
ARG DOCKER_COMPOSE_VERSION=1.28.2

# Run as root user
USER root
RUN echo ${https_proxy} && echo ${DOCKER_GROUP_ID} && echo ${DOCKER_LINK}
RUN curl -o /root/docker.tgz \
  ${DOCKER_LINK} \
  && tar -C /root -zvxf /root/docker.tgz \
  && mv /root/docker/docker /usr/local/bin/docker \
  && rm -rf /root/docker*
# Install docker_compose. Ref: https://docs.docker.com/compose/install/
RUN \
  curl -L \
  "https://github.com/docker/compose/releases/download/${DOCKER_COMPOSE_VERSION}/docker-compose-$(uname -s)-$(uname -m)" \
  -o /usr/local/bin/docker-compose \
  && chmod +x /usr/local/bin/docker-compose
# Add the HOST docker user's group ID to the CONTAINER docker user's group ID
# Also add the user `jenkins` to the `docker` and `dialout` group
RUN groupadd -g ${DOCKER_GROUP_ID} docker \
  && gpasswd -a jenkins docker \
  && gpasswd -a jenkins dialout
# Run the image as the `jenkins` user
USER jenkins


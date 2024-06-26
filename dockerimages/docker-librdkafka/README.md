# Dockerfile for Librdkafka

This is the Dockerfile to be used to compile librdkafka for AWS Lambda AL2023.

You can change the `--platform` tag to any platform that you would like. Reference: https://docs.docker.com/build/building/multi-platform/

NOTE: You must also copy `.dockerignore` file! This way every time you COPY, the cache can ignore changes to Dockerfile and Dockerfile_Libs.

- Install podman (CLI at the least) at https://podman.io/
- Init and start podman machine.

```shell
podman machine init
podman machine start
```

- Build the "Libs" image first by typing in the below command.

```shell
podman build --platform linux/amd64 --file Dockerfile_Libs -t myal2023:latest
```

- Clone the librdkafka library. Check out the version that you want to compile. For my case, I want to install v2.4.0.

```shell
git clone https://github.com/confluentinc/librdkafka.git
cd librdkafka
git checkout v2.4.0
```

- If you want SSL, https://github.com/confluentinc/librdkafka/wiki/Using-SSL-with-librdkafka
- If you want SASL: https://github.com/confluentinc/librdkafka/wiki/Using-SASL-with-librdkafka

- Copy [Dockerfile](Dockerfile) into the librdkafka folder.
- Run the below command.

```shell
podman build --platform linux/amd64 --file Dockerfile -t mylibrdkafka:latest
# Ref: https://stackoverflow.com/a/51186557/6323360
podman create --platform linux/amd64 --name dummy mylibrdkafka:latest
podman cp dummy:/mylib/mylib.zip .
# Cleanup
podman rm -f dummy
```

- This will create a zipped file of the library called `mylib.zip`. You can upload this as an AWS layer and you can start using it!

---

## Clean Up

Run the below commands.

```shell
podman image prune -f
podman container prune -f
podman machine stop
```

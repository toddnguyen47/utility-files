# Prerequisite Tutorial

Please read the tutorial [Build a Java app with maven](https://jenkins.io/doc/tutorials/build-a-java-app-with-maven/) to understand some of the commands that we will run.

Other useful tutorials:

- [Using docker socket for CI](https://jpetazzo.github.io/2015/09/03/do-not-use-docker-in-docker-for-ci/#the-solution)
- [Make container's docker user's group ID the same as the host's docker user's group ID ](https://stackoverflow.com/a/41574919)

# Docker Setup

## Install Docker

- [Install Docker on Ubuntu using the repository](https://docs.docker.com/install/linux/docker-ce/ubuntu/#install-using-the-repository)
- [Manage Docker as a non-root user](https://docs.docker.com/install/linux/linux-postinstall/#manage-docker-as-a-non-root-user)
- [Configure docker to start on boot](https://docs.docker.com/install/linux/linux-postinstall/#configure-docker-to-start-on-boot)
- [Set up Docker logs json-file rotation](https://docs.docker.com/config/containers/logging/configure/#configure-the-default-logging-driver#configure-the-default-logging-driver)

## Create Docker Networks and Volumes

Run the following commands to setup a network and some persistent volume.

```
$ docker network create jenkins-lua-network
$ docker volume create jenkins-docker-certs
$ docker volume create jenkins-lua-data
```

To `ls` and `inspect` the newly created network and volumes:

```
$ docker network ls
$ docker network inspect jenkins-lua
$ docker volume ls
$ docker volome inspect jenkins-lua-data
```

## Build Docker Images

Please see [DockerSetup/README.md](DockerSetup/README.md)

# Run Jenkins

## Start the Docker Jenkins local image

- Be sure you have already built the `local-jenkins-image:1.0` image
- Start the jenkins container with the following command:

```bash
./startLocalJenkinsContainer.sh
```

- Check if the container started with the following command:

```bash
docker container ls -a | grep jenkins-docker
```

## Ensure that Jenkins is running

Go to http://localhost:8080/ and make sure Jenkins appears.

**FIRST TIME ONLY:** To get the temporary password, use this command:

```
sudo cat /var/lib/docker/volumes/jenkins-lua-data/_data/secrets/initialAdminPassword
```

Then on Jenkins, copy the output to the "Password" field.

- If needed, configure the proxy appropriately.
- In the next step, click on **Install suggested plugins**.
- Create the admin user
- Leave the default host as http://localhost:8080
- You're done!

## Install Blue Ocean

- Go to http://localhost:8080
- On the left, click on **Manage Jenkins**
- Click on **Manage Plugins**
- Click on the tab **Available**
- Search for **Blue Ocean**.
- Check the **Blue Ocean** checkbox.
- On the bottom, click on the button **Install without Restart**
- Wait for the plugin to install
- Click **Go Back to Top Page**
- You're done!

## Create your Pipeline Project in Jenkins

- As a precaution, restart your Lua docker container and refresh http://localhost:8080

```
docker container restart jenkins-docker
```

- Follow Steps 1-7 of [this tutorial](https://jenkins.io/doc/tutorials/build-a-java-app-with-maven/#create-your-pipeline-project-in-jenkins)
- In the **Repository URL** field, specify the Github link to our Github repository, e.g. `https://github.com/GFsignet/ContinuousIntegration`
- In **Credentials**, click the **Add** --> **Jenkins** credential, then enter in your username and password. You can leave every other fields as defaults.
- After you added your credentials, click on the **Credentials** dropdown and select your recently added credential.
- Press **Apply** and **Save**
- On the left-side menu, click on **Open Blue Ocean**
- Run the pipeline!

## Setting a Jenkins build trigger

Ref: https://stackoverflow.com/q/12472645
To set a Jenkins job to run periodically:

- Go to the locally hosted Jenkins site, http://localhost:8080
- Click on your desired job
- On the left menu, click on **Configure**
- Click on the tab **Build Triggers**
- Enable the option **Build periodically**

  - In the **Schedule** text area, Jenkins using [CRON expression](https://en.wikipedia.org/wiki/Cron#CRON_expression) to schedule a job.
  - For example, if you want the job to run every 5 minutes, enter the following into the text area. Note that lines beginning with a `#` are comments.

  ```shell
  # Every 2 minutes
  H/2 * * * *

  # Every hour
  H/60 * * * *

  # Every 4 hours
  H H/4 * * *

  # Run at rand(5:55 - 5:59) PM and rand(11:00 - 11:59) PM from Mon - Fri
  H(55-59) 17,23 * * 1-5

  # On Saturday and Sunday, run once every 6 hours
  H(55-59) H/6 * * 6,0
  ```

## Stop a Docker Container

To stop our docker container named `jenkins-docker`:

```bash
docker stop jenkins-docker
```


## Docker image

We provide a Docker image with all requirements to build and cross-compile IoT-LAB Yocto Linux images. 

### Build Docker image 

You can find at the root of the project a `DockerFile` and build the Docker image with the following command:

```
docker image build \
    --build-arg PUID=$(id -u ${USER}) \
    --build-arg PGID=$(id -g ${USER}) \
    --build-arg USERNAME=$(echo ${USER}) \
    -t yocto \
    .
```

As you can see we use build argument values to run Docker container with current host user. Thus it will be possible to build the Yocto Linux images in the build|build-rpi3 directories at the root of the `iot-lab-yocto` repository on your host filesystem as a Docker volume.

### Run Docker container

Launch this command at the root of the iot-lab-yocto repository.

```
docker run -it --hostname yocto -v $PWD:$HOME yocto
```

When you are logged in the Docker container you can use directly make commands:

```
<login>@yocto:~$ make iotlab-image
<login>@yocto:~$ make build-pkg-<name>
```

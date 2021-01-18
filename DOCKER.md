## Docker image

We provide a Docker image with all requirements to build and cross-compile IoT-LAB Yocto Linux images. 

### Build Yocto images and packages using the Docker container

To build the `iotlab-image` using the Docker container, use the following
command:

```
docker run -it --rm --hostname yocto -u $(id -u):$(id -g) -v $(pwd):/shared fitiotlab/iot-lab-yocto make iotlab-image
```

The `make iotlab-image` is then run inside the container and use the provided
build environment.

Using the `-u $(id -u):$(id -g)` option, the local host user ids are
automatically mapped to the `dev` user inside the container so the files
created by the container have the right ownership.

Other make target can also be used the same way:

- build the `iotlab-image-gateway` image:

```
docker run -it --rm --hostname yocto -u $(id -u):$(id -g) -v $(pwd):/shared fitiotlab/iot-lab-yocto make iotlab-image
```

- build a package (replace `<pkg-name>` at the end):

```
docker run -it --rm --hostname yocto -u $(id -u):$(id -g) -v $(pwd):/shared fitiotlab/iot-lab-yocto make build-pkg-<pkg-name>
```

By default, the target architecture is the var-som-am35 (e.g. the A8 chips). If
you want to build for `rpi3`, add the `-e TARGET=rpi3` option to the Docker command:

```
docker run -it --rm --hostname yocto -u $(id -u):$(id -g) -v $(pwd):/shared -e TARGET=rpi3 fitiotlab/iot-lab-yocto make iotlab-image
```

### Build the Docker image locally

Simply build the Docker image with the following command:

```
docker image build . -t fitiotlab/iot-lab-yocto
```

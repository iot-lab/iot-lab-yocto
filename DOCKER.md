## Docker image

We provide a Docker image with all requirements to build and cross-compile IoT-LAB Yocto Linux images. 

### Build Yocto images and packages using the Docker container

To build the `iotlab-image` using the Docker container, just add
`BUILD_IN_DOCKER=1` to the make command:

```
BUILD_IN_DOCKER=1 make iotlab-image
```

And the `make iotlab-image` is run inside the container.

The local host user ids are automatically mapped to the `dev` user inside the
container so the files created by the container have the right ownership.

Other make target can also be used the same way:

- build the `iotlab-image-gateway` image:

```
BUILD_IN_DOCKER=1 make iotlab-image
```

- build a package (replace `<pkg-name>` at the end):

```
BUILD_IN_DOCKER=1 make build-pkg-<pkg-name>
```

By default, the target architecture is the var-som-am35 (e.g. the A8 chips). If
you want to build for `rpi3`, add `TARGET=rpi3` option to the make command, as
usual:

```
BUILD_IN_DOCKER=1 TARGET=rpi3 make iotlab-image
```

### Build the Docker image locally

Simply build the Docker image with the following command:

```
docker image build . -t fitiotlab/iot-lab-yocto
```

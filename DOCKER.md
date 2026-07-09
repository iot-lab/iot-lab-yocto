## Docker image

We provide a Docker image with all requirements to build and cross-compile IoT-LAB Yocto Linux images. 

### Build Yocto images and packages using the Docker container

To build the `iotlab-image-gateway` image using the Docker container, just
add `BUILD_IN_DOCKER=1` to the make command:

```
BUILD_IN_DOCKER=1 make iotlab-image-gateway
```

And `make iotlab-image-gateway` is run inside the container.

The container is run with your host UID/GID (`-u $(id -u):$(id -g)`), so
files created inside the container keep the right ownership on the host. It
also runs with `--security-opt apparmor=unconfined`, needed for bitbake's
network-sandboxing of `do_unpack`/`do_prepare_recipe_sysroot` to work under
Docker's default AppArmor profile (see the top-level README's Troubleshooting
section for the equivalent host-side fix for native, non-Docker builds).

Other make targets can also be used the same way:

- build a package (replace `<pkg-name>` at the end):

```
BUILD_IN_DOCKER=1 make build-pkg-<pkg-name>
```

- build U-Boot:

```
BUILD_IN_DOCKER=1 make build-uboot
```

- build the kernel:

```
BUILD_IN_DOCKER=1 make build-kernel
```

By default, the target is `rpi5`. Supported values for `TARGET` are `rpi2`,
`rpi3`, `rpi4` and `rpi5` (see the top-level README — the var-som-am35/A8
target has been retired from this branch). To build another target, add
`TARGET=<target>` to the make command, as usual:

```
BUILD_IN_DOCKER=1 TARGET=rpi4 make iotlab-image-gateway
```

### Persisting the download cache across container runs

Each container run only sees the repo checkout (mounted at `/shared`), so
bitbake's download cache normally lives inside `build-<target>/downloads/`
and doesn't survive across separate build directories/targets. To share a
persistent download cache instead, set `BUILD_DOWNLOAD_DIR` to a host
directory; it gets bind-mounted into the container and `DL_DIR` is pointed
at it automatically:

```
BUILD_IN_DOCKER=1 BUILD_DOWNLOAD_DIR=/path/to/shared-downloads make iotlab-image-gateway
```

### Build the Docker image locally

Simply build the Docker image with the following command:

```
docker image build . -t fitiotlab/iot-lab-yocto
```

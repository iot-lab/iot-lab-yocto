Build IoT-LAB images and packages
=================================

[Yocto Project](https://www.yoctoproject.org/) aim to develop embedded Linux images and user-space applications that run on targeted devices like Raspberry Pi gateways (RPI2, RPI3, RPI4, RPI5).

**Legacy hardware note**: the var-som-am35 (IoT-LAB Open A8) target has been retired from this branch. It remains supported on the `dunfell`-based `master` branch, which is kept in maintenance mode.

The process of building images and packages has been wrapped in a Makefile for easier use. This will setup you environment  if it hasn't been set before, update the configuration for your machine and build the images or packages.


System Requirements
-------------------

For Ubuntu and Debian Linux distributions

    $ sudo apt-get install gawk wget git-core diffstat unzip texinfo gcc-multilib \
        build-essential chrpath socat libsdl1.2-dev xterm lz4

For others distributions view the [Yocto official documentation](https://www.yoctoproject.org/documentation).

Build images
---------------

To build images you can run:

    $ make build-all
    # or one by one
    $ make iotlab-image-gateway

By default the Makefile builds the gateway image for RPI5 hardware. If you want to build for another Raspberry Pi model you should specify the target as follows:

    $ make TARGET=rpi4 iotlab-image-gateway

Supported values for `TARGET` are `rpi2`, `rpi3`, `rpi4` and `rpi5`.

Build packages
-----------------

To build package you can run:

    $ make build-pkg-<pkg_name>

Build Linux kernel
-----------------

    $ make build-kernel

Manual process
--------------

    $ git submodule update --init --remote (eg. only the first time)
    $ source ./poky/oe-init-build-env build-rpi4
    $ bitbake -k iotlab-image-gateway-rpi4
    $ bitbake -k <pkg_name>

If you want to build for RPI5 hardware:

    $ source ./poky/oe-init-build-env build-rpi5
    $ bitbake -k iotlab-image-gateway-rpi5


Yocto and OpenEmbedded
----------------------

At the top of the directory you can find a local copy of:

* Yocto project (poky)
* [OpenEmbedded](https://www.openembedded.org/wiki/Main_Page) layers (meta-openembedded)
* Raspberry Pi boards layer (meta-raspberrypi)

The build system uses sub-modules, tracking the `walnascar` branch of each project:

    $ git submodule add -b walnascar https://git.yoctoproject.org/poky
    $ git submodule add -b walnascar https://git.openembedded.org/meta-openembedded
    $ git submodule add -b walnascar https://git.yoctoproject.org/meta-raspberrypi
    $ git commit -m "Add submodules meta-openembedded/poky/meta-raspberrypi"

Use `git submodule status` and/or check file .gitmodules for details.

    $ git submodule status

The sub-modules are set to allow for tracking of upstream updates
on branch walnascar, the last Yocto release still using the classic
combined `poky` repository (later releases split it into separate
`bitbake`/`openembedded-core` clones). You may update the sub-modules
to use the latest upstream commit on that branch using:

    $ git submodule update --remote

Should you need to update either of the sub-modules to a specific version,
simply cd to it, git pull, inspect changes, then git checkout the desired
version or tag.  Then cd .. and git commit the new sub-module(s) setup.
If switching to another branch, update .gitmodules accordingly.

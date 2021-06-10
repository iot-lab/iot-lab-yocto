Build IoT-LAB images and packages
=================================

[Yocto Project](https://www.yoctoproject.org/) aim to develop embedded Linux images and user-space applications that run on targeted devices like IoT-LAB Open A8 and RPI3 hardware.

The process of building images and packages has been wrapped in a Makefile for easier use. This will setup you environment  if it hasn't been set before, update the configuration for your machine and build the images or packages.


System Requirements
-------------------

For Ubuntu and Debian Linux distributions

    $ sudo apt-get install gawk wget git-core diffstat unzip texinfo gcc-multilib \
        build-essential chrpath socat libsdl1.2-dev xterm

For others distributions view the [Yocto official documentation](https://www.yoctoproject.org/documentation).

Build images
---------------

To build images you can run:

    $ make build-all 
    # or one by one
    $ make iotlab-image

By default the Makefile builds an image for IoT-LAB A8 hardware. If you want to build image for RPI3 hardware you should specify the target as follows:

    $ make TARGET=rpi3 iotlab-image

Build packages
-----------------

To build package you can run:

    $ make build-pkg-<pkg_name>

Build Linux kernel/U-boot
-----------------

    $ make build-kernel
    $ make build-uboot

Manual process
--------------
   
    $ git submodule update --init --remote (eg. only the first time)
    $ source ./poky/oe-init-build-env build
    $ bitbake -k iotlab-image-open-a8
    $ bitbake -k <pkg_name>

If you want to build for RPI3 hardware:

    $ source ./poky/oe-init-build-env build-rpi3
    $ bitbake -k iotlab-image-rpi3


Yocto and OpenEmbedded
----------------------

At the top of the directory you can find a local copy of:

* Yocto project (poky)
* [OpenEmbedded](https://www.openembedded.org/wiki/Main_Page) layers (meta-openembedded)
* Raspberry Pi boards layer (meta-raspberrypi)
* Java OpenJDK layer (meta-java)

The build system uses sub-modules and were initialized as follows:

    $ git submodule add -b dunfell git://git.yoctoproject.org/poky
    $ git submodule add -b dunfell git://git.openembedded.org/meta-openembedded
    $ git submodule add -b dunfell git://git.yoctoproject.org/meta-java
    $ git submodule add -b dunfell git://git.yoctoproject.org/meta-raspberrypi
    $ git commit -m "Add submodules meta-openembedded/poky/meta-java/meta-raspberrypi"

Use `git submodule status` and/or check file .gitmodules for details.
   
    $ git submodule status
    -67e48693501bddb80745b9735b7b3d4d28dce9a1 meta-java
    -55c8a76da5dc099a7bc3838495c672140cedb78e meta-openembedded
    -a5f9b07a820d50ae5fb62e07306cd4e72d8638a9 meta-raspberrypi
    -50fdd784231299bedb008f20adeaeeca3eb1452d poky

The sub-modules are set to allow for tracking of upstream updates
on branch dunfell, current TLS release version of Yocto. You may
update the sub-modules to use the latest upstream version using:

    $ git submodule update --remote

Should you need to update either of the sub-modules to a specific version,
simply cd to it, git pull, inspect changes, then git checkout the desired
version or tag.  Then cd .. and git commit the new sub-module(s) setup.
If switching to another branch, update .gitmodules accordingly.

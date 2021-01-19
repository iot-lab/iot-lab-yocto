## IoT-LAB Layer

The OpenEmbedded build system supports organizing Metadata into multiple layers. Layers are basically a group of directories and meta data in configuration and recipes files. Layers allow you to isolate different types of customizations from each other. Folders that represent layers typically have names that begin with the string meta-*. So you can find the custom IoT-LAB layer in the meta-iotlab directory with its configuration and the layer.conf file. (eg. directory meta-iotlab/conf). 

```
# We have a conf and classes directory, add to BBPATH
BBPATH .= ":${LAYERDIR}"

# We have a recipes-* directories, add to BBFILES
BBFILES += "${LAYERDIR}/recipes-*/*/*.bb ${LAYERDIR}/recipes-*/*/*.bbappend"

BBFILE_COLLECTIONS += "iotlab"
BBFILE_PATTERN_iotlab = "^${LAYERDIR}"
BBFILE_PRIORITY_iotlab = "10"

LAYERSERIES_COMPAT_iotlab = "krogoth"
```
You can note for example that the Bitbake recipes for the layer are appended to **BBFILES** variable, the layer name is appended to **BBFILE_COLLECTIONS** and you define layer priority (allows to choose the layer that takes precedence) in **BBFILE_PRIORITY_iotlab**.

If you want that OpenEmbedded build system use your new layer you must enable it. The layer's path is adding to the BBLAYERS variable in the bblayers.conf file of your build directory (eg. build/conf/)

```
# LAYER_CONF_VERSION is increased each time build/conf/bblayers.conf
# changes incompatibly
POKY_BBLAYERS_CONF_VERSION = "2"

BBPATH = "${TOPDIR}"
BBFILES ?= ""

BBLAYERS ?= " \
  ${TOPDIR}/../poky/meta \
  ${TOPDIR}/../poky/meta-poky \
  ${TOPDIR}/../meta-openembedded/meta-oe \
  ${TOPDIR}/../meta-openembedded/meta-networking \
  ${TOPDIR}/../meta-openembedded/meta-python \
  ${TOPDIR}/../meta-java/ \
  ${TOPDIR}/../meta-iotlab/ \
  "
BBLAYERS_NON_REMOVABLE ?= " \
  ${TOPDIR}/../poky/meta \
  ${TOPDIR}/../poky/meta-poky \
```

You can see that we have two build directories, build and build-rpi3, which correspond respectively to the IoT-LAB A8 and RPI3 hardware.

### Bitbake recipes

Recipes used to append Metadata are called BitBake append files (.bbappend or .bb suffix). You can find all recipes of a layer in directories recipes-*. A bitbake recipe file is a set of instructions from creating one, or more, packages for installation on the target device (eg. ipkg package). For example we have written a bitbake append file (eg. ftdi-utils_1.1.0.bb) for IoT-LAB FTDI tools (ftdi-devices-list and ftdi-eeprom-config). 

```
DESCRIPTION = "IOT-LAB ftdi list and configuration"
LICENSE = "CECILL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CECILL-2.0;md5=574109ac4bdff61f9c3e0de892ecbd19"

PACKAGES =+ "${PN}-list ${PN}-config"
S = "${WORKDIR}"
FILESEXTRAPATHS_prepend := "${THISDIR}/src:"

# metapackage
DEPENDS = "libftdi libusb1"

RDEPENDS_${PN} += "${PN}-list ${PN}-config"
ALLOW_EMPTY_${PN} = "1"


DESCRIPTION_${PN}-list = "IOT-LAB ftdi list"
FILES_${PN}-list = "${bindir}/ftdi-devices-list"
RDEPENDS_${PN}-list = "libftdi libusb1"

DESCRIPTION_${PN}-config = "IOT-LAB ftdi eeprom config"
FILES_${PN}-config = "${bindir}/ftdi-eeprom-config"
RDEPENDS_${PN}-config = "libftdi libusb1"

SRC_URI = "git://github.com/iot-lab/iot-lab-ftdi-utils.git;protocol=git;tag=${PV}"
S = "${WORKDIR}/git/"


do_compile () {
  export LDFLAGS="$(pkg-config --libs libftdi1) ${LDFLAGS}"
  export CFLAGS="$(pkg-config --cflags-only-I libftdi1) -DLIBFTDI1=1 ${CFLAGS}"

  oe_runmake
}

do_install () {
  install -d ${D}${bindir}
  install -m 755 ${S}/ftdi-devices-list  ${D}${bindir}/
  install -m 755 ${S}/ftdi-eeprom-config ${D}${bindir}/
}
```

You can view in this file:
* **DEPENDS**: lists a recipe's build-time dependencies  (other recipes files).
* **PACKAGES**: the list of packages the recipe creates ($PN is the recipe name)
* **ALLOW_EMPTY**: is used to control the creation of empty packages (rg. virtual package) which will install other packages
* **SRC_URI**: the list of source files and here IoT-LAB public GitHub repository iot-lab-ftdi-utils
* **do_compile**: provide a compile task which tells Bitbake how to compile. We set CFLAGS and LDFLAGS environment variables and launch oe_runmake used to run make
* **do_install**: provide install task files

The result on A8 node:
```
root@a8-1:~# opkg list | grep ftdi
ftdi-utils - 1.0.0-r0
ftdi-utils-config - 1.0.0-r0
ftdi-utils-list - 1.0.0-r0

root@a8-1:~# opkg files ftdi-utils       
Package ftdi-utils (1.0.0-r0) is installed on root and has the following files:

root@a8-1:~# opkg files ftdi-utils-list
Package ftdi-utils-list (1.0.0-r0) is installed on root and has the following files:
/usr/bin/ftdi-devices-list

root@a8-1:~# opkg files ftdi-utils-config
Package ftdi-utils-config (1.0.0-r0) is installed on root and has the following files:
/usr/bin/ftdi-eeprom-config
```

### Customizing Images

You can customize an image by creating a custom image recipe file that defines additional software as part of the image. The easiest way is to use **IMAGE_INSTALL** variable which specify the **packagegroup** to install into an image. View the iotlab-image-open-a8.bb recipe file for the open A8 image generation. (eg. meta-iotlab/recipes-core/images directory)

```
DESCRIPTION = "IoT-LAB image for Open A8 used by users"
include iotlab-image.inc

IMAGE_INSTALL += "open-a8-packagegroup"
```
This file include a generic iotlab-image.inc file with the minimal IoT-LAB requirements and Linux node package group. Thus you can simply add in the package group recipe file (eg. recipes-core/packagegroups/linux-node-packagegroup.bb) your custom package in RDEPENDS_${PN} variable. 

```
DESCRIPTION = "IoT-LAB image for Open A8 used by users"
include iotlab-image.inc

IMAGE_INSTALL += " \
        linux-node-packagegroup \
        u-boot-fw-utils \
        flash-scripts-open-a8 \
        "
```
 
In the iotlab-image.inc you can view that we inherit of core-image which is the most basic image allowing a device to boot to a Linux. We also use **IMAGE_FEATURES** variable to enable or high-level feature like use openssh server instead of minimal dropbear or activate read_only filesystem and **DISTRO_FEATURES** with ipv6 support. 

```
DESCRIPTION = "IoT-LAB base image, supports target devices"
LICENSE = "GPLv2"
inherit core-image


IMAGE_FEATURES += "          \
        ssh-server-openssh   \
        package-management   \
        tools-sdk            \
        tools-debug          \
        read-only-rootfs     \
        "

DISTRO_FEATURES += "nfs ipv6"

IMAGE_INSTALL +="               \
        lsb                     \
        tzdata                  \
        cronie                  \
```

### Recipes structure


| Recipes directory    | Recipes                                           |
|----------------------|---------------------------------------------------|
| recipes-core         | Images, package groups, sshd keys, initrd scripts |
| recipes-bsp          | U-boot (eg. A8 nodes)                             |
| recipes-kernel       | Linux kernel (eg. A8 nodes)                       |
| recipes-support      | Gateway python app                                |
| recipes-connectivity | OpenSSL/SSH, OML, websocket, MQTT, NTP            |
| recipes-devtools     | OpenOCD, Edbg, Python2/3 dependencies              |




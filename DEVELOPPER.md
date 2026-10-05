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

LAYERSERIES_COMPAT_iotlab = "walnascar"
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
  ${TOPDIR}/../meta-iotlab/ \
  ${TOPDIR}/../meta-raspberrypi/ \
  "
BBLAYERS_NON_REMOVABLE ?= " \
  ${TOPDIR}/../poky/meta \
  ${TOPDIR}/../poky/meta-poky \
  "
```

You can see that we have five build directories, `build-a8` for the
var-som-am35 gateways and Open A8 nodes, and `build-rpi2`, `build-rpi3`,
`build-rpi4` and `build-rpi5`, one per supported Raspberry Pi gateway model.

### Bitbake recipes

Recipes used to append Metadata are called BitBake append files (.bbappend or .bb suffix). You can find all recipes of a layer in directories recipes-*. A bitbake recipe file is a set of instructions from creating one, or more, packages for installation on the target device (eg. ipkg package). For example we have written a bitbake append file (eg. ftdi-utils_1.1.0.bb) for IoT-LAB FTDI tools (ftdi-devices-list and ftdi-eeprom-config). 

```
DESCRIPTION = "IOT-LAB ftdi list and configuration"
LICENSE = "CECILL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CECILL-2.0;md5=574109ac4bdff61f9c3e0de892ecbd19"

PACKAGES =+ "${PN}-list ${PN}-config"
FILESEXTRAPATHS:prepend := "${THISDIR}/src:"

# metapackage
DEPENDS = "libftdi libusb1"

RDEPENDS:${PN} += "${PN}-list ${PN}-config"
ALLOW_EMPTY:${PN} = "1"

inherit pkgconfig

DESCRIPTION:${PN}-list = "IOT-LAB ftdi list"
FILES:${PN}-list = "${bindir}/ftdi-devices-list"
RDEPENDS:${PN}-list = "libftdi libusb1"

DESCRIPTION:${PN}-config = "IOT-LAB ftdi eeprom config"
FILES:${PN}-config = "${bindir}/ftdi-eeprom-config"
RDEPENDS:${PN}-config = "libftdi libusb1"

SRC_URI = "git://github.com/iot-lab/iot-lab-ftdi-utils.git;protocol=https;tag=${PV};nobranch=1"
S = "${UNPACKDIR}/git"

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

Note the `:` override syntax (`RDEPENDS:${PN}`, `FILES:${PN}-list`, ...)
rather than the older underscore form (`RDEPENDS_${PN}`) — this recipe, like
the rest of `meta-iotlab`, was modernized for the `walnascar` release, which
dropped support for the underscore override syntax.

Once built, this produces three packages (`ftdi-utils`, `ftdi-utils-list`,
`ftdi-utils-config`) installable with `opkg` on a gateway, e.g.
`opkg files ftdi-utils-list` would list `/usr/bin/ftdi-devices-list`.

### Customizing Images

You can customize an image by creating a custom image recipe file that defines additional software as part of the image. The easiest way is to use **IMAGE_INSTALL** variable which specify the **packagegroup** to install into an image. View the `iotlab-image-gateway-rpi5.bb` recipe file for the RPi5 gateway image generation (`meta-iotlab/recipes-core/images/` directory) — there is one such recipe per target (`iotlab-image-gateway-rpi2/rpi3/rpi4/rpi5.bb`).

```
DESCRIPTION = "IoT-LAB image for RPI5 gateways"
include iotlab-image.inc

# Include modules in rootfs
IMAGE_INSTALL += " \
    kernel-modules \
    gateway-packagegroup \
    mjpg-streamer \
    sudo \
    rtl-sdr \
    ykush \
    "
```
This file includes the generic `iotlab-image.inc` file with the minimal IoT-LAB requirements, then the gateway package group. Thus you can simply add your custom package to the package group recipe file (`recipes-core/packagegroups/gateway-packagegroup.bb`, or the more minimal `base-packagegroup.bb` it's built on) in its `RDEPENDS:${PN}` variable, or add it directly to a specific target's `IMAGE_INSTALL` as shown above.

In `iotlab-image.inc` you can view that we inherit of core-image which is the most basic image allowing a device to boot to a Linux. We also use **IMAGE_FEATURES** variable to enable or high-level feature like use openssh server instead of minimal dropbear or activate read_only filesystem and **DISTRO_FEATURES** with ipv6/nfs support (further headless-gateway trimming — dropping `opengl`/`wayland`/`vulkan`/`x11` and `ptest` — happens distro-wide in `conf/distro/iotlab.conf`, not in this file).

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
        tzdata                  \
        cronie                  \
        logrotate                \
        ...
```

### Recipes structure

| Recipes directory    | Recipes                                                          |
|-----------------------|------------------------------------------------------------------|
| recipes-core          | Images (`iotlab-image-gateway-*`), packagegroups                 |
| recipes-bsp           | `uboot-netboot` (rpi2-5 network-boot script), `libubootenv`      |
| recipes-kernel        | Currently unused — RPi kernels come straight from meta-raspberrypi, no local override needed |
| recipes-connectivity  | OpenSSH, OML2, NTP                                                |
| recipes-devtools      | OpenOCD, avrdude, edbg, avarice, segger-jlink, jimtcl, pseudo, Python3 dependencies |
| recipes-extended      | sudo, procps                                                      |
| recipes-support       | Gateway python app (`gateway-code`), FTDI tools, LoRa gateway/packet-forwarder, mjpg-streamer, rtl-sdr, ykush, uhubctl, pycom-utils |

See also `meta-iotlab/recipes.txt` for the canonical description of what each
`recipes-*` category is meant to hold.


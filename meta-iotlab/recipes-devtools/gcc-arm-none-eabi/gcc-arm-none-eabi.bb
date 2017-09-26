DESCRIPTION = "GNU ARM Embedded Toolchain"
HOMEPAGE = "https://developer.arm.com/open-source/gnu-toolchain/gnu-rm"
LICENSE = "FreeBSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=670d6aae00a2c7a4da40f70b78bdf992"

PV = "gitr${SRCPV}"
PR = "r4"

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"

SRC_URI = "https://launchpad.net/gcc-arm-embedded/4.8/4.8-2014-q3-update/+download/gcc-arm-none-eabi-4_8-2014q3-20140805-linux.tar.bz2"


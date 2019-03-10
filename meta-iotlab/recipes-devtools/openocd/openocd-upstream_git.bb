DESCRIPTION = "Free and Open On-Chip Debugging, In-System Programming and Boundary-Scan Testing"
HOMEPAGE = "http://openocd.berlios.de/"
SECTION = "utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PR = "r1"

DEPENDS = ""
DEPENDS += "libftdi"
DEPENDS += "libusb1"
DEPENDS += "libjaylink"
DEPENDS += "jimtcl"
DEPENDS += "hidapi"
DEPENDS += "texinfo-native"

## ##################################################
## ##################################################

# Use git version master: issues when using source code
SRCREV = "05e0d633bad9e8b0bdfaf16fc76ab1f9d9419d8b"

SRC_URI = "git://github.com/ntfreak/openocd.git;protocol=https"
SRC_URI += "file://0001-static-library.patch"

S = "${WORKDIR}/git"

## ##################################################
## ##################################################

PACKAGES = "${PN}-dbg ${PN}"

FILES_${PN}-dbg += "/opt/openocd-dev/.debug"
FILES_${PN}     += "/opt/openocd-dev"

# inherit autotools
# Don't use out of tree build
inherit autotools-brokensep pkgconfig

EXTRA_OECONF = ""
EXTRA_OECONF += " --disable-werror "

# samr21
EXTRA_OECONF += " --enable-cmsis-dap --enable-hidapi-libusb "
EXTRA_OECONF += " --enable-maintainer-mode "

# nrf52dk
EXTRA_OECONF += " --enable-jlink "

# disable libjaylink (no submodule)
EXTRA_OECONF += " --disable-internal-libjaylink "

PARAMS_BUILD  = " --enable-largefile --disable-nls --enable-ipv6 --with-sysroot=${STAGING_DIR_TARGET} --with-libtool-sysroot=${STAGING_DIR_TARGET} "
PARAMS_BUILD += " --disable-internal-jimtcl "

PARAMS_CROSS  = " --build=${BUILD_SYS} --host=${HOST_SYS} --target=${TARGET_SYS} "
PARAMS_CROSS += " --libdir=${STAGING_DIR_TARGET}/lib "
PARAMS_INST   = " --prefix=/opt/openocd-dev "

## --enable-ipv6
## --libdir=${STAGING_DIR_TARGET}/lib
##
## export BUILD_SYS=x86_64-linux
## export HOST_SYS=arm-oe-linux-gnueabi
## export TARGET_SYS=arm-oe-linux-gnueabi
## export STAGING_DIR_TARGET=/home/volume6/dev.oe/openembedded-core/build.gw/tmp-eglibc/sysroots/var-som-am35
##
## ./configure --build=x86_64-linux --host=arm-oe-linux-gnueabi --target=arm-oe-linux-gnueabi \
## --libdir=/home/volume6/dev.oe/openembedded-core/build.gw/tmp-eglibc/sysroots/var-som-am35/lib \
## --enable-largefile --disable-nls \
## --with-sysroot=/home/volume6/dev.oe/openembedded-core/build.gw/tmp-eglibc/sysroots/var-som-am35 \
## --with-libtool-sysroot=/home/volume6/dev.oe/openembedded-core/build.gw/tmp-eglibc/sysroots/var-som-am35 \
## --enable-ft2232_libftdi --disable-ftdi2232 --disable-ftd2xx --disable-werror --disable-internal-jimtcl

do_configure() {
    # Build jimtcl in a separate package !!!!!!! got segfault when not doing it
    # So nosubmodule
    ./bootstrap nosubmodule
    ## oe_runconf does *not* work and must not be used here
    ./configure ${PARAMS_INST} ${PARAMS_CROSS} ${PARAMS_BUILD} ${EXTRA_OECONF}
    #oe_runconf ./configure ${PARAMS_CROSS} ${PARAMS_BUILD} ${EXTRA_OECONF}
}

## ##################################################
## ##################################################

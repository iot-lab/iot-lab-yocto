DESCRIPTION = "Free and Open On-Chip Debugging, In-System Programming and Boundary-Scan Testing"
HOMEPAGE = "https://openocd.org/"
SECTION = "utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=599d2d1ee7fc84c0467b3d19801db870"

PR = "r0"

DEPENDS = ""
DEPENDS += "libftdi"
DEPENDS += "libusb1"
DEPENDS += "hidapi" 
DEPENDS += "texinfo-native"
DEPENDS += "autoconf-native" 
DEPENDS += "automake-native" 
DEPENDS += "libtool-native" 
DEPENDS += "pkgconfig-native"

## ##################################################
## ##################################################

SRC_URI = " \
    git://github.com/openocd-org/openocd.git;protocol=https;branch=master;name=openocd \
    git://github.com/msteveb/jimtcl.git;protocol=https;destsuffix=git/jimtcl;name=jimtcl \
    git://repo.or.cz/r/libjaylink.git;protocol=https;destsuffix=git/src/jtag/drivers/libjaylink;name=libjaylink \
    file://0001-static-library.patch \
"

# OpenOCD v0.12.0 release commit
SRCREV_openocd = "9ea7f3d647c8ecf6b0f1424002dfc3f4504a162c"

# Keep these from a known-good OpenOCD recipe lineage until you verify newer ones
SRCREV_jimtcl = "0aa0fb4e3a38d38a49de9eb585d93d63a370dcf6"
SRCREV_libjaylink = "9aa7a5957c07bb6e862fc1a6d3153d109c7407e4"

S = "${WORKDIR}/git"

## ##################################################
## ##################################################

# inherit autotools
# Don't use out of tree build
inherit autotools-brokensep pkgconfig gettext

EXTRA_OECONF = " \
    --prefix=/usr/local \
    --disable-werror \
    --enable-cmsis-dap \
    --enable-hidapi-libusb \
    --enable-maintainer-mode \
    --enable-jlink \
    --enable-stlink \
"

FILES_${PN}-dbg += "/usr/local/bin/.debug"
FILES_${PN} += "/usr/local"

do_configure() {
    ./bootstrap nosubmodule
    oe_runconf
}

## ##################################################
## ##################################################
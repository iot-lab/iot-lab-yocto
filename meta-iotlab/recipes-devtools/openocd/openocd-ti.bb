DESCRIPTION = "TI's fork of OpenOCD Free and Open On-Chip Debugging, In-System Programming and Boundary-Scan Testing"
HOMEPAGE = "git://git.ti.com/sdo-emu/openocd.git"
SECTION = "utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PR = "r1"

DEPENDS = ""
DEPENDS += "libusb1"
DEPENDS += "jimtcl"
DEPENDS += "hidapi"
DEPENDS += "texinfo-native"

## ##################################################
## ##################################################

# Use the master merge from branch develop dated September 21 2018
SRCREV = "4765fd4c864c47d48754350c196bd5c5ce9c5ed9"

SRC_URI = "git://git.ti.com/sdo-emu/openocd.git;protocol=git"

S = "${WORKDIR}/git/openocd"

## ##################################################
## ##################################################

PACKAGES = "${PN}-dbg ${PN}"

FILES_${PN}-dbg += "/opt/openocd-ti/.debug"
FILES_${PN}     += "/opt/openocd-ti"

# inherit autotools
# Don't use out of tree build
inherit autotools-brokensep pkgconfig

EXTRA_OECONF = ""
EXTRA_OECONF += " --disable-werror "

# samr21
EXTRA_OECONF += " --enable-cmsis-dap --enable-hidapi-libusb "
EXTRA_OECONF += " --enable-maintainer-mode "

# disable libjaylink (no submodule)
EXTRA_OECONF += " --disable-internal-libjaylink "

PARAMS_BUILD  = " --enable-largefile --disable-nls --enable-ipv6 --with-sysroot=${STAGING_DIR_TARGET} --with-libtool-sysroot=${STAGING_DIR_TARGET} "
PARAMS_BUILD += " --disable-internal-jimtcl --disable-ftdi "

PARAMS_CROSS  = " --build=${BUILD_SYS} --host=${HOST_SYS} --target=${TARGET_SYS} "
PARAMS_CROSS += " --libdir=${STAGING_DIR_TARGET}/lib "
PARAMS_INST   = " --prefix=/opt/openocd-ti "

do_configure() {
    autoreconf -f -i
    ./configure ${PARAMS_INST} ${PARAMS_CROSS} ${PARAMS_BUILD} ${EXTRA_OECONF}
}

## ##################################################
## ##################################################

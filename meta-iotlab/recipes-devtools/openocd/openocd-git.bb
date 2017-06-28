DESCRIPTION = "Free and Open On-Chip Debugging, In-System Programming and Boundary-Scan Testing"
HOMEPAGE = "http://openocd.berlios.de/"
SECTION = "utils"
LICENSE = "GPLv2"
## LIC_FILES_CHKSUM = "file://COPYING;md5=52aad3ae14f33671f4d848e9579f7870"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PR = "r6"

DEPENDS = "jimtcl-git libftdi"
DEPENDS += "texinfo-native"

## ##################################################
## ##################################################

# validated version,
# please keep version of the jimtcl package in sync.
# v0.6.0 SRCREV="a6cf60c9c2387ca9f6c5a916ebf3d164554a7165"
# v0.6.1 SRCREV="d9c4700b4de322a578fa4812a8492df7b1d2c451"

# SRCREV="d9c4700b4de322a578fa4812a8492df7b1d2c451"
# protocol=git
# tag=v0.6.1

SRCREV = "${AUTOREV}"

PV = "gitr${SRCPV}"

SRC_URI = "git://git.code.sf.net/p/openocd/code;protocol=git \
           file://openocd-link-static.patch"

S = "${WORKDIR}/git"

## ##################################################
## ##################################################

PACKAGES = "${PN}-dbg ${PN}"

FILES_${PN}-dbg += "/usr/local/bin/.debug"
FILES_${PN}     += "/usr/local"

inherit autotools

EXTRA_OECONF = " --enable-ft2232_libftdi --disable-ftdi2232 --disable-ftd2xx --disable-internal-jimtcl --disable-werror "

PARAMS_BUILD = " --enable-largefile --disable-nls --enable-ipv6 --with-libtool-sysroot=${STAGING_DIR_TARGET} "
PARAMS_CROSS = " --build=${BUILD_SYS} --host=${HOST_SYS} --target=${TARGET_SYS} "
PARAMS_INST  = " --prefix=/usr/local "

do_configure() {
	       ./bootstrap nosubmodule
	       echo -e "@set UPDATED 09 April 2012\n@set UPDATED-MONTH April 2012\n@set EDITION 0.6.0\n@set VERSION 0.6.0" > doc/version.texi

	       ## oe_runconf does *not* work and must not be used here
               ## (it includes /usr/include which is a very bad idea ...)
	       ./configure ${PARAMS_CROSS} ${PARAMS_BUILD} ${EXTRA_OECONF}
}

## ##################################################
## ##################################################

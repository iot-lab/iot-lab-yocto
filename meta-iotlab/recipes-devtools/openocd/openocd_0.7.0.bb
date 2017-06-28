DESCRIPTION = "Free and Open On-Chip Debugging, In-System Programming and Boundary-Scan Testing"
HOMEPAGE = "http://openocd.berlios.de/"
SECTION = "utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=52aad3ae14f33671f4d848e9579f7870"

PR = "r6"

DEPENDS = "libftdi libusb libusb1 jimtcl"
DEPENDS += "texinfo-native"

## ##################################################
## ##################################################

SRC_URI = "${SOURCEFORGE_MIRROR}/openocd/${PV}/openocd-${PV}.tar.bz2;name=tarball"
SRC_URI[tarball.md5sum] = "8977a26a4e3a529e1c4fcc0df587a6a4"
SRC_URI[tarball.sha256sum] = "52237b786530c8460b221556c26fa4779f668b7dcb83ff14b8c5eb2050f38e63"

## SRC_URI = "http://ftp.fr.debian.org/debian/pool/main/o/openocd/openocd_0.7.0.orig.tar.gz;name=debtar"
## SRC_URI[debtar.md5sum] = "53ecbb5151df59541b6251335a440766"
## SRC_URI[debtar.sha256sum] = "3d653f594743248f0da3fedb40a03e19637e2ac972f5108eb298b7fd5f53d360"

SRC_URI += "file://openocd-link-static.patch"

S = "${WORKDIR}/openocd-${PV}"

## ##################################################
## ##################################################

PACKAGES = "${PN}-dbg ${PN}"

FILES_${PN}-dbg += "/usr/local/bin/.debug"
FILES_${PN}     += "/usr/local"

inherit autotools 

EXTRA_OECONF = " --enable-ft2232_libftdi --disable-ftdi2232 --disable-ftd2xx --disable-werror "

PARAMS_BUILD=" --enable-largefile --disable-nls --with-sysroot=${STAGING_DIR_TARGET} --with-libtool-sysroot=${STAGING_DIR_TARGET} "
PARAMS_CROSS=" --build=${BUILD_SYS} --host=${HOST_SYS} --target=${TARGET_SYS} "
PARAMS_INST=" --prefix=/usr/local "

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
	## touch jimtcl/configure
 	## oe_runconf does *not* work and must not be used here 
 	./configure ${PARAMS_CROSS} ${PARAMS_BUILD} ${EXTRA_OECONF}
}

## ##################################################
## ##################################################

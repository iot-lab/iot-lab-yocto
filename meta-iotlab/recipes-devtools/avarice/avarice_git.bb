DESCRIPTION = "AVaRICE is a program which interfaces the GNU Debugger GDB with the AVR JTAG ICE available from Atmel."
HOMEPAGE = "http://avarice.sourceforge.net/"
SECTION = "console"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

SRC_URI[md5sum] = "346ec2e46393a54ac152b95abf1d9850"
SRC_URI[sha256sum] = "c6804668dfa96b23185dfe2e8239089af4e4ae0b11aa7435bebb28c3260ede41"

PR = "r1"
# Use git version master
SRC_URI = "git://github.com/multiplemonomials/avarice.git;protocol=https;nobranch=1"
SRCREV = "9a0e122fb0d74ab69d9d5922ca8f6b157ae2aa1d"

S = "${UNPACKDIR}/git"

DEPENDS = "libusb libftdi hidapi"

inherit cmake

do_install:append () {
    rm -rf ${D}/usr/bin/ice-gdb
    rm -rf ${D}/usr/data/gdb-avarice-script
    rm -rf ${D}/usr/data
}

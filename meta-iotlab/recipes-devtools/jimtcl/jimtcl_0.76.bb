DESCRIPTION = "Jim is an opensource small-footprint implementation of the Tcl programming language"
HOMEPAGE = "http://jim.tcl.tk"
LICENSE = "FreeBSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d69300147248518155ea330e78019033"

##
## HIKOB Note
##
## We use a fixed SRCREV in the GIT repo as jimtcl does
## not release tarballs.
## please keep in sync with the openocd package.
##
## SRCREV == 51f65c6d38fbf86e1f0b036ad336761fd2ab7fa0
##

SRCREV="51f65c6d38fbf86e1f0b036ad336761fd2ab7fa0"

PR = "r0"

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev ${PN}-staticdev"

FILES_${PN}-dev += "/usr/include"
FILES_${PN}-dev += "/usr/lib/jim"
FILES_${PN}-dev += "/usr/doc"
FILES_${PN}-staticdev += "/usr/lib/libjim.a"

inherit autotools
DISABLE_STATIC = ""

SRC_URI = "git://github.com/msteveb/jimtcl.git;protocol=git;branch=master"
S = "${WORKDIR}/git"

CONFIGUREOPTS = ""

EXTRA_OECONF = " --prefix=/usr "

do_configure() {
  oe_runconf
}

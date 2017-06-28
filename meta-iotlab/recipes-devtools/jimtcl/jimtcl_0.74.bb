DESCRIPTION = "Jim is an opensource small-footprint implementation of the Tcl programming language"
HOMEPAGE = "http://jim.tcl.tk"
LICENSE = "FreeBSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=670d6aae00a2c7a4da40f70b78bdf992"

##
## HIKOB Note
##
## We use a fixed SRCREV in the GIT repo as jimtcl does
## not release tarballs.
## please keep in sync with the openocd package.
##
## SRCREV == 7c126ab08220024b7dfe293045e6ee4d0bb3ca95
##

SRCREV="7c126ab08220024b7dfe293045e6ee4d0bb3ca95"

## PV = "0.74"
PR = "r6"

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"

FILES_${PN}-dev += "/usr/include"
FILES_${PN}-dev += "/usr/lib"
FILES_${PN}-dev += "/usr/doc"

inherit autotools

SRC_URI = "git://github.com/msteveb/jimtcl.git;protocol=git;branch=master"
S = "${WORKDIR}/git"

CONFIGUREOPTS = ""

EXTRA_OECONF = " --prefix=/usr "

do_configure() {
  oe_runconf
}

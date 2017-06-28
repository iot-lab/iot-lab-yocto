DESCRIPTION = "Jim is an opensource small-footprint implementation of the Tcl programming language"
HOMEPAGE = "http://jim.tcl.tk"
LICENSE = "FreeBSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=670d6aae00a2c7a4da40f70b78bdf992"

## SRCREV = ${AUTOREV}
##
## We use a fixed SRCREV to allow offline bitbake 
## SRCREV == 35eff1766cc2dd2528f70349ab5522247ed0b40c
##     corresponds to git rev on 2013-03-16 22:02
## please keep in sync with the openocd package.
##

SRCREV="35eff1766cc2dd2528f70349ab5522247ed0b40c"

PV = "gitr${SRCPV}"
PR = "r4"

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"

inherit autotools

SRC_URI = "git://github.com/msteveb/jimtcl.git;protocol=git;branch=master"
S = "${WORKDIR}/git"

CONFIGUREOPTS = ""

do_configure() {
 oe_runconf
}

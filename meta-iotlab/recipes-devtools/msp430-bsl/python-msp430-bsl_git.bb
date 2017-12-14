DESCRIPTION = "msp430-bsl script fork for telosB used for tmotesky"
HOMEPAGE = "https://github.com/cetic/python-msp430-tools"
SECTION = "console"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3f35f76815f8f56319273610675d2758"

PR = "r1"

PV = "git-src${SRCDATE}-r${SRCPV}"

SRC_URI = "git://github.com/cetic/python-msp430-tools.git;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit setuptools

DESCRIPTION = "library with cross-python path, ini-parsing, io, code, log facilities"
HOMEPAGE = " http://pylib.org"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a6bb0320b04a0a503f12f69fea479de9"

PR = "r0"

SRCNAME = "py"
SRC_URI = "https://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "5f1708be5482f3ff6711dfd6cafd45e0"
SRC_URI[sha256sum] = "23c99d99ebb2a60eb7023b7577bfc988acb0092082257a57189f100ce84b72f1"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

DEPENDS += " \
        python-pip \
        "

RDEPENDS_${PN} += " \
        python-virtualenv \
        "

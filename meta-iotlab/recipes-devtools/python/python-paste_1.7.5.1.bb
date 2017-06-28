DESCRIPTION = "Tools for using a Web Server Gateway Interface stack"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRCNAME = "Paste"

SRC_URI = "http://pypi.python.org/packages/source/${@SRCNAME[0]}/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "7ea5fabed7dca48eb46dc613c4b6c4ed"
SRC_URI[sha256sum] = "11645842ba8ec986ae8cfbe4c6cacff5c35f0f4527abf4f5581ae8b4ad49c0b6"

S = "${WORKDIR}/${SRCNAME}-${PV}"
inherit setuptools

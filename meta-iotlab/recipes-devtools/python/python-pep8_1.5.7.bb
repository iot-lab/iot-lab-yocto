DESCRIPTION = "Python style guide checker"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRCNAME = "pep8"

SRC_URI = "https://pypi.python.org/packages/source/${@SRCNAME[0]}/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "f6adbdd69365ecca20513c709f9b7c93"
SRC_URI[sha256sum] = "15b42131b25f376165d195fe4e17b0a28311182aaf9330d5eb575bbeda5a6989"

S = "${WORKDIR}/${SRCNAME}-${PV}"
inherit setuptools

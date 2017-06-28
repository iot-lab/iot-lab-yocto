DESCRIPTION = "A Python script for summarizing gcov data."
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD;md5=3775480a712fc46a69647678acb234cb"

SRCNAME = "gcovr"

SRC_URI = "http://pypi.python.org/packages/source/${@SRCNAME[0]}/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "a800afbd4e512e26e5cd01b77adb45b4"
SRC_URI[sha256sum] = "3c6f408817694c99f1395fe0b69118a078b87771ae2a9076d034fa79c21d873e"

S = "${WORKDIR}/${SRCNAME}-${PV}"
inherit setuptools

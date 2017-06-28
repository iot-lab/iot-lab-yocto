DESCRIPTION = "Extends nose.plugins.cover to add Cobertura-style XML reports"
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD;md5=3775480a712fc46a69647678acb234cb"

SRCNAME = "nosexcover"

SRC_URI = "http://pypi.python.org/packages/source/${@SRCNAME[0]}/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "12bf494a801b376debeb6a167c247391"
SRC_URI[sha256sum] = "f5b3a7c936c4f703f15418c1f325775098184b69fa572f868edb8a99f8f144a8"

S = "${WORKDIR}/${SRCNAME}-${PV}"
inherit setuptools

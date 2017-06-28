DESCRIPTION = "Setuptools command for pylint"
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD;md5=3775480a712fc46a69647678acb234cb"

SRCNAME = "setuptools-lint"
RDEPENDS_${PN} = "python-pylint"

SRC_URI = "http://pypi.python.org/packages/source/${@SRCNAME[0]}/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "1a44bb3e64fd173579eb22d4b15e7869"
SRC_URI[sha256sum] = "78e0011ce0bcd52e561ff9842ef445f5d306d83f40709235ef4f5deb47f851c7"

S = "${WORKDIR}/${SRCNAME}-${PV}"
inherit setuptools

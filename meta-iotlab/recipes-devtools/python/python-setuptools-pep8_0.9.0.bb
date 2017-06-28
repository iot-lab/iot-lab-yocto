DESCRIPTION = "Setuptools command for pylint"
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD;md5=3775480a712fc46a69647678acb234cb"

SRCNAME = "setuptools-pep8"
RDEPENDS_${PN} = "python-pep8"

SRC_URI = "http://pypi.python.org/packages/source/${@SRCNAME[0]}/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "8d21d3dbee4e82652b15b1873e789e6c"
SRC_URI[sha256sum] = "f620cd7494015729249bda7260c9b94900d6e6ab27df46051fb144c32627080c"

S = "${WORKDIR}/${SRCNAME}-${PV}"
inherit setuptools

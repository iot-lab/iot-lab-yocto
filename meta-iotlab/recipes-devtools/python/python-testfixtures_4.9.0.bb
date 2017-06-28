DESCRIPTION = "A collection of helpers and mock objects for unit tests and doc tests."
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://docs/license.txt;md5=13685953d426da689b05c0f7556c3453"

SRCNAME = "testfixtures"

SRC_URI = "http://pypi.python.org/packages/source/${@SRCNAME[0]}/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "214b95ec8a7b0a56dc70852662be91d3"
SRC_URI[sha256sum] = "19940361159252b0fff17e714cad26d45c6c9d4a9d67d6d48384b1ab69767e44"

S = "${WORKDIR}/${SRCNAME}-${PV}"
inherit setuptools

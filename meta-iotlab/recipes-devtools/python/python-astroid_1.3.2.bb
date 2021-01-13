DESCRIPTION = "rebuild a new abstract syntax tree from Python's ast"
SECTION = "devel/python"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=2d5025d4aa3495befef8f17206a5b0a1"

SRCNAME = "astroid"

SRC_URI = "https://pypi.python.org/packages/source/${@SRCNAME[0]}/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "2ab96129a977b6eba27765a15d1a9bf2"
SRC_URI[sha256sum] = "9d1f1803958238e93f2c17e1b38cecfa5bcca40c1bbb99aea80fe10c596f45c1"

S = "${WORKDIR}/${SRCNAME}-${PV}"
inherit setuptools

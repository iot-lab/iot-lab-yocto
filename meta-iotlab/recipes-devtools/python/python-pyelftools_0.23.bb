DESCRIPTION = "Library for analyzing ELF files and DWARF debugging information"
SECTION = "devel/python"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8ffb8d54e2d57d6b201d43878fc1ed98"

SRCNAME = "pyelftools"
# python-modules used for simplicity
RDEPENDS_${PN} = 'python-modules'

SRC_URI = "http://pypi.python.org/packages/source/${@SRCNAME[0]}/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "aa7cefa8bd2f63d7b017440c9084f310"
SRC_URI[sha256sum] = "fc57aadd096e8f9b9b03f1a9578f673ee645e1513a5ff0192ef439e77eab21de"

S = "${WORKDIR}/${SRCNAME}-${PV}"
inherit distutils

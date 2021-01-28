DESCRIPTION = "A Python Mocking and Patching Library for Testing"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=de9dfbf780446b18aab11f00baaf5b7e"

SRCNAME = "mock"

SRC_URI = "https://pypi.python.org/packages/source/${@SRCNAME[0]}/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "d834a46d9a129be3e76fdcc99751e82c"
SRC_URI[sha256sum] = "83657d894c90d5681d62155c82bda9c1187827525880eda8ff5df4ec813437c3"

S = "${WORKDIR}/${SRCNAME}-${PV}"
inherit setuptools3

RDEPENDS_${PN} += " \
            ${PYTHON_PN}-prettytable \
            ${PYTHON_PN}-cmd2 \
            ${PYTHON_PN}-pyparsing \
            ${PYTHON_PN}-mccabe \
            ${PYTHON_PN}-pep8 \
            ${PYTHON_PN}-pyflakes"

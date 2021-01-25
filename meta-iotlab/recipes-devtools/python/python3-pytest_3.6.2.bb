## -*-conf-*-
DESCRIPTION = "pytest: simple powerful testing with Python"
HOMEPAGE = "https://pypi.python.org/pypi/pytest/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c39b24965f4aef64222cb35de9d47cc4"

PR = "r0"

SRC_URI[md5sum] = "f773f6fd2e093f59a0939be028e570b3"
SRC_URI[sha256sum] = "8ea01fc4fcc8e1b1e305252b4bc80a1528019ab99fd3b88666c9dc38d754406c"

RDEPENDS_${PN}_class-target += " \
    ${PYTHON_PN}-argparse \
    ${PYTHON_PN}-atomicwrites \
    ${PYTHON_PN}-attrs \
    ${PYTHON_PN}-funcsigs \
    ${PYTHON_PN}-more-itertools \
    ${PYTHON_PN}-pluggy \
    ${PYTHON_PN}-py \
    ${PYTHON_PN}-six \
"

inherit pypi setuptools3

do_install_append() {
    # python2-pytest recipe clash
    rm -f ${D}${bindir}/py.test
    rm -f ${D}${bindir}/pytest
    rmdir ${D}${bindir}
}


## -*-conf-*-
DESCRIPTION = "Hosted coverage reports for Github, Bitbucket and Gitlab"
HOMEPAGE = "https://pypi.python.org/pypi/codecov/"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r0"

PYPI_PACKAGE = "codecov"

SRC_URI[md5sum] = "497aec02bcf38b20b2c7bad8db2e13fe"
SRC_URI[sha256sum] = "8ed8b7c6791010d359baed66f84f061bba5bd41174bf324c31311e8737602788"

RDEPENDS_${PN}_class-target += " \
    ${PYTHON_PN}-requests \
    ${PYTHON_PN}-coverage \
"

inherit pypi setuptools

do_install_append() {
    # python3-codecov recipe clash
    rm -f ${D}${bindir}/codecov
    rmdir ${D}${bindir}
}


DESCRIPTION = "Hosted coverage reports for Github, Bitbucket and Gitlab"
HOMEPAGE = "https://pypi.python.org/pypi/codecov/"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PYPI_PACKAGE = "codecov"

SRC_URI[md5sum] = "8f8c02818963d34b011fd5b669cf900b"
SRC_URI[sha256sum] = "2362b685633caeaf45b9951a9b76ce359cd3581dd515b430c6c3f5dfb4d92a8c"

RDEPENDS_${PN}_class-target += " \
    ${PYTHON_PN}-requests \
    ${PYTHON_PN}-coverage \
"

inherit pypi setuptools3

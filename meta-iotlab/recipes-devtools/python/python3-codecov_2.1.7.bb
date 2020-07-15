DESCRIPTION = "Hosted coverage reports for Github, Bitbucket and Gitlab"
HOMEPAGE = "https://pypi.python.org/pypi/codecov/"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PYPI_PACKAGE = "codecov"

SRC_URI[md5sum] = "ff791c7e7d9cdcff322a8e08e22b1904"
SRC_URI[sha256sum] = "491938ad774ea94a963d5d16354c7299e90422a33a353ba0d38d0943ed1d5091"

RDEPENDS_${PN}_class-target += " \
    ${PYTHON_PN}-requests \
    ${PYTHON_PN}-coverage \
"

inherit pypi setuptools3

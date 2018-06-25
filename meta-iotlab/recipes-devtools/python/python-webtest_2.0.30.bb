## -*-conf-*-
DESCRIPTION = "Helper to test WSGI applications"
HOMEPAGE = "https://pypi.python.org/pypi/WebTest/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PR = "r0"

PYPI_PACKAGE = "WebTest"

SRC_URI[md5sum] = "0dd5a9093922e08e452f60d7d2eae99a"
SRC_URI[sha256sum] = "5c69f73cc58bef355e919ff96054b68cbaecc7d970b60b602568d3d92ca967d5"

RDEPENDS_${PN}_class-target += " \
    ${PYTHON_PN}-webob \
    ${PYTHON_PN}-waitress \
    ${PYTHON_PN}-beautifulsoup4 \
"

inherit pypi setuptools

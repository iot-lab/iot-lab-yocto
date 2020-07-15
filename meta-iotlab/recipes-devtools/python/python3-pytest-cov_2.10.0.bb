## -*-conf-*-
DESCRIPTION = "pytest-cov: Pytest plugin for measuring coverage."
HOMEPAGE = "https://pypi.python.org/pypi/pytest-cov/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cbc4e25353c748c817db2daffe605e43"

SRC_URI[md5sum] = "59f10b3749cfba88df81ecc02b7b4202"
SRC_URI[sha256sum] = "1a629dc9f48e53512fcbfda6b07de490c374b0c83c55ff7a1720b3fccff0ac87"

RDEPENDS_${PN} = "${PYTHON_PN}-pytest"

inherit pypi setuptools3

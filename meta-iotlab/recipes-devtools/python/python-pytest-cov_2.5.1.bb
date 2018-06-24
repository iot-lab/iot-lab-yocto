## -*-conf-*-
DESCRIPTION = "pytest-cov: Pytest plugin for measuring coverage."
HOMEPAGE = "https://pypi.python.org/pypi/pytest-cov/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cbc4e25353c748c817db2daffe605e43"

PR = "r0"
SRC_URI[md5sum] = "5acf38d4909e19819eb5c1754fbfc0ac"
SRC_URI[sha256sum] = "03aa752cf11db41d281ea1d807d954c4eda35cfa1b21d6971966cc041bbf6e2d"

RDEPENDS_${PN} = "python-pytest"

inherit pypi setuptools

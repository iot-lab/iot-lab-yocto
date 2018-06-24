## -*-conf-*-
DESCRIPTION = "py: library with cross-python path, ini-parsing, io, code, log facilities"
HOMEPAGE = "https://pypi.python.org/pypi/py/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a6bb0320b04a0a503f12f69fea479de9"

SRC_URI[md5sum] = "667d37a148ad9fb81266492903f2d880"
SRC_URI[sha256sum] = "29c9fab495d7528e80ba1e343b958684f4ace687327e6f789a94bf3d1915f881"

RDEPENDS_${PN} = "python"

inherit pypi setuptools

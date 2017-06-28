## -*-conf-*-
DESCRIPTION = "py: library with cross-python path, ini-parsing, io, code, log facilities"
HOMEPAGE = "https://pypi.python.org/pypi/py/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a6bb0320b04a0a503f12f69fea479de9"

PR = "r0"
SRCNAME = "py"
SRC_URI = "https://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "5d2c63c56dc3f2115ec35c066ecd582b"
SRC_URI[sha256sum] = "a6501963c725fc2554dabfece8ae9a8fb5e149c0ac0a42fd2b02c5c1c57fc114"

RDEPENDS_${PN} = "python"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

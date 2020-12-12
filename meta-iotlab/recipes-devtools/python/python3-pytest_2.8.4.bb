## -*-conf-*-
DESCRIPTION = "pytest: simple powerful testing with Python"
HOMEPAGE = "https://pypi.python.org/pypi/pytest/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a6bb0320b04a0a503f12f69fea479de9"

PR = "r0"
SRCNAME = "pytest"
SRC_URI = "https://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "0671eab4cd12b7e67c73123360d86b9c"
SRC_URI[sha256sum] = "ca8afa5e216acfba6df7f26f2ef8a45baedf6a952e6b2f033ab55ec7e0a679eb"

RDEPENDS_${PN} = "python3-py"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools3

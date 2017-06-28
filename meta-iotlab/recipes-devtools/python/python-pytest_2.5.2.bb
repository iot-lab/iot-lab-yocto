DESCRIPTION = "pytest: simple powerful testing with Python"
HOMEPAGE = " http://pytest.org"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a6bb0320b04a0a503f12f69fea479de9"

PR = "r0"

SRCNAME = "pytest"
SRC_URI = "http://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "8ea3d1939e81514ccba9ba0e9566b5be"
SRC_URI[sha256sum] = "962452611799878616d6df5e7674cc2c356f902ed005dc4dae9d4e79bb8abda4"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

DEPENDS += " \
        python-pip \
        "

RDEPENDS_${PN} += " \
        "

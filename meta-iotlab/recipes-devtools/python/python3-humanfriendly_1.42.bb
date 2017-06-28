## -*-conf-*-
DESCRIPTION = "Human friendly output for text interfaces using Python"
HOMEPAGE = "https://pypi.python.org/pypi/humanfriendly/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=838993c671c3d6af09496c281ef29ffb"

PR = "r0"
SRCNAME = "humanfriendly"

SRC_URI = "https://pypi.python.org/packages/source/h/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "23250a4d0b871915642d89df137b3c2f"
SRC_URI[sha256sum] = "e64417fd2b7e9b1fa0c2264e03a289734c2c7692030059fc4d3a0440b1e87da3"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools3

DESCRIPTION = "Multi-producer-multi-consumer signal dispatching mechanism"
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://license.txt;md5=09dffabd4d29ee02f66b0b7f808d57c2"

SRCNAME = "PyDispatcher"

SRC_URI = "http://pypi.python.org/packages/source/${@SRCNAME[0]}/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "b99b8f90e6b239282e6c2817d6df18ec"
SRC_URI[sha256sum] = "6fb0b7099de3dcda2dc71c6c261b5abe55d2af3558c16e38926c1a45ec20151f"

S = "${WORKDIR}/${SRCNAME}-${PV}"
inherit setuptools

DESCRIPTION = "collection of low-level Python packages and modules used by Logilab projects"
SECTION = "devel/python"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=2d5025d4aa3495befef8f17206a5b0a1"

SRCNAME = "logilab-common"
RDEPENDS_${PN} = "python-six"

SRC_URI[md5sum] = "e1419b1e73caf63a2c3125fc567ac113"
SRC_URI[sha256sum] = "ed1c60f32c3fa03dc2efaa730e606db1209d14b4769561ff0365aa584a29360a"

S = "${WORKDIR}/${SRCNAME}-${PV}"
inherit pypi setuptools

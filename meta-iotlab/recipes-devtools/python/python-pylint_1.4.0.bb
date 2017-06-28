DESCRIPTION = "python code static checker"
SECTION = "devel/python"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=4325afd396febcb659c36b49533135d4"

SRCNAME = "pylint"
RDEPENDS_${PN} = "python-logilab-common python-astroid python-six"

SRC_URI = "http://pypi.python.org/packages/source/${@SRCNAME[0]}/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "c164738f90213981db5d3297a60b4138"
SRC_URI[sha256sum] = "816646b9d5fd0c33f9e3b009953c46727d22bec3e880712d69b39b1b630fa3ca"

S = "${WORKDIR}/${SRCNAME}-${PV}"
inherit setuptools

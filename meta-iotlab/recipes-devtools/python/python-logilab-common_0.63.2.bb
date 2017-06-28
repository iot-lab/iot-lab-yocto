DESCRIPTION = "collection of low-level Python packages and modules used by Logilab projects"
SECTION = "devel/python"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=2d5025d4aa3495befef8f17206a5b0a1"

SRCNAME = "logilab-common"
RDEPENDS_${PN} = "python-six"

SRC_URI = "http://pypi.python.org/packages/source/${@SRCNAME[0]}/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "2bf4599ae1f2ccf4603ca02c5d7e798e"
SRC_URI[sha256sum] = "603ba00c9bb09219a3bd2906c214d9455481110dacc472f450b53d5ae90f28e7"

S = "${WORKDIR}/${SRCNAME}-${PV}"
inherit setuptools

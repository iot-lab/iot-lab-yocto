## -*-conf-*-
DESCRIPTION = "A module provides basic functions for parsing mime-type names and matching them against a list of media-ranges"
HOMEPAGE = "https://pypi.python.org/pypi/python-mimeparse/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=838c366f69b72c5df05c96dff79b35f2"

PR = "r0"
SRCNAME = "python-mimeparse"
SRC_URI = "https://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "1d2816a16f17dcfe0c613da611fe7e13"
SRC_URI[sha256sum] = "3c69a21e37e77f754e6fc09ebda70acd92c90d8a58f29a41cc0248351378ddc3"

RDEPENDS_${PN} = "python"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools3

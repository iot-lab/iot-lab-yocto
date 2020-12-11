## -*-conf-*-
DESCRIPTION = "Peewee is a simple and small ORM"
HOMEPAGE = "https://pypi.python.org/pypi/peewee/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8a68e3ec7a6e09a43370f0a3fbc48660"

PR = "r0"
SRCNAME = "peewee"

SRC_URI = "https://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "973250df50a081b67d4216b88f694945"
SRC_URI[sha256sum] = "161561ebcdd4e37e7b2220832ad65ff555831e3cd568b3300cb9e8134a2f6d16"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools3

RDEPENDS_${PN} = "python-sqlite3"

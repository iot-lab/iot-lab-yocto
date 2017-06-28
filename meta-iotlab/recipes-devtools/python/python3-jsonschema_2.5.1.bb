## -*-conf-*-
DESCRIPTION = "An implementation of JSON Schema validation for Python"
HOMEPAGE = "https://pypi.python.org/pypi/jsonschema/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=7a60a81c146ec25599a3e1dabb8610a8"

PR = "r0"
SRCNAME = "jsonschema"

SRC_URI = "https://pypi.python.org/packages/source/j/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "374e848fdb69a3ce8b7e778b47c30640"
SRC_URI[sha256sum] = "36673ac378feed3daa5956276a829699056523d7961027911f064b52255ead41"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools3

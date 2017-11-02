DESCRIPTION = "A Python script for summarizing gcov data."
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD;md5=3775480a712fc46a69647678acb234cb"

SRCNAME = "gcovr"

SRC_URI = "https://github.com/${SRCNAME}/${SRCNAME}/archive/${PV}.tar.gz"
SRC_URI[md5sum] = "85af705ca6e1187d2bae967d26810c55"
SRC_URI[sha256sum] = "8a60ba6242d67a58320e9e16630d80448ef6d5284fda5fb3eff927b63c8b04a2"

S = "${WORKDIR}/${SRCNAME}-${PV}"
inherit setuptools

DESCRIPTION = "Code coverage measurement for Python"
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD;md5=3775480a712fc46a69647678acb234cb"

SRCNAME = "coverage"

SRC_URI = "https://pypi.python.org/packages/source/${@SRCNAME[0]}/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "c47b36ceb17eaff3ecfab3bcd347d0df"
SRC_URI[sha256sum] = "d1aea1c4aa61b8366d6a42dd3650622fbf9c634ed24eaf7f379c8b970e5ed44e"

S = "${WORKDIR}/${SRCNAME}-${PV}"
inherit setuptools

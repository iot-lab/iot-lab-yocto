DESCRIPTION = "Extends nose.plugins.cover to add Cobertura-style XML reports"
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD;md5=3775480a712fc46a69647678acb234cb"

PYPI_PACKAGE = "nosexcover"

SRC_URI[md5sum] = "f32ef4824b4484343e9766b2c376365d"
SRC_URI[sha256sum] = "298c3c655da587f6cab8a666e9f4b150320032431062dea91353988d45c8b883"

inherit pypi setuptools3

RDEPENDS_${PN} += " \
        python3-nose \
        "

DESCRIPTION = "A Python script for summarizing gcov data."
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

PYPI_PACKAGE = "gcovr"

SRC_URI[md5sum] = "83f75d78d59cbd8a34275a372a47f557"
SRC_URI[sha256sum] = "5aae34dc81e51600cfecbbbce3c3a80ce3f7548bc0aa1faa4b74ecd18f6fca3f"

inherit pypi setuptools3

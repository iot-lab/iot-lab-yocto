DESCRIPTION = "A Python script for summarizing gcov data."
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD;md5=3775480a712fc46a69647678acb234cb"

PYPI_PACKAGE = "gcovr"

SRC_URI[md5sum] = "83f75d78d59cbd8a34275a372a47f557"
SRC_URI[sha256sum] = "5aae34dc81e51600cfecbbbce3c3a80ce3f7548bc0aa1faa4b74ecd18f6fca3f"

inherit pypi setuptools3

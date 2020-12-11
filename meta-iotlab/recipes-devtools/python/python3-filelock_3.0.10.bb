DESCRIPTION = "A platform independent file lock."
HOMEPAGE = "https://github.com/benediktschmitt/py-filelock"
SECTION = "devel/python"
LICENSE = "Unlicense"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=782ad7bbca1c0963a9aaf6bf97202d9b"

PYPI_PACKAGE = "filelock"

SRC_URI[sha256sum] = "d610c1bb404daf85976d7a82eb2ada120f04671007266b708606565dd03b5be6"

inherit pypi setuptools3


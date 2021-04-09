DESCRIPTION = "Low-level components of distutils2/packaging"
HOMEPAGE = "https://pypi.org/project/distlib/"
SECTION = "packaging/python"
LICENSE = "Python-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f6a11430d5cd6e2cd3832ee94f22ddfc"

SRC_URI[md5sum] = "4baf787d8aceb260d6f77cb31bf27cf6"
SRC_URI[sha256sum] = "edf6116872c863e1aa9d5bb7cb5e05a022c519a4594dc703843343a9ddd9bff1"

PYPI_PACKAGE = "distlib"
PYPI_PACKAGE_EXT = "zip"

inherit pypi setuptools3

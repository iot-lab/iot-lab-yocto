DESCRIPTION = "Virtual Python Environment builder"
HOMEPAGE = "https://pypi.python.org/pypi/virtualenv"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0ce089158cf60a8ab6abb452b6405538"

DEPENDS += "${PYTHON_PN}-setuptools-scm-git-archive-native"

SRC_URI[md5sum] = "c4d8d3bfc3cac8ab091795473b601e50"
SRC_URI[sha256sum] = "49ec4eb4c224c6f7dd81bb6d0a28a09ecae5894f4e593c89b0db0885f565a107"

PYPI_PACKAGE = "virtualenv"

inherit pypi setuptools3

RDEPENDS_${PN}_class-target += " \
        python3-distlib \
        python3-appdirs \
        "

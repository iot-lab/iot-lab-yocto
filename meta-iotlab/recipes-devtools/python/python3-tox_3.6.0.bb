DESCRIPTION = "virtualenv-based automation of test activities"
HOMEPAGE = "http://tox.testrun.org"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f24505dfbdd8c22c61de6383f2e74bf9"

PYPI_PACKAGE = "tox"

SRC_URI[sha256sum] = "304177defdcb403d84aeb0400b1625b1e65a7fff19f0441329f9f76ebf67882f"

inherit pypi setuptools3

RDEPENDS_${PN}_class-target += " \
        python3-virtualenv \
        python3-py \
        python3-filelock \
        python3-toml \
        python3-pluggy \
        "

DEPENDS += "${PYTHON_PN}-setuptools-scm-git-archive"


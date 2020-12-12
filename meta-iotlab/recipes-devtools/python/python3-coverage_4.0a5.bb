# Copyright (C) 2014 Wind River Systems, Inc.
#
DESCRIPTION = "Code coverage measurement for Python"
HOMEPAGE = "https://pypi.python.org/pypi/coverage"
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD;md5=3775480a712fc46a69647678acb234cb"

SRC_URI[md5sum] = "37f4d8ae679b2c43a87da8d519817a3b"
SRC_URI[sha256sum] = "07a36ac3c82f5961c45565fd5575b4c568a8fc59e8d5d29a5b04dc8597a07e0e"

inherit pypi setuptools3

DEPENDS += " \
        ${PYTHON_PN}-pip \
        "

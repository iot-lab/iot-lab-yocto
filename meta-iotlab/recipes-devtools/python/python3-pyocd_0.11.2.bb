DESCRIPTION = "pyOCD, an Open Source python based library for programming and debugging ARM Cortex-M microcontrollers using CMSIS-DAP. " 
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=19cbd64715b51267a47bf3750cc6a8a5"
HOMEPAGE = "https://github.com/mbedmicro/pyOCD"
SUMMARY = "CMSIS-DAP debugger for Python"

DEPENDS += "${PYTHON_PN}-setuptools-scm-git-archive-native"

SRC_URI[md5sum] = "5d3d3084e8cf4c12871b81491d6c0819"
SRC_URI[sha256sum] = "376c04e65321ec36162427b5d2fa47529b45d3a61d0db4fd49a895017676b473"

PYPI_PACKAGE = "pyOCD"

inherit pypi setuptools3

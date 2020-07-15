DESCRIPTION = "pyOCD, an Open Source python based library for programming and debugging ARM Cortex-M microcontrollers using CMSIS-DAP. " 
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=421492e27872cb498685e9d7649f63a2"
HOMEPAGE = "https://github.com/mbedmicro/pyOCD"
SUMMARY = "CMSIS-DAP debugger for Python"

DEPENDS += "${PYTHON_PN}-setuptools-scm-git-archive-native"

SRC_URI[md5sum] = "ed01d209bb06e908b8868ccf91b091a1"
SRC_URI[sha256sum] = "a5fd9d3faa64397adc6426f8ddb771ab4cb31e7fcaacff9a352ea70615ef3e97"

PYPI_PACKAGE = "pyocd"

inherit pypi setuptools3

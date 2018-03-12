DESCRIPTION = "pyOCD, an Open Source python 2.7 based library for programming and debugging ARM Cortex-M microcontrollers using CMSIS-DAP. "
HOMEPAGE = "https://github.com/mbedmicro/pyOCD"
SECTION = "utils"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=715f3348ed8b9bf4fac3b08133384a4d"

SRC_URI[md5sum] = "3a7b1197fcc950d86a6d05ea83145764"
SRC_URI[sha256sum] = "b89f41b1d6c1f81777220d0dfdaf42f73064709df9842cf81027812b4d7a1951"

PV = '0.8.0'

SRCNAME = "pyOCD"
SRC_URI = "https://pypi.python.org/packages/35/c6/9fee7264155206498796066e924f6edef475b1b683f727e320219b1c85e2/pyOCD-0.8.0.tar.gz"

S = "${WORKDIR}/${SRCNAME}-${PV}"
inherit setuptools
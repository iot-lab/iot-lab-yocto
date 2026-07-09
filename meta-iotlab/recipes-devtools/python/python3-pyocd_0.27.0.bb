DESCRIPTION = "pyOCD, an Open Source python based library for programming and debugging ARM Cortex-M microcontrollers using CMSIS-DAP. " 
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=421492e27872cb498685e9d7649f63a2"
HOMEPAGE = "https://github.com/mbedmicro/pyOCD"
SUMMARY = "CMSIS-DAP debugger for Python"

SRC_URI[md5sum] = "ed01d209bb06e908b8868ccf91b091a1"
SRC_URI[sha256sum] = "a5fd9d3faa64397adc6426f8ddb771ab4cb31e7fcaacff9a352ea70615ef3e97"

PYPI_PACKAGE = "pyocd"

# Pre-satisfy setup.py's setup_requires=['setuptools_scm', ...] so setuptools
# doesn't try (and fail, no network in do_compile) to pip-fetch it itself.
DEPENDS += "python3-setuptools-scm-native"

inherit pypi setuptools3

do_configure:prepend() {
    # setuptools_scm_git_archive is a dead PyPI package (its functionality
    # was folded into setuptools_scm itself); leaving it in setup_requires
    # makes setuptools try and fail to pip-fetch it at build time.
    sed -i "/setuptools_scm_git_archive/d" ${S}/setup.py
}

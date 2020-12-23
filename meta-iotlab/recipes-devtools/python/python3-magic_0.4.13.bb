SUMMARY = "Python library that uses ctypes to access the libmagic file type identification library."
HOMEPAGE = "http://github.com/ahupp/python-magic"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit pypi setuptools3

SRC_URI[md5sum] = "bbf309c8eeb414ed9957aa3a5e43a869"
SRC_URI[sha256sum] = "604eace6f665809bebbb07070508dfa8cabb2d7cb05be9a56706c60f864f1289"

PYPI_PACKAGE = "python-magic"

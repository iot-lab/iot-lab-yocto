SUMMARY = "Python library that uses ctypes to access the libmagic file type identification library."
HOMEPAGE = "http://github.com/ahupp/python-magic"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit setuptools

SRC_URI = "https://github.com/ahupp/${BPN}/archive/${PV}.tar.gz"
SRC_URI[md5sum] = "aeec179b1c268ba686ec63bf9d888d40"

## -*-conf-*-
DESCRIPTION = "Fast and simple WSGI-framework for small web-applications"
HOMEPAGE = "https://pypi.python.org/pypi/bottle/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.rst;md5=1f9017c97709920b7382f39fb402466f"

PR = "r0"
SRCNAME = "bottle"

SRC_URI = "https://pypi.python.org/packages/source/b/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "f5850258a86224a791171e8ecbb66d99"
SRC_URI[sha256sum] = "fe0a24b59385596d02df7ae7845fe7d7135eea73799d03348aeb9f3771500051"

RDEPENDS_${PN} = "python python-wsgiref"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

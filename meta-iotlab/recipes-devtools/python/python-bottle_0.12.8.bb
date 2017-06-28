DESCRIPTION = "Fast and simple WSGI-framework for small web-applications."
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRCNAME = "bottle"
# many more required... , python-modules can be used for simplicity
RDEPENDS_${PN} = 'python-netclient python-netserver python-email'

SRC_URI = "http://pypi.python.org/packages/source/${@SRCNAME[0]}/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "13132c0a8f607bf860810a6ee9064c5b"
SRC_URI[sha256sum] = "cd5d1755a85e533205ed8a2465badf38602227c5f171c3852e07724b28c050ac"

S = "${WORKDIR}/${SRCNAME}-${PV}"
inherit distutils

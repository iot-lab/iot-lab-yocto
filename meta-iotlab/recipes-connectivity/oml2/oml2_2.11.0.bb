DESCRIPTION = "oml2 OML is a measurement library that allows to define measurement points inside applications"

LICENSE = "MIT"
LIC_FILES_CHKSUM ?= "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PR = "r2"

DEPENDS += "ruby-native popt libxml2 sqlite3 python3"
RDEPENDS:${PN} += "libxml2 libtool sqlite3"
# oml2-scaffold needed for gateway-code
RDEPENDS:${PN} += "ruby"

SRC_URI += "file://oml2-2.11.0.tar.gz"
SRC_URI += "file://0001-Use-python3-binary.patch"

S = "${UNPACKDIR}/${PN}-${PV}"

inherit autotools pkgconfig

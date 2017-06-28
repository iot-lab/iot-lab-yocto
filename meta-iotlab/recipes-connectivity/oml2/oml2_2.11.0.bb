DESCRIPTION = "oml2 OML is a measurement library that allows to define measurement points inside applications"

LICENSE = "MIT"
LIC_FILES_CHKSUM ?= "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PR = "r2"

DEPENDS += "ruby-native popt"
RDEPENDS_${PN} += "libxml2 libtool"
# oml2-scaffold needed for gateway-code
RDEPENDS_${PN} += "ruby"

SRC_URI += "file://oml2-2.11.0.tar.gz"

S = "${WORKDIR}/${PN}-${PV}"

inherit autotools

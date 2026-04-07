DESCRIPTION = "oml2 OML is a measurement library that allows to define measurement points inside applications"

LICENSE = "MIT"
LIC_FILES_CHKSUM ?= "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

require oml2_${PV}.bb
inherit native

DEPENDS += "libxml2-native pkgconfig-native gnulib-native python3-native popt-native"

SRC_URI += "file://0001-configure-use-pkg-config.patch"

S = "${WORKDIR}/oml2-${PV}"

# do autogen to apply configure.ac patch
do_configure_prepend() {
    cd ${S}
    ./autogen.sh
    cd ${B}
}

do_install:append:class-native() {
    if [ -f ${D}${bindir}/oml2-scaffold ]; then
        sed -i '1s|^#!/usr/bin/ruby$|#!/usr/bin/env ruby|' ${D}${bindir}/oml2-scaffold
    fi
}
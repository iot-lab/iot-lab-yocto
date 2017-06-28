DESCRIPTION = "IOT-LAB ftdi list and configuration"
LICENSE = "CECILL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CECILL-2.0;md5=574109ac4bdff61f9c3e0de892ecbd19"

PACKAGES =+ "${PN}-list ${PN}-config"
S = "${WORKDIR}"
FILESEXTRAPATHS_prepend := "${THISDIR}/src:"

# metapackage
DEPENDS = "libftdi libusb1"

RDEPENDS_${PN} += "${PN}-list ${PN}-config"
ALLOW_EMPTY_${PN} = "1"


DESCRIPTION_${PN}-list = "IOT-LAB ftdi list"
FILES_${PN}-list = "${bindir}/ftdi-devices-list"
RDEPENDS_${PN}-list = "libftdi libusb1"

DESCRIPTION_${PN}-config = "IOT-LAB ftdi eeprom config"
FILES_${PN}-config = "${bindir}/ftdi-eeprom-config"
RDEPENDS_${PN}-config = "libftdi libusb1"

SRC_URI = "git://github.com/iot-lab/iot-lab-ftdi-utils.git;protocol=git;tag=${PV}"
S = "${WORKDIR}/git/"


do_compile () {
  export LDFLAGS="$(pkg-config --libs libftdi1) ${LDFLAGS}"
  export CFLAGS="$(pkg-config --cflags-only-I libftdi1) -DLIBFTDI1=1 ${CFLAGS}"

  oe_runmake
}

do_install () {
  install -d ${D}${bindir}
  install -m 755 ${S}/ftdi-devices-list  ${D}${bindir}/
  install -m 755 ${S}/ftdi-eeprom-config ${D}${bindir}/
}

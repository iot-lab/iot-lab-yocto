DESCRIPTION = "Add extra udev rules"
LICENSE = "CECILL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CECILL-2.0;md5=574109ac4bdff61f9c3e0de892ecbd19"

SRC_URI += "file://ftdi.rules"

S = "${WORKDIR}"

do_install () {
  install -d                                     ${D}${sysconfdir}/udev/rules.d
  install -m 0644 ${S}/ftdi.rules                ${D}${sysconfdir}/udev/rules.d/
}

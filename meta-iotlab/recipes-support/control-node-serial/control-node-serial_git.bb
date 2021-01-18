DESCRIPTION = "Control Node Serial Interface"
LICENSE = "CECILL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CECILL-2.0;md5=574109ac4bdff61f9c3e0de892ecbd19"

DEPENDS = "oml2 oml2-native"

SRC_URI  = "git://github.com/iot-lab/control_node_serial.git;protocol=https"

PR = "r4"
PV = "git-src${SRCDATE}-r${SRCPV}"

S = "${WORKDIR}/git/"
SRCREV = "${AUTOREV}"

do_install() {
	install -d ${D}${bindir}
	install -m 755 ${S}/control_node_serial_interface ${D}${bindir}
}

DESCRIPTION = "IoT-LAB openocd scripts"
LICENSE = "CECILL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CECILL-2.0;md5=574109ac4bdff61f9c3e0de892ecbd19"

PACKAGES =+ "${PN}-open-a8 ${PN}-gateway"
S = "${WORKDIR}"

PR = "r1"

DESCRIPTION_${PN}-open-a8 = "IoT-LAB openocd scripts for a8 board"
RDEPENDS_${PN}-open-a8    = "openocd"
RDEPENDS_${PN}-open-a8    += "bash"
FILES_${PN}-open-a8  = "${bindir}/flash_a8_m3"
FILES_${PN}-open-a8 += "${bindir}/reset_a8_m3"
FILES_${PN}-open-a8 += "${bindir}/debug_a8_m3"
FILES_${PN}-open-a8 += "${sysconfdir}/iotlab-flash-scripts/iot-lab-a8-m3.cfg"

DESCRIPTION_${PN}-gateway = "IoT-LAB openocd scripts for gateway board"
RDEPENDS_${PN}-gateway    = "openocd bash"
FILES_${PN}-gateway  = "${bindir}/flash_m3"
FILES_${PN}-gateway += "${bindir}/reset_m3"
FILES_${PN}-gateway += "${bindir}/debug_m3"
FILES_${PN}-gateway += "${sysconfdir}/iotlab-flash-scripts/iot-lab-m3.cfg"
FILES_${PN}-gateway += "${bindir}/flash_cn"
FILES_${PN}-gateway += "${bindir}/reset_cn"
FILES_${PN}-gateway += "${bindir}/debug_cn"
FILES_${PN}-gateway += "${sysconfdir}/iotlab-flash-scripts/iot-lab-cn.cfg"

SRC_URI = "\
        \
        file://iot-lab-a8-m3.cfg  \
        file://flash_a8_m3 \
        file://reset_a8_m3 \
        file://debug_a8_m3 \
        \
        file://iot-lab-cn.cfg \
        file://flash_cn    \
        file://reset_cn    \
        file://debug_cn    \
        \
        file://iot-lab-m3.cfg  \
        file://flash_m3    \
        file://reset_m3    \
        file://debug_m3    \
        "

do_install () {
  install -d ${D}${bindir}
  install -m 755 ${S}/flash_a8_m3  ${D}${bindir}/
  install -m 755 ${S}/reset_a8_m3  ${D}${bindir}/
  install -m 755 ${S}/debug_a8_m3  ${D}${bindir}/

  install -m 755 ${S}/flash_cn     ${D}${bindir}/
  install -m 755 ${S}/reset_cn     ${D}${bindir}/
  install -m 755 ${S}/debug_cn     ${D}${bindir}/

  install -m 755 ${S}/flash_m3     ${D}${bindir}/
  install -m 755 ${S}/reset_m3     ${D}${bindir}/
  install -m 755 ${S}/debug_m3     ${D}${bindir}/

  install -d ${D}${sysconfdir}/iotlab-flash-scripts/
  install -m 644 ${S}/iot-lab-a8-m3.cfg  ${D}${sysconfdir}/iotlab-flash-scripts/
  install -m 644 ${S}/iot-lab-cn.cfg     ${D}${sysconfdir}/iotlab-flash-scripts/
  install -m 644 ${S}/iot-lab-m3.cfg     ${D}${sysconfdir}/iotlab-flash-scripts/
}

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://ptpd.conf"
SRC_URI += "file://ptpd.default"
SRC_URI += "file://ptpd.init"

#inherit update-rc.d
#INITSCRIPT_PACKAGES = "${PN}"

do_install_append() {
  install -d ${D}${sysconfdir}/init.d
  install -m 0755 ${WORKDIR}/ptpd.init ${D}${sysconfdir}/init.d/ptpd
  install -d ${D}${sysconfdir}/default
  install -m 0644 ${WORKDIR}/ptpd.default ${D}${sysconfdir}/default/ptpd
  install -m 0644 ${WORKDIR}/ptpd.conf ${D}${sysconfdir}/ptpd.conf
}

FILES_${PN} += "${sysconfdir}/ptpd.conf"
FILES_${PN} += "${sysconfdir}/init.d/ptpd"
FILES_${PN} += "${sysconfdir}/default/ptpd"

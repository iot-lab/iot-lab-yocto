FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://sysctl.conf"

do_install_append() {
        install -d ${D}${sysconfdir}
        install -m 0644 ${WORKDIR}/sysctl.conf ${D}${sysconfdir}/sysctl.conf
}

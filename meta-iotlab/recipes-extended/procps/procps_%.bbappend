FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://sysctl.conf"

do_install:append() {
        install -d ${D}${sysconfdir}
        install -m 0644 ${UNPACKDIR}/sysctl.conf ${D}${sysconfdir}/sysctl.conf
}

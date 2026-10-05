FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# U-Boot environment location in the var-som-am35 NAND
SRC_URI:append:var-som-am35 = " file://fw_env.config"

do_install:append:var-som-am35() {
	install -d ${D}${sysconfdir}
	install -m 644 ${UNPACKDIR}/fw_env.config ${D}${sysconfdir}
}

FILES:${PN}:append:var-som-am35 = " ${sysconfdir}"

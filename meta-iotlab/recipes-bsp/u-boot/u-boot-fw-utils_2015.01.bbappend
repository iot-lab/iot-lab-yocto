## -*-conf-*-

SRCREV_FORMAT = "uboot_iotlab"

#do_install_append_iotlab-a8 () {
do_install_append () {
	install -d ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}
}

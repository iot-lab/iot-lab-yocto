do_install:append () {
    sed -i -e 's:#UseDNS yes:UseDNS no:' ${D}${sysconfdir}/ssh/sshd_config_readonly ${D}${sysconfdir}/ssh/sshd_config
}

PR_append = "-v0"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://etc_hosts"

# base-files should not create 'hostname' file
# it will be created by iotlab-base-config
hostname = ""

# Make '/etc/hosts' volatile

do_install_append() {
    rm ${D}${sysconfdir}/hosts

    install -d                           ${D}${sysconfdir}/default/volatiles
    install -m 0644 ${WORKDIR}/etc_hosts ${D}${sysconfdir}/default/volatiles/10_etc_hosts
}

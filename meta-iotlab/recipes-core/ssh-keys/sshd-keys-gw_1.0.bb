DESCRIPTION = "Generation of dummy sshd server keys"
LICENSE = "CECILL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CECILL-2.0;md5=574109ac4bdff61f9c3e0de892ecbd19"

SRC_URI += "file://ssh_host_rsa_key"
SRC_URI += "file://ssh_host_rsa_key.pub"

S = "${WORKDIR}"

# we copy a dummy server key because in poky/meta/classes/rootfs-postcommands.bbclass
# the read_only_rootfs_hook test if this file exist and otherwise use /etc/ssh/sshd_config_readonly
# and the keys under /var/run/ssh. 

# Do not use same keys for your project
# You are expected to create user own ssh keys

do_install() {
        install -d ${D}${sysconfdir}/ssh/
        install -m 0600 ${S}/ssh_host_rsa_key     ${D}/${sysconfdir}/ssh/
        install -m 0600 ${S}/ssh_host_rsa_key.pub ${D}/${sysconfdir}/ssh/
}

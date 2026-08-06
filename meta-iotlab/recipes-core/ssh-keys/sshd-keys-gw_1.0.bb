DESCRIPTION = "Generation of dummy sshd server keys"
LICENSE = "CECILL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CECILL-2.0;md5=574109ac4bdff61f9c3e0de892ecbd19"

SRC_URI += "file://ssh_host_rsa_key"
SRC_URI += "file://ssh_host_rsa_key.pub"
SRC_URI += "file://ssh_host_ecdsa_key"
SRC_URI += "file://ssh_host_ecdsa_key.pub"
SRC_URI += "file://ssh_host_ed25519_key"
SRC_URI += "file://ssh_host_ed25519_key.pub"

S = "${UNPACKDIR}"

# we copy a dummy server key because in poky/meta/classes/rootfs-postcommands.bbclass
# the read_only_rootfs_hook test if this file exist and otherwise use /etc/ssh/sshd_config_readonly
# and the keys under /var/run/ssh.

# All three types sshd_check_keys looks for (rsa, ecdsa, ed25519 - see
# openssh/openssh/sshd_check_keys) must be pre-provided, not just rsa:
# read_only_rootfs_hook only checks for ssh_host_rsa_key's presence to
# decide whether ssh uses /etc/ssh (assuming everything's already there) or
# /var/run/ssh (auto-generate on every boot). With only rsa present, sshd
# still tries to generate the missing ecdsa/ed25519 keys straight into
# /etc/ssh at boot, which fails since read-only-rootfs is enabled - see
# iotlab-image.inc.

# Do not use same keys for your project
# You are expected to create user own ssh keys

do_install() {
        install -d ${D}${sysconfdir}/ssh/
        install -m 0600 ${S}/ssh_host_rsa_key       ${D}/${sysconfdir}/ssh/
        install -m 0600 ${S}/ssh_host_rsa_key.pub   ${D}/${sysconfdir}/ssh/
        install -m 0600 ${S}/ssh_host_ecdsa_key     ${D}/${sysconfdir}/ssh/
        install -m 0600 ${S}/ssh_host_ecdsa_key.pub ${D}/${sysconfdir}/ssh/
        install -m 0600 ${S}/ssh_host_ed25519_key     ${D}/${sysconfdir}/ssh/
        install -m 0600 ${S}/ssh_host_ed25519_key.pub ${D}/${sysconfdir}/ssh/
}

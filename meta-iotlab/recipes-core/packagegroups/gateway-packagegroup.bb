DESCRIPTION = "Package group list for gateway image"
LICENSE = "GPLv2"

inherit packagegroup

RDEPENDS_${PN} += " \
    base-packagegroup \
    base-config \
    flash-scripts-gateway \
    gateway-code \
    ftdi-utils \
    initrdscripts-mountgw \
    root-sshd-ssh-keys-gw \
    "

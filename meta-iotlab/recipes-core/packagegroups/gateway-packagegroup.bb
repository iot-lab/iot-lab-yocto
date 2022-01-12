DESCRIPTION = "Package group list for gateway image"
LICENSE = "GPLv2"

inherit packagegroup

RDEPENDS_${PN} += " \
    base-packagegroup \
    gateway-code \
    gateway-code-server \
    ftdi-utils \
    initrdscripts-mountgw \
    initrdscripts-networkgw \
    sshd-keys-gw \
    "

DESCRIPTION = "Package group list for gateway image"
LICENSE = "GPL-2.0-only"

inherit packagegroup

RDEPENDS:${PN} += " \
    base-packagegroup \
    gateway-code \
    gateway-code-server \
    ftdi-utils \
    initrdscripts-mountgw \
    initrdscripts-networkgw \
    sshd-keys-gw \
    "

DESCRIPTION = "Package group list for gateway image"
LICENSE = "GPLv2"

inherit packagegroup

RDEPENDS_${PN} += " \
    base-packagegroup \
    base-config \
    flash-scripts-gateway \
    gateway-code \
    cc2538-bsl \
    ftdi-utils \
    initrdscripts-mountgw \
    sshd-keys-gw \
    "

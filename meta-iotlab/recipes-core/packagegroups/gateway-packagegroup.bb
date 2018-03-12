DESCRIPTION = "Package group list for gateway image"
LICENSE = "GPLv2"

inherit packagegroup

RDEPENDS_${PN} += " \
    base-packagegroup \
    base-config \
    flash-scripts-gateway \
    gateway-code \
    python-cc2538-bsl \
    python-pyocd \
    edbg \
    python-msp430-bsl \
    ftdi-utils \
    initrdscripts-mountgw \
    sshd-keys-gw \
    "

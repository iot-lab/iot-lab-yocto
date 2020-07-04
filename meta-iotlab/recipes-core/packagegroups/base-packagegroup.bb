DESCRIPTION = "default package group list for images"
LICENSE = "GPLv2"

inherit packagegroup

RDEPENDS_${PN} += " \
    avarice \
    avrdude \
    base-files \
    base-config \
    initrdscripts-settime \
    initrdscripts-volatile \
    openocd \
    openocd-git \
    python-cc2538-bsl \
    python-pyocd \
    edbg \
    python-msp430-bsl \
    "

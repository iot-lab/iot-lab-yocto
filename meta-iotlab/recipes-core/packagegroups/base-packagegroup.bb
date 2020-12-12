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
    openocd-ti \
    python3-cc2538-bsl \
    python3-pyocd \
    edbg \
    "

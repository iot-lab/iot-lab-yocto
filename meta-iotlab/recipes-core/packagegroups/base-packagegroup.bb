DESCRIPTION = "default package group list for images"
LICENSE = "GPLv2"

inherit packagegroup

RDEPENDS_${PN} += " \
    base-files \
    base-config \
    initrdscripts-settime \
    initrdscripts-volatile \
    openocd \
    openocd-last \
    openocd-upstream \
    python3-cc2538-bsl \
    python3-pyocd \
    edbg \
    "

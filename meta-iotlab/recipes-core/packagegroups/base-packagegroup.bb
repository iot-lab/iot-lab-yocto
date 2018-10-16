DESCRIPTION = "default package group list for images"
LICENSE = "GPLv2"

inherit packagegroup

RDEPENDS_${PN} += " \
    base-files \
    initrdscripts-settime \
    initrdscripts-volatile \
    openocd \
    openocd-last \
    openocd-upstream \
    udev-extrarules \
    "

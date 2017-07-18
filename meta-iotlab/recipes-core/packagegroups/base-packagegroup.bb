DESCRIPTION = "default package group list for images"
LICENSE = "GPLv2"

inherit packagegroup

RDEPENDS_${PN} += " \
    base-files \
    initrdscripts-settime \
    initrdscripts-volatile \
    openocd \
    openocd-last \
    admin-servers-ssh-keys \
    udev-extrarules \
    "

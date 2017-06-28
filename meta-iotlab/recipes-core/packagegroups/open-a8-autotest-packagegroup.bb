DESCRIPTION = "Package group list for open A8 autotest image"
LICENSE = "GPLv2"

inherit packagegroup

RDEPENDS_${PN} += " \
    open-a8-packagegroup \
    root-ssh-keys-open-a8-autotest \
    "

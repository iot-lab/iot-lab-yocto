DESCRIPTION = "default package group list for images"
LICENSE = "GPL-2.0-only"

inherit packagegroup

RDEPENDS:${PN} += " \
    avarice \
    avrdude \
    base-files \
    base-config \
    initrdscripts-settime \
    initrdscripts-volatile \
    openocd \
    openocd-git \
    openocd-ti \
    python3-pyserial        \
    python3-intelhex        \
    python3-cc2538-bsl \
    python3-pyocd \
    edbg \
    pycom-utils \
    segger-jlink \
    "

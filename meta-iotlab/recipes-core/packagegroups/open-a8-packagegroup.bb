DESCRIPTION = "Package group list for open A8 image"
LICENSE = "GPLv2"

inherit packagegroup

RDEPENDS_${PN} += " \
    base-packagegroup \
    base-config \
    flash-scripts-open-a8 \
    ftdi-utils-list \
    initrdscripts-mounta8 \
    initrdscripts-ipv6 \
    initrdscripts-flashidle \
    initrdscripts-serial \
    root-ssh-keys-open-a8 \
    python-scons \
    python-bottle \
    python-pydispatcher \
    mosquitto \
    mosquitto-rsmb \
    nodejs \
    openjdk-8 \
    openjre-8 \
    python-paho-mqtt \
    wpantund \
    "

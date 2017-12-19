DESCRIPTION = "Package group list for open A8 image"
LICENSE = "GPLv2"

inherit packagegroup

# OpenWSN python package depedencies
RDEPENDS_${PN} += " \
    python-scons \
    python-bottle \
    python-pydispatcher \
    python-yappi \
    python-pyzmq \
    python-netifaces \
    python-cbor \
    python-hkdf \
    python-pycryptodome \
    python-numpy \
    "
# nghttp2 package depedencies
RDEPENDS_${PN} += " \
    python-cython \
    python3-cython \
    "

RDEPENDS_${PN} += " \
    base-packagegroup \
    base-config \
    flash-scripts-open-a8 \
    ftdi-utils-list \
    initrdscripts-mounta8 \
    initrdscripts-ipv6 \
    initrdscripts-flashidle \
    initrdscripts-serial \
    sshd-keys-open-a8 \
    mosquitto \
    mosquitto-rsmb \
    nodejs \
    openjdk-8 \
    openjre-8 \
    python-paho-mqtt \
    wpantund \
    "

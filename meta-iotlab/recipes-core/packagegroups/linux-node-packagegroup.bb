DESCRIPTION = "Package group list for open A8 image"
LICENSE = "GPLv2"

inherit packagegroup

# gateway-code-standalone 
RDEPENDS_${PN} += " \
    base-packagegroup \
    gateway-code \
    sshd-keys-linux-node \
    ftdi-utils-list \
    initrdscripts-mountnode \
    initrdscripts-ipv6 \
    initrdscripts-flashidle \
    initrdscripts-serial \
    "

# OpenWSN python package depedencies
RDEPENDS_${PN} += " \
    python3-scons \
    python3-bottle \
    python3-pydispatcher \
    python3-yappi \
    python3-pyzmq \
    python3-netifaces \
    python3-cbor \
    python3-hkdf \
    python3-pycryptodome \
    "

# nghttp2 package depedencies
RDEPENDS_${PN} += " \
    python3-cython \
    "

RDEPENDS_${PN} += " \
    mosquitto \
    mosquitto-rsmb \
    paho-mqtt-c \
    nodejs \
    openjdk-8 \
    openjre-8 \
    openvpn \
    python3-paho-mqtt \
    wpantund \
    "

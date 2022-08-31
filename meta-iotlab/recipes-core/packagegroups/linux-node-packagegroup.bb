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

# OpenWSN python package dependencies
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
    python3-numpy \
    "

# nghttp2 package dependencies
RDEPENDS_${PN} += " \
    python3-cython \
    "

RDEPENDS_${PN} += " \
    libmosquitto1 \
    libmosquittopp1 \
    mosquitto-clients \
    mosquitto \
    mosquitto-rsmb \
    paho-mqtt-c \
    openjdk-8 \
    openjre-8 \
    openvpn \
    python3-paho-mqtt \
    wpantund \
    "

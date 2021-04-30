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

# Jool NAT64 tools
RDEPENDS_${PN} += " \
    jool-tools \
    "

# nghttp2 package dependencies
RDEPENDS_${PN} += " \
    python3-cython \
    "

RDEPENDS_${PN} += " \
    mosquitto \
    mosquitto-rsmb \
    paho-mqtt-c \
    nodejs \
    openvpn \
    openjdk-8 \
    openjre-8 \
    python-paho-mqtt \
    python3-paho-mqtt \
    wpantund \
    "

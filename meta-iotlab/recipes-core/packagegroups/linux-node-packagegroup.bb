DESCRIPTION = "Package group list for open A8 image"
LICENSE = "GPLv2"

inherit packagegroup

# gateway-code-standalone 
RDEPENDS_${PN} += " \
    base-packagegroup \
    gateway-code \
    sshd-keys-linux-node \
    ftdi-utils-list \
    initrdscripts-mounta8 \
    initrdscripts-ipv6 \
    initrdscripts-flashidle \
    initrdscripts-serial \
    "

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
    mosquitto \
    mosquitto-rsmb \
    paho-mqtt-c \
    nodejs \
    openjdk-8 \
    openjre-8 \
    openvpn \
    python-paho-mqtt \
    wpantund \
    "

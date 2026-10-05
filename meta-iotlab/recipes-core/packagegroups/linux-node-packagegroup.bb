DESCRIPTION = "Package group list for open A8 image"
LICENSE = "GPL-2.0-only"

inherit packagegroup

# gateway-code-standalone 
RDEPENDS:${PN} += " \
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
RDEPENDS:${PN} += " \
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
RDEPENDS:${PN} += " \
    python3-cython \
    "

# openjdk-8/openjre-8 are not available anymore: meta-java was dropped with
# the switch to walnascar.
RDEPENDS:${PN} += " \
    mosquitto \
    mosquitto-rsmb \
    paho-mqtt-c \
    openvpn \
    python3-paho-mqtt \
    wpantund \
    "

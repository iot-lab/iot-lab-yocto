DESCRIPTION = "LoRa Gateway Bridge"
HOMEPAGE = "https://www.loraserver.io/"
PRIORITY = "optional"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5301050fd7cd58850085239d559297be"
SRC_URI = "https://artifacts.loraserver.io/downloads/lora-gateway-bridge/lora-gateway-bridge_${PV}_linux_armv5.tar.gz \
           file://lora-gateway-bridge.toml \
"
SRC_URI[md5sum] = "e7700cd481eb1c6545ae5b4bf51379cd"
SRC_URI[sha256sum] = "4ebe707b634087951f6f99a6e1a3ffe65ff1f8bcd43ebf66e83776d3c6f5248d"
PR = "r1"

LORA_GATEWAY_BRIDGE_DIR = "/opt/lora-gateway-bridge"

INSANE_SKIP_${PN} = "ldflags"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${LORA_GATEWAY_BRIDGE_DIR}
    install -m 755 lora-gateway-bridge ${D}${LORA_GATEWAY_BRIDGE_DIR}/

    install -d ${D}/var/config/lora-gateway-bridge
    install -m 0644 ${WORKDIR}/lora-gateway-bridge.toml ${D}/opt/lora-gateway-bridge/lora-gateway-bridge.toml
}

FILES_${PN} += "${LORA_GATEWAY_BRIDGE_DIR}"
FILES_${PN}-dbg += "${LORA_GATEWAY_BRIDGE_DIR}/.debug"

CONFFILES_${PN} += "/var/config/lora-gateway-bridge/lora-gateway-bridge.toml"

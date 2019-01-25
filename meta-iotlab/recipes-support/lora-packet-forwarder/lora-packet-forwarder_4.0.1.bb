DESCRIPTION = "LoRa Packet Forwarder"
HOMEPAGE = "https://github.com/Lora-net/packet_forwarder"
PRIORITY = "optional"
SECTION = "console/utils"
# Semtech license is a modified BSD-style license
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=22af7693d7b76ef0fc76161c4be76c45"
DEPENDS = "lora-gateway"
RDEPENDS_${PN} += "bash"
PR = "r12"

SRCREV = "v${PV}"

SRC_URI = "git://github.com/Lora-net/packet_forwarder.git;protocol=git \
           file://global_conf.json \
           file://local_conf.json \
           file://start.sh \
           file://lora-packet-forwarder-add-spi-dev-path.patch \
           file://lora-packet-forwarder-remove-jit-power-check.patch \
"

S = "${WORKDIR}/git"
B = "${S}"

LORA_DIR = "/opt/lora"

export LGW_PATH = "${STAGING_LIBDIR}/lora"
export LGW_INC = "${STAGING_INCDIR}/lora"

CFLAGS += "-I${LGW_INC} -Iinc -I. -std=gnu11"

do_compile() {
  oe_runmake
}

do_install() {
  install -d ${D}${LORA_DIR}
  install -m 755 lora_pkt_fwd/lora_pkt_fwd ${D}${LORA_DIR}/
  install -m 755 ${WORKDIR}/global_conf.json ${D}${LORA_DIR}/
  install -m 755 ${WORKDIR}/local_conf.json ${D}${LORA_DIR}/
  install -m 755 ${WORKDIR}/start.sh ${D}${LORA_DIR}/

  install -d ${D}${LORA_DIR}/forwarder-utils
  install -m 755 util_sink/util_sink ${D}${LORA_DIR}/forwarder-utils/
  install -m 755 util_ack/util_ack ${D}${LORA_DIR}/forwarder-utils/
  install -m 755 util_tx_test/util_tx_test ${D}${LORA_DIR}/forwarder-utils/
}

FILES_${PN} += "${LORA_DIR}"
FILES_${PN}-dbg += "${LORA_DIR}/.debug ${LORA_DIR}/forwarder-utils/.debug"

# disable this on purpose for dev purposes
do_rm_work() {
  echo "skipping"
}

DESCRIPTION = "RSMB: Really Small Message Broker"
HOMEPAGE = "https://github.com/eclipse/mosquitto.rsmb"

LICENSE = "EPL-1.0 | EDL-1.0"

LIC_FILES_CHKSUM = " \
        file://edl-v10;md5=3adfcc70f5aeb7a44f3f9b495aa1fbf3 \
        file://epl-v10;md5=659c8e92a40b6df1d9e3dccf5ae45a08 \
        "

PR = "r2"

PV = "git-src${SRCDATE}-r${SRCPV}"

SRC_URI  = "git://github.com/eclipse/mosquitto.rsmb.git;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/"
B = "${S}/rsmb/src"

# run make in ${B}
inherit base

do_install() {
        install -d                              ${D}${bindir}
        install -m 0755 ${B}/broker             ${D}${bindir}/broker
        install -m 0755 ${B}/broker_dbg         ${D}${bindir}/broker_dbg
        install -m 0755 ${B}/broker_mqtts       ${D}${bindir}/broker_mqtts
        install         ${B}/Messages.1.3.0.2   ${D}${bindir}/Messages.1.3.0.2
}

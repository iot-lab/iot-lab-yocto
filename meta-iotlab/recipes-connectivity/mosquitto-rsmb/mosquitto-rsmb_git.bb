DESCRIPTION = "RSMB: Really Small Message Broker"
HOMEPAGE = "https://github.com/eclipse/mosquitto.rsmb"

LICENSE = "EPL-1.0 | EDL-1.0"

LIC_FILES_CHKSUM = " \
        file://edl-v10;md5=3adfcc70f5aeb7a44f3f9b495aa1fbf3 \
        file://epl-v10;md5=659c8e92a40b6df1d9e3dccf5ae45a08 \
        "

PV = "1.3.0.2+git"

SRC_URI  = "git://github.com/eclipse/mosquitto.rsmb.git;protocol=https;branch=master"
SRCREV = "36fd4ba9433da172f0af580eb6c1a3139b63c355"

S = "${WORKDIR}/git"
B = "${S}/rsmb/src"

# No build system class: the default do_compile runs make in ${B}

do_install() {
        install -d                              ${D}${bindir}
        install -m 0755 ${B}/broker             ${D}${bindir}/broker
        install -m 0755 ${B}/broker_dbg         ${D}${bindir}/broker_dbg
        install -m 0755 ${B}/broker_mqtts       ${D}${bindir}/broker_mqtts
        install         ${B}/Messages.1.3.0.2   ${D}${bindir}/Messages.1.3.0.2
}

# The Makefile ignores the Yocto compiler and linker flags: no GNU_HASH, and
# build paths left in the debug symbols of broker_dbg
INSANE_SKIP:${PN} = "ldflags"
INSANE_SKIP:${PN}-dev = "ldflags"
INSANE_SKIP:${PN}-dbg = "buildpaths"

DESCRIPTION = "A script that communicates with the boot loader of the TI CC2538 and other SoCs (System on Chips)."
HOMEPAGE = "https://github.com/JelmerT/cc2538-bsl"
SECTION = "console"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://cc2538-bsl.py;md5=d1bcb7441b237a8b695b4d6624dcf7b2"

PR = "r1"

PV = "git-src${SRCDATE}-r${SRCPV}"

SRC_URI = "git://github.com/JelmerT/cc2538-bsl.git;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit autotools

do_install(){
    install -d ${D}${bindir}
    install -m 0755 ${S}/cc2538-bsl.py  ${D}${bindir}/
}

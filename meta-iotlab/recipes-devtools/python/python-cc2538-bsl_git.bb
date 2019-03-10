DESCRIPTION = "A script that communicates with the boot loader of the TI CC2538 and other SoCs (System on Chips)."
HOMEPAGE = "https://github.com/JelmerT/cc2538-bsl"
SECTION = "console"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://cc2538-bsl.py;md5=fd7e87c62fec34c3f872ee3fc0a46d81"

PR = "r1"

PV = "git-src${SRCDATE}-r${SRCPV}"

SRC_URI = "git://github.com/JelmerT/cc2538-bsl.git;protocol=https"
SRCREV = "c6100a7794c7b530923145c03e37412013a4551e"

S = "${WORKDIR}/git"

do_install(){
    install -d ${D}${bindir}
    install -m 0755 ${S}/cc2538-bsl.py  ${D}${bindir}/
}

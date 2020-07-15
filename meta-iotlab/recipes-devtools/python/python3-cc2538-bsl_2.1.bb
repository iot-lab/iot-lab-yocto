DESCRIPTION = "A script that communicates with the boot loader of the TI CC2538 and other SoCs (System on Chips)."
HOMEPAGE = "https://github.com/JelmerT/cc2538-bsl"
SECTION = "console"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://cc2538-bsl.py;md5=fd7e87c62fec34c3f872ee3fc0a46d81"

PR = "r1"

SRCNAME = "cc2538-bsl"
SRC_URI = "https://github.com/JelmerT/${SRCNAME}/archive/${PV}.tar.gz"

SRC_URI[md5sum] = "ad7b1e48b4cac832b0a8045cd4d6dd2d"
SRC_URI[sha256sum] = "30f2d5a200ffecd549e05311e94681514ff6e251aeee8e87b88c0ca9d219aad0"

S = "${WORKDIR}/${SRCNAME}-${PV}"

do_install(){
    install -d ${D}${bindir}
    install -m 0755 ${S}/cc2538-bsl.py  ${D}${bindir}/
}

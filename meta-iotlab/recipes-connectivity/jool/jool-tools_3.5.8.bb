DESCRIPTION = "Jool is an Open Source IPv4/IPv6 Translator"
SECTION = "networking"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://../COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "https://github.com/NICMx/Jool/archive/refs/tags/v${PV}.tar.gz"
SRC_URI[md5sum] = "efaf1e14eb2ac643774106d3ea72d6bb"
SRC_URI[sha256sum] = "69afcded2d07cc598bf278084150a245676be05ad864a6d71a6c6b625e7edeb5"

DEPENDS = "libnl iptables"

S = "${WORKDIR}/Jool-${PV}/usr"

inherit autotools-brokensep pkgconfig

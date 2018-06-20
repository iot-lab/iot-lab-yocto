DESCRIPTION = "Libjaylink: access to SEGGER J-Link devices"
SECTION = "libs"
DEPENDS += "libusb"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRCREV = "8645845c1abebd004e991ba9a7f808f4fd0c608b"
SRC_URI = "git://github.com/syntacore/libjaylink;protocol=https"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

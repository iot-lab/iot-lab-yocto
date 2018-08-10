DESCRIPTION = "hub-ctrl.c: Control USB power on a port by port basis on some USB hubs"
HOMEPAGE = "https://github.com/codazoda/hub-ctrl.c"
SECTION = "console"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "git://github.com/codazoda/hub-ctrl.c;protocol=https"
SRCREV = "42095e522859059e8a5f4ec05c1e3def01a870a9"

DEPENDS = "libusb"

S = "${WORKDIR}"

SRCS = "${S}/git/hub-ctrl.c"

LIBS += "-lusb"

CFLAGS += "-W -Wall -Wextra -O2 -std=gnu11"

do_compile() {
	${CC} ${CFLAGS} ${SRCS} ${LIBS} -o ${S}/git/hub-ctrl
}

do_install() {
	install -d ${D}${bindir}
	install -m 755 ${S}/git/hub-ctrl ${D}${bindir}
}

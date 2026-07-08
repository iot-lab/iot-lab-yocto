SUMMARY = "Software to turn the RTL2832U into a SDR"
HOMEPAGE = "http://sdr.osmocom.org/trac/wiki/rtl-sdr"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "libusb1"

PV = "0.5.3+git${SRCPV}"

SRC_URI = "git://git.osmocom.org/rtl-sdr;protocol=https;nobranch=1 \
           file://0001-Do-not-store-build-system-CFLAGS-in-the-pkgconfig-fi.patch \
          "
SRCREV = "e3c03f738f5aef4dc51e2b741fbdb542b9cc1bb1"

S = "${UNPACKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF = "--enable-driver-detach"

do_install:append() {
	install -d ${D}${sysconfdir}/udev/rules.d
	install -m 0644 ${S}/rtl-sdr.rules ${D}${sysconfdir}/udev/rules.d
}

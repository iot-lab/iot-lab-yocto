DESCRIPTION = "An Open Source MQTT v3.1/v3.1.1 Broker"
HOMEPAGE = "http://www.mosquitto.org"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=62ddc846179e908dc0c8efec4a42ef20"

inherit autotools
inherit useradd

DEPENDS = "openssl python-pytest-runner util-linux"
RDEPENDS_${PN} = "libcrypto libssl util-linux-libuuid"

SRC_URI += "http://mosquitto.org/files/source/mosquitto-${PV}.tar.gz"
SRC_URI += "file://0001-config-mk_${PV}.patch"

SRC_URI[md5sum] = "89a57f11cdfec140fa21fe3d4493b2ca"
SRC_URI[sha256sum] = "c643c7123708aadcd29287dda7b5ce7c910f75b02956a8fc4fe65ad2ea767a5f"

S = "${WORKDIR}/${PN}-${PV}"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "-r -m -d /var/lib/mosquitto -s /usr/sbin/nologin -g nogroup mosquitto"

do_compile() {
	cd ${S}
	oe_runmake
}

do_install() {
	cd ${S}
	mkdir -p ${D}${bindir}
	mkdir -p ${D}${sbindir}
	mkdir -p ${D}${libdir}
	mkdir -p ${D}${sysconfdir}
	oe_runmake install DESTDIR=${D}
}

FILES_${PN} = "${bindir} ${sbindir} ${libdir} ${sysconfdir}"

INSANE_SKIP_${PN} = "already-stripped"

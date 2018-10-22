DESCRIPTION = "Control application for Yepkit YKUSH Switchable USB Hub board."
HOMEPAGE = "https://github.com/Yepkit/ykush"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=4b29e96047e31a3c38509d2c49b768b3"

DEPENDS += "libusb"
RDEPENDS_${PN} += "hidapi libusb1 udev"

SRC_URI = "git://github.com/Yepkit/ykush.git;protocol=https \
           file://fix_makefile.patch"
SRCREV = "30d490423782a1d5379d1691a2539fad6ff6046d"
PV = "git"
S = "${WORKDIR}/git"

PATCHTOOL = "git"

do_install() {
	install -d ${D}${bindir}
	install -m 755 ${S}/bin/ykushcmd ${D}${bindir}
}

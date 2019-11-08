DESCRIPTION = "Gateway Python Code"
LICENSE = "CECILL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CECILL-2.0;md5=574109ac4bdff61f9c3e0de892ecbd19"

DEPENDS = "python oml2 oml2-native"

SRC_URI  = "git://github.com/iot-lab/iot-lab-gateway.git;user=git;protocol=ssh \
	    file://start_dc_on \
	    file://stop_dc_on"

PR = "r4"
PV = "git-src${SRCDATE}-r${SRCPV}"

S = "${WORKDIR}/git/"
SRCREV = "${AUTOREV}"
# SRCREV = "2.9.0"

# Add a package gateway-code-server with update-rc.d script & start|stop_dc_on scripts & post install dialout group
# gateway: install gateway-code and gateway-code-server packages
# Linux open node (eg. A8 and RPI3): install gateway-code package (udev rules and generic programmer)

PACKAGES =+ "${PN}-server"

inherit update-rc.d setuptools
INITSCRIPT_PACKAGES = "${PN}-server"
INITSCRIPT_NAME_${PN}-server = "gateway-server-daemon"
INITSCRIPT_PARAMS_${PN}-server = "defaults"

do_install_append () {
	install -d ${D}${sysconfdir}/udev/rules.d/
	install -m 0644 ${S}/bin/rules.d/*.rules ${D}${sysconfdir}/udev/rules.d/

	install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${S}/bin/init_script/gateway-server-daemon ${D}${sysconfdir}/init.d/gateway-server-daemon

	install -d ${D}${bindir}/
	install -m 0755 ${WORKDIR}/stop_dc_on  ${D}${bindir}/
	install -m 0755 ${WORKDIR}/start_dc_on ${D}${bindir}/

	# create www-data home folder and .ssh folder
	# used to access A8 open nodes
	install -m 0755 -o www-data -g www-data -d ${D}${localstatedir}/www/
	install -m 0755 -o www-data -g www-data -d ${D}${localstatedir}/www/.ssh
}

FILES_${PN} += "${libdir}/*"
FILES_${PN} += "${sysconfdir}/udev/rules.d/*"
FILES_${PN}-server += "${bindir}/start_dc_on"
FILES_${PN}-server += "${bindir}/stop_dc_on"
FILES_${PN}-server += "${sysconfdir}/init.d/gateway-server-daemon"

RDEPENDS_${PN} = "openocd socat oml2"
RDEPENDS_${PN} += "python python-pyserial python-modules"
RDEPENDS_${PN} += "python-argparse python-bottle python-paste"
RDEPENDS_${PN} += "python-subprocess32 python-pyelftools"
# for usermod on postinst
RDEPENDS_${PN} += "shadow"
RDEPENDS_${PN} += "openssh-ssh openssh-scp"

# for development
RDEPENDS_${PN} += "python-gcovr"
RDEPENDS_${PN} += "python-nose (>= 1.3.0)"
RDEPENDS_${PN} += "python-coverage"
RDEPENDS_${PN} += "python-nosexcover"
RDEPENDS_${PN} += "python-mock python-pep8"
RDEPENDS_${PN} += "python-pylint"
RDEPENDS_${PN} += "python-tox (>= 3.4)"
RDEPENDS_${PN} += "python-testfixtures"
RDEPENDS_${PN} += "python-pytest"
RDEPENDS_${PN} += "python-pytest-cov"
RDEPENDS_${PN} += "python-webtest"
RDEPENDS_${PN} += "python-codecov"


pkg_postinst_${PN}-server () {
  # Add www-data to dialout group
  if test "x$D" != "x"; then
    OPT="--root $D"   # Installing into a sysroot
  else
    OPT=""
  fi
  usermod $OPT -a -G dialout www-data
}

# Allow shipping arduino elf files
INSANE_SKIP_${PN} = "arch"

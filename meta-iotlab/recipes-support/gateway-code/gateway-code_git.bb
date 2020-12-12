DESCRIPTION = "Gateway Python Code"
LICENSE = "CECILL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CECILL-2.0;md5=574109ac4bdff61f9c3e0de892ecbd19"

DEPENDS = "python3 oml2 oml2-native control-node-serial"

SRC_URI  = "git://github.com/iot-lab/iot-lab-gateway.git;protocol=https \
	    file://start_dc_on \
	    file://stop_dc_on"

PR = "r4"
PV = "git-src${SRCDATE}-r${SRCPV}"

S = "${WORKDIR}/git/"
SRCREV = "${AUTOREV}"
# SRCREV = "2.10.0"

# Add a package gateway-code-server with update-rc.d script & start|stop_dc_on scripts & post install dialout group
# gateway: install gateway-code and gateway-code-server packages
# Linux open node (eg. A8 and RPI3): install gateway-code package (udev rules and generic programmer)

PACKAGES =+ "${PN}-server"

WWW_DIR ?= "${localstatedir}/www"
WWW_USER ?= "www-data"

inherit update-rc.d setuptools3 useradd
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
	install -m 0755 -o ${WWW_USER} -g ${WWW_USER} -d ${D}${WWW_DIR}/
	install -m 0755 -o ${WWW_USER} -g ${WWW_USER} -d ${D}${WWW_DIR}/.ssh
}

FILES_${PN} += "${libdir}/*"
FILES_${PN} += "${sysconfdir}/udev/rules.d/*"
FILES_${PN}-server += "${bindir}/start_dc_on"
FILES_${PN}-server += "${bindir}/stop_dc_on"
FILES_${PN}-server += "${sysconfdir}/init.d/gateway-server-daemon"

RDEPENDS_${PN} = "openocd socat oml2"
RDEPENDS_${PN} += "python3 python3-pyserial"
RDEPENDS_${PN} += "python3-bottle python3-paste"
RDEPENDS_${PN} += "python3-pyelftools"
# for usermod on postinst
RDEPENDS_${PN} += "shadow"
RDEPENDS_${PN} += "openssh-ssh openssh-scp"

# for development
RDEPENDS_${PN} += "python3-gcovr"
RDEPENDS_${PN} += "python3-coverage"
RDEPENDS_${PN} += "python3-nosexcover"
RDEPENDS_${PN} += "python3-mock python3-pep8"
RDEPENDS_${PN} += "python3-pylint"
RDEPENDS_${PN} += "python3-tox (>= 3.4)"
RDEPENDS_${PN} += "python3-testfixtures"
RDEPENDS_${PN} += "python3-pytest"
RDEPENDS_${PN} += "python3-pytest-cov"
RDEPENDS_${PN} += "python3-webtest"
RDEPENDS_${PN} += "python3-codecov"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "-u 33 -g 33 -d ${WWWDIR} -r -M -s /bin/sh ${WWW_USER}"
GROUPADD_PARAM_${PN} = "-g 33 ${WWW_USER}"

pkg_postinst_${PN}-server () {
  # Add www-data to dialout group
  if test "x$D" != "x"; then
    OPT="--root $D"   # Installing into a sysroot
  else
    OPT=""
  fi
  usermod $OPT -a -G dialout ${WWW_USER}
}

# Allow shipping arduino elf files
INSANE_SKIP_${PN} = "arch"

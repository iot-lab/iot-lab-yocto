DESCRIPTION = "Gateway Python Code"
LICENSE = "CECILL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CECILL-2.0;md5=574109ac4bdff61f9c3e0de892ecbd19"

DEPENDS = "python3 oml2 oml2-native"

SRC_URI  = "git://github.com/iot-lab/iot-lab-gateway.git;protocol=https;branch=master \
	    file://start_dc_on \
	    file://stop_dc_on"

PR = "r4"
PV = "git-src${SRCDATE}-r${SRCPV}"

S = "${UNPACKDIR}/git"
SRCREV = "${AUTOREV}"
# SRCREV = "2.10.0"

# Add a package gateway-code-server with update-rc.d script & start|stop_dc_on scripts & post install dialout group
# gateway: install gateway-code and gateway-code-server packages
# Linux open node (eg. A8 and RPI3): install gateway-code package (udev rules and generic programmer)

PACKAGES =+ "${PN}-server"

WWW_DIR ?= "${localstatedir}/www"
WWW_USER ?= "www-data"

# iot-lab-gateway is a pyproject.toml (hatchling) project, without setup.py
inherit update-rc.d python_hatchling useradd
INITSCRIPT_PACKAGES = "${PN}-server"
INITSCRIPT_NAME:${PN}-server = "gateway-server-daemon"
# must start after gateway-server-network init.d script (S83/K17) which set virtual ip for open nodes
INITSCRIPT_PARAMS:${PN}-server = "defaults 85 15"

do_install:append () {
	install -d ${D}${sysconfdir}/udev/rules.d/
	install -m 0644 ${S}/bin/rules.d/*.rules ${D}${sysconfdir}/udev/rules.d/

	install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${S}/bin/init_script/gateway-server-daemon ${D}${sysconfdir}/init.d/gateway-server-daemon

	install -d ${D}${bindir}/
	install -m 0755 ${UNPACKDIR}/stop_dc_on  ${D}${bindir}/
	install -m 0755 ${UNPACKDIR}/start_dc_on ${D}${bindir}/

	# create www-data home folder and .ssh folder
	# used to access A8 open nodes
	install -m 0755 -o ${WWW_USER} -g ${WWW_USER} -d ${D}${WWW_DIR}/
	install -m 0755 -o ${WWW_USER} -g ${WWW_USER} -d ${D}${WWW_DIR}/.ssh
}

FILES:${PN} += "${libdir}/*"
FILES:${PN} += "${sysconfdir}/udev/rules.d/*"
FILES:${PN}-server += "${bindir}/start_dc_on"
FILES:${PN}-server += "${bindir}/stop_dc_on"
FILES:${PN}-server += "${sysconfdir}/init.d/gateway-server-daemon"

RDEPENDS:${PN}-server = "bash" 
RDEPENDS:${PN} = "openocd socat oml2 control-node-serial"
RDEPENDS:${PN} += "python3 python3-pyserial"
RDEPENDS:${PN} += "python3-bottle python3-paste"
RDEPENDS:${PN} += "python3-pyelftools"
# for usermod on postinst
RDEPENDS:${PN} += "shadow"
RDEPENDS:${PN} += "openssh-ssh openssh-scp"

# for development
RDEPENDS:${PN} += "python3-gcovr"
RDEPENDS:${PN} += "python3-coverage"
RDEPENDS:${PN} += "python3-mock python3-pep8"
RDEPENDS:${PN} += "python3-pylint"
RDEPENDS:${PN} += "python3-tox (>= 3.4)"
RDEPENDS:${PN} += "python3-testfixtures"
RDEPENDS:${PN} += "python3-pytest"
RDEPENDS:${PN} += "python3-pytest-cov"
RDEPENDS:${PN} += "python3-webtest"
RDEPENDS:${PN} += "python3-beautifulsoup4"
RDEPENDS:${PN} += "python3-webob"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "-u 33 -g 33 -d ${WWW_DIR} -r -M -s /bin/sh ${WWW_USER}"
GROUPADD_PARAM:${PN} = "-g 33 ${WWW_USER}"

pkg_postinst:${PN}-server () {
  # Add www-data to dialout group
  if test "x$D" != "x"; then
    OPT="--root $D"   # Installing into a sysroot
  else
    OPT=""
  fi
  usermod $OPT -a -G dialout ${WWW_USER}
}

# Allow shipping arduino elf files (and their split-out debug symbols)
INSANE_SKIP:${PN} = "arch"
INSANE_SKIP:${PN}-dbg = "arch"

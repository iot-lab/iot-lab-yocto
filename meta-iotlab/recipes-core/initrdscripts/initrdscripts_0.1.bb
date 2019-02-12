DESCRIPTION = "Initscripts provide the basic system startup initialization scripts for the system"
LICENSE = "CECILL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CECILL-2.0;md5=574109ac4bdff61f9c3e0de892ecbd19"

S = "${WORKDIR}"

FILESEXTRAPATHS_prepend = "${THISDIR}/files/linux-node/:"
FILESEXTRAPATHS_prepend = "${THISDIR}/files/gateway/:"
SRC_URI += "file://set_default_values_to_volatile.sh"
SRC_URI += "file://set_time.sh"
SRC_URI += "file://networking_ipv6"
SRC_URI += "file://ipv6.profile"
SRC_URI += "file://flash_idle"
SRC_URI += "file://nfs_mount_conf_a8"
SRC_URI += "file://nfs_mount_conf_users"
SRC_URI += "file://serial_redirection"

# split package to use update-rc.d class
ALLOW_EMPTY_${PN}   = "1"

RDEPENDS_${PN}      = "${PN}-settime ${PN}-volatile ${PN}-ipv6 ${PN}-flashidle ${PN}-mounta8 ${PN}-mountgw ${PN}-serial"
PACKAGES            =+ "${PN}-settime ${PN}-volatile ${PN}-ipv6 ${PN}-flashidle ${PN}-mounta8 ${PN}-mountgw ${PN}-serial"

inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}-settime ${PN}-volatile ${PN}-ipv6 ${PN}-flashidle ${PN}-mounta8 ${PN}-mountgw"

INITSCRIPT_NAME_${PN}-volatile   = "set_default_values_to_volatile.sh"
INITSCRIPT_PARAMS_${PN}-volatile = "start 80 S ."
FILES_${PN}-volatile             = "${sysconfdir}/init.d/set_default_values_to_volatile.sh"

# requires /etc/hosts to be configured by set_default_values
RDEPENDS_${PN}-settime          += "coreutils"
INITSCRIPT_NAME_${PN}-settime    = "set_time.sh"
INITSCRIPT_PARAMS_${PN}-settime  = "start 81 S ."
FILES_${PN}-settime              = "${sysconfdir}/init.d/set_time.sh"

INITSCRIPT_NAME_${PN}-mounta8       = "nfs_mount_conf_a8"
INITSCRIPT_PARAMS_${PN}-mounta8     = "start 82 S . stop 18 0 6 ."
FILES_${PN}-mounta8                 = "${sysconfdir}/init.d/nfs_mount_conf_a8"

INITSCRIPT_NAME_${PN}-mountgw       = "nfs_mount_conf_users"
INITSCRIPT_PARAMS_${PN}-mountgw     = "start 82 S . stop 18 0 6 ."
FILES_${PN}-mountgw                 += "${sysconfdir}/init.d/nfs_mount_conf_users"
FILES_${PN}-mountgw                 += "/iotlab/users"

INITSCRIPT_NAME_${PN}-ipv6       = "networking_ipv6"
# start AFTER nfs mounting
INITSCRIPT_PARAMS_${PN}-ipv6     = "start 83 S ."
FILES_${PN}-ipv6                 += "${sysconfdir}/init.d/networking_ipv6"
FILES_${PN}-ipv6                 += "${sysconfdir}/profile.d/ipv6.sh"

# don't start by default by update-rc.d
RDEPENDS_${PN}-serial              += "gateway-code"
FILES_${PN}-serial                 = "${sysconfdir}/init.d/serial_redirection"

# don't try to check elf files
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_SYSROOT_STRIP = "1"
INSANE_SKIP_${PN}-flashidle += "arch"
INSANE_SKIP_${PN}-flashidle-dbg += "arch"

RDEPENDS_${PN}-flashidle += "gateway-code"
INITSCRIPT_NAME_${PN}-flashidle = "flash_idle"
INITSCRIPT_PARAMS_${PN}-flashidle = "start 85 S ."
FILES_${PN}-flashidle     += "${sysconfdir}/init.d/flash_idle"

# release files
do_install() {
    install -d                                             ${D}/${sysconfdir}/init.d

    # set time
    install -m 0755 ${S}/set_time.sh                       ${D}/${sysconfdir}/init.d/

    # volatile
    install -m 0755 ${S}/set_default_values_to_volatile.sh ${D}/${sysconfdir}/init.d/

    # ipv6 network
    install -m 0755 ${S}/networking_ipv6                   ${D}/${sysconfdir}/init.d/
    install -d                                             ${D}/${sysconfdir}/profile.d
    install -m 0755 ${S}/ipv6.profile                      ${D}/${sysconfdir}/profile.d/ipv6.sh

    # flash idle
    install -m 0755 ${S}/flash_idle                        ${D}/${sysconfdir}/init.d/

    # mount gateway
    install -m 0755 ${S}/nfs_mount_conf_users              ${D}/${sysconfdir}/init.d/
    install -d                                             ${D}/iotlab/users

    # mount a8
    install -m 0755 ${S}/nfs_mount_conf_a8                 ${D}/${sysconfdir}/init.d/

    # serial redirection
    install -m 0755 ${S}/serial_redirection                ${D}/${sysconfdir}/init.d/
}

pkg_postinst_${PN}-mounta8 () {
  install -m 0755 -d $D${ROOT_HOME}/A8
  # add shared symlink to A8 directory for other Linux nodes (eg. RPI3)
  ln -s -r $D${ROOT_HOME}/A8 $D${ROOT_HOME}/shared
}

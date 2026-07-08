DESCRIPTION = "Initscripts provide the basic system startup initialization scripts for the system"
LICENSE = "CECILL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CECILL-2.0;md5=574109ac4bdff61f9c3e0de892ecbd19"

S = "${UNPACKDIR}"

FILESEXTRAPATHS:prepend = "${THISDIR}/files/linux-node/:"
FILESEXTRAPATHS:prepend = "${THISDIR}/files/gateway/:"
SRC_URI += "file://set_default_values_to_volatile.sh"
SRC_URI += "file://set_time.sh"
SRC_URI += "file://networking_ipv6"
SRC_URI += "file://ipv6.profile"
SRC_URI += "file://flash_idle"
SRC_URI += "file://nfs_mount_conf_shared"
SRC_URI += "file://nfs_mount_conf_users"
SRC_URI += "file://gateway-server-network"
SRC_URI += "file://serial_redirection"

# split package to use update-rc.d class
ALLOW_EMPTY:${PN}   = "1"

RDEPENDS:${PN}      = "${PN}-settime ${PN}-volatile ${PN}-ipv6 ${PN}-flashidle ${PN}-mountnode ${PN}-mountgw ${PN}-networkgw ${PN}-serial"
PACKAGES            =+ "${PN}-settime ${PN}-volatile ${PN}-ipv6 ${PN}-flashidle ${PN}-mountnode ${PN}-mountgw ${PN}-networkgw ${PN}-serial"

inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}-settime ${PN}-volatile ${PN}-ipv6 ${PN}-flashidle ${PN}-mountnode ${PN}-mountgw ${PN}-networkgw"

INITSCRIPT_NAME:${PN}-volatile   = "set_default_values_to_volatile.sh"
INITSCRIPT_PARAMS:${PN}-volatile = "start 80 S ."
FILES:${PN}-volatile             = "${sysconfdir}/init.d/set_default_values_to_volatile.sh"

# requires /etc/hosts to be configured by set_default_values
RDEPENDS:${PN}-settime          += "coreutils"
INITSCRIPT_NAME:${PN}-settime    = "set_time.sh"
INITSCRIPT_PARAMS:${PN}-settime  = "start 81 S ."
FILES:${PN}-settime              = "${sysconfdir}/init.d/set_time.sh"

INITSCRIPT_NAME:${PN}-mountnode       = "nfs_mount_conf_shared"
INITSCRIPT_PARAMS:${PN}-mountnode     = "defaults 82 18"
FILES:${PN}-mountnode                 = "${sysconfdir}/init.d/nfs_mount_conf_shared"

INITSCRIPT_NAME:${PN}-mountgw       = "nfs_mount_conf_users"
INITSCRIPT_PARAMS:${PN}-mountgw     = "defaults 82 18"
FILES:${PN}-mountgw                 += "${sysconfdir}/init.d/nfs_mount_conf_users"
FILES:${PN}-mountgw                 += "/iotlab/users"

# start AFTER nfs mount (check eth0 ip to mount nfs directory)

INITSCRIPT_NAME:${PN}-networkgw       = "gateway-server-network"
INITSCRIPT_PARAMS:${PN}-networkgw     = "defaults 83 17"
FILES:${PN}-networkgw                 += "${sysconfdir}/init.d/gateway-server-network"

INITSCRIPT_NAME:${PN}-ipv6       = "networking_ipv6"
INITSCRIPT_PARAMS:${PN}-ipv6     = "defaults 84 16"
FILES:${PN}-ipv6                 += "${sysconfdir}/init.d/networking_ipv6"
FILES:${PN}-ipv6                 += "${sysconfdir}/profile.d/ipv6.sh"

# don't start by default by update-rc.d
RDEPENDS:${PN}-serial              += "gateway-code"
FILES:${PN}-serial                 = "${sysconfdir}/init.d/serial_redirection"

# don't try to check elf files
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_SYSROOT_STRIP = "1"
INSANE_SKIP:${PN}-flashidle += "arch"
INSANE_SKIP:${PN}-flashidle-dbg += "arch"

RDEPENDS:${PN}-flashidle += "gateway-code"
INITSCRIPT_NAME:${PN}-flashidle = "flash_idle"
INITSCRIPT_PARAMS:${PN}-flashidle = "start 85 S ."
FILES:${PN}-flashidle     += "${sysconfdir}/init.d/flash_idle"

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

    # mount linux node
    install -m 0755 ${S}/nfs_mount_conf_shared             ${D}/${sysconfdir}/init.d/

    # gateway-server network (virtual ip for open nodes)
    install -m 0755 ${S}/gateway-server-network            ${D}/${sysconfdir}/init.d/

    # serial redirection
    install -m 0755 ${S}/serial_redirection                ${D}/${sysconfdir}/init.d/
}

pkg_postinst:${PN}-mountnode() {
  install -m 0755 -d $D${ROOT_HOME}/shared
  # add A8 symlink to shared directory (eg. A8 Open node backward compatibility)
  ln -s -r $D${ROOT_HOME}/shared $D${ROOT_HOME}/A8
}

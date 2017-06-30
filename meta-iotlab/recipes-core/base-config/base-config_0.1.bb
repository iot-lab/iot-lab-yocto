DESCRIPTION = "Basic configuration for iotlab images"
LICENSE = "CECILL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CECILL-2.0;md5=574109ac4bdff61f9c3e0de892ecbd19"

S = "${WORKDIR}"

SRC_URI += "file://hosts"
SRC_URI += "file://hostname"
SRC_URI += "file://sysctl.conf"
SRC_URI += "file://inputrc"
SRC_URI += "file://bashrc"
SRC_URI += "file://profile"

do_install () {
  # create local conf directory
  install -d                                     ${D}${localstatedir}/local/config
  install -m 0644 ${S}/hostname                  ${D}${localstatedir}/local/config/

  # set hosts default file
  #     didn't manage to dynamically copy original 'netbase' file
  install -d                                     ${D}${sysconfdir}/default
  install -m 0644 ${S}/hosts                     ${D}${sysconfdir}/default/
  # Sysctl for IPv6, no autoconf by default
  install -m 0644 ${S}/sysctl.conf               ${D}${sysconfdir}/

  # hostame link
  ln -s ${localstatedir}/local/config/hostname   ${D}${sysconfdir}/hostname

  install -d                                     ${D}${sysconfdir}/iotlab-config/
  install -m 0755 ${S}/bashrc                    ${D}${sysconfdir}/iotlab-config/bashrc
  install -m 0755 ${S}/profile                   ${D}${sysconfdir}/iotlab-config/profile
  install -m 0600 ${S}/inputrc                   ${D}${sysconfdir}/iotlab-config/inputrc
}

pkg_postinst_${PN} () {
  install -m 0600 $D${sysconfdir}/iotlab-config/bashrc             $D${ROOT_HOME}/.bashrc
  install -m 0600 $D${sysconfdir}/iotlab-config/profile            $D${ROOT_HOME}/.profile
  install -m 0600 $D${sysconfdir}/iotlab-config/inputrc            $D${ROOT_HOME}/.inputrc
}

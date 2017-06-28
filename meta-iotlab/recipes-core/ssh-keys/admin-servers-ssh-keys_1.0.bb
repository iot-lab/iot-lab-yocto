DESCRIPTION = "Add admin and servers public ssh keys in authorized_keys"
LICENSE = "CECILL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CECILL-2.0;md5=574109ac4bdff61f9c3e0de892ecbd19"

S = "${WORKDIR}"

# files can be overwritten
SRC_URI += "git://github.com/iot-lab/iot-lab-dev.git;user=git;protocol=ssh;branch=master"
SRCREV = "${AUTOREV}"

do_compile() {
  cp ${S}/git/install_lib/template/sshkeys/admin_authorized_keys     ${S}
  cp ${S}/git/install_lib/template/sshkeys/servers_authorized_keys   ${S}
}

do_install () {
  install -d                                     ${D}${sysconfdir}/iotlab-config/
  install -m 0600 ${S}/admin_authorized_keys     ${D}${sysconfdir}/iotlab-config/authorized_keys
  cat             ${S}/servers_authorized_keys >>${D}${sysconfdir}/iotlab-config/authorized_keys
}

pkg_postinst_${PN} () {
  install -m 0700 -d                                                $D${ROOT_HOME}/.ssh
  install -m 0600 $D${sysconfdir}/iotlab-config/authorized_keys     $D${ROOT_HOME}/.ssh/
}

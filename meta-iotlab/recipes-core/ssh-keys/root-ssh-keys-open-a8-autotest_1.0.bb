DESCRIPTION = "Private ssh keys configuration used for open a8 autotest"
LICENSE = "CECILL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CECILL-2.0;md5=574109ac4bdff61f9c3e0de892ecbd19"

PR = "r1"

# provide "install" command at postinst
RDEPENDS_${PN} += "coreutils"

S = "${WORKDIR}"

SRC_URI = "git://github.com/iot-lab/iot-lab-dev.git;user=git;protocol=ssh;branch=master"
SRCREV = "${AUTOREV}"

do_install() {
  install -d                                              ${D}${sysconfdir}/default/
  install ${S}/git/install_lib/template/nodes/id_rsa.pub  ${D}${sysconfdir}/default/gw_id_rsa.pub
}

pkg_postinst_${PN} () {
  install -m 0600 $D${sysconfdir}/default/gw_id_rsa.pub $D${ROOT_HOME}/.ssh/authorized_keys
}

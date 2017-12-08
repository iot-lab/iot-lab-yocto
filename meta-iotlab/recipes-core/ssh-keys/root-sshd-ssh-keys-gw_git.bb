DESCRIPTION = "Private ssh keys configuration used for gateway"
LICENSE = "CECILL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CECILL-2.0;md5=574109ac4bdff61f9c3e0de892ecbd19"

PR = "r1"

DEPENDS += "python-native"
DEPENDS += "python-pycrypto-native"
# provide "install" command at postinst
RDEPENDS_${PN} += "coreutils"

S = "${WORKDIR}"

PV = "git-src${SRCDATE}-r${SRCPV}"

SRC_URI = "git://github.com/iot-lab/iot-lab-dev.git;user=git;protocol=ssh;branch=master"
SRCREV = "${AUTOREV}"


PYTHON = "${STAGING_BINDIR_NATIVE}/python-native/python"

# Decrypt encrypted keys
do_compile() {
  ${PYTHON} ${S}/git/lock_file.py -d ${S}/git/install_lib/template/nodes/id_rsa.enc
  cp ${S}/git/install_lib/template/nodes/id_rsa*  ${S}
  ${PYTHON} ${S}/git/lock_file.py -d ${S}/git/install_lib/template/nodes/ssh_host_keys.tar.gz.enc
  tar xzvf ${S}/git/install_lib/template/nodes/ssh_host_keys.tar.gz
}

do_install() {
  # ssh keys for openssh server
  install -d                                     ${D}${sysconfdir}/ssh
  install -m 0600 ${S}/ssh_host_keys/*           ${D}${sysconfdir}/ssh/

  # create www-data home folder and .ssh folder
  # used to access A8 open nodes
  install         -o www-data -g www-data -d               ${D}${localstatedir}/www
  install -m 0755 -o www-data -g www-data -d               ${D}${localstatedir}/www/.ssh
  install -m 0600 -o www-data -g www-data ${S}/id_rsa      ${D}${localstatedir}/www/.ssh/
  install -m 0644 -o www-data -g www-data ${S}/id_rsa.pub  ${D}${localstatedir}/www/.ssh/
}

pkg_postinst_${PN} () {
  install -m 0700 -d                                       $D${ROOT_HOME}/.ssh
  install -m 0600 $D${localstatedir}/www/.ssh/id_rsa       $D${ROOT_HOME}/.ssh/
}


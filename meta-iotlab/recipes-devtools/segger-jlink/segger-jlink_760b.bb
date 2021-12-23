DESCRIPTION = "SEGGER J-Link Software and Documentation pack"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "\
                    file://Doc/LicenseIncGUI.txt;md5=e4b273c1048dd2c3afe701297ce62697 \
                   "
S = "${WORKDIR}/JLink_Linux_V${PV}_arm"
PR = "r0"

SRC_URI="https://www.segger.com/downloads/jlink/JLink_Linux_V${PV}_arm.tgz"
BODY_DATA='accept_license_agreement=accepted&non_emb_ctr=confirmed&submit="Download software"'

do_fetch () {
 /usr/bin/wget --post-data '${BODY_DATA}' ${SRC_URI} -O ${DL_DIR}/JLink_Linux_V${PV}_arm.tgz
}

inherit relative_symlinks

do_install () {
  install -d ${D}/etc/udev/rules.d
  install -m 755 ${S}/99-jlink.rules  ${D}/etc/udev/rules.d/
 
  install -d ${D}/usr/local/share/JLink
  install -m 755 ${S}/JLinkExe 		${D}/usr/local/share/JLink/JLinkExe
  install -m 755 ${S}/JLinkGDBServer 	${D}/usr/local/share/JLink/JLinkGDBServer
  install -m 755 ${S}/libjlinkarm.so.7.60.2		${D}/usr/local/share/JLink/libjlinkarm.so.7.60.2
  lnr ${D}/usr/local/share/JLink/libjlinkarm.so.7.60.2 	${D}/usr/local/share/JLink/libjlinkarm.so.7
  lnr ${D}/usr/local/share/JLink/libjlinkarm.so.7 	${D}/usr/local/share/JLink/libjlinkarm.so
}

INSANE_SKIP_${PN} = "already-stripped ldflags dev-so"

FILES_${PN} += "/usr/local/share/JLink/JLinkExe "
FILES_${PN} += "/usr/local/share/JLink/JLinkGDBServer "
FILES_${PN} += "/usr/local/share/JLink/libjlinkarm.so.7.60.2 "
FILES_${PN} += "/usr/local/share/JLink/libjlinkarm.so.7 "
FILES_${PN} += "/usr/local/share/JLink/libjlinkarm.so "

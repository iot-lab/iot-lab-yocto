DESCRIPTION = "SEGGER J-Link Software and Documentation pack"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "\
                    file://Doc/LicenseIncGUI.txt;md5=e4b273c1048dd2c3afe701297ce62697 \
                   "
# SEGGER ships separate packs for 32-bit ARM (rpi2/3/4) and AArch64 (rpi5)
JLINK_ARCH = "${@bb.utils.contains('TUNE_FEATURES', 'aarch64', 'arm64', 'arm', d)}"

S = "${UNPACKDIR}/JLink_Linux_V${PV}_${JLINK_ARCH}"
PR = "r0"

SRC_URI="https://www.segger.com/downloads/jlink/JLink_Linux_V${PV}_${JLINK_ARCH}.tgz"
BODY_DATA='accept_license_agreement=accepted&non_emb_ctr=confirmed&submit="Download software"'

do_fetch () {
 /usr/bin/wget --post-data '${BODY_DATA}' ${SRC_URI} -O ${DL_DIR}/JLink_Linux_V${PV}_${JLINK_ARCH}.tgz
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit relative_symlinks

do_install () {
  install -d ${D}/etc/udev/rules.d
  install -m 755 ${S}/99-jlink.rules  ${D}/etc/udev/rules.d/
 
  install -d ${D}/usr/local/share/JLink
  install -m 755 ${S}/JLinkExe 		${D}/usr/local/share/JLink/JLinkExe
  install -m 755 ${S}/JLinkGDBServer 	${D}/usr/local/share/JLink/JLinkGDBServer
  install -m 755 ${S}/libjlinkarm.so.7.60.2		${D}/usr/local/share/JLink/libjlinkarm.so.7.60.2
  ln -sf ${D}/usr/local/share/JLink/libjlinkarm.so.7.60.2 	${D}/usr/local/share/JLink/libjlinkarm.so.7
  ln -sf ${D}/usr/local/share/JLink/libjlinkarm.so.7 	${D}/usr/local/share/JLink/libjlinkarm.so
}

# file-rdeps/libdir: prebuilt vendor binaries installed outside the
# standard libdir, so the usual shlib/rdeps auto-detection doesn't apply.
INSANE_SKIP:${PN} = "already-stripped ldflags dev-so file-rdeps libdir"

FILES:${PN} += "/usr/local/share/JLink/JLinkExe "
FILES:${PN} += "/usr/local/share/JLink/JLinkGDBServer "
FILES:${PN} += "/usr/local/share/JLink/libjlinkarm.so.7.60.2 "
FILES:${PN} += "/usr/local/share/JLink/libjlinkarm.so.7 "
FILES:${PN} += "/usr/local/share/JLink/libjlinkarm.so "

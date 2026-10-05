DESCRIPTION = "IoT-LAB open A8 Linux node openocd scripts"
LICENSE = "CECILL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CECILL-2.0;md5=574109ac4bdff61f9c3e0de892ecbd19"

S = "${WORKDIR}"

PR = "r1"

# package to ensure compatibility with old A8 open node image. Indeed now these scripts use generic programmer scripts on the gateway code

RDEPENDS_${PN}   = "openocd"
RDEPENDS_${PN}   += "bash"
FILES_${PN}  = "${bindir}/flash_a8_m3"
FILES_${PN}  += "${bindir}/reset_a8_m3"
FILES_${PN}  += "${bindir}/debug_a8_m3"

SRC_URI = "\
        file://flash_a8_m3 \
        file://reset_a8_m3 \
        file://debug_a8_m3 \
        "

do_install () {
  install -d ${D}${bindir}
  install -m 755 ${S}/flash_a8_m3  ${D}${bindir}/
  install -m 755 ${S}/reset_a8_m3  ${D}${bindir}/
  install -m 755 ${S}/debug_a8_m3  ${D}${bindir}/
}

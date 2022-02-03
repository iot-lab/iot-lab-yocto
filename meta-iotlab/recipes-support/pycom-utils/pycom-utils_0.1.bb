DESCRIPTION = "Pycom boards utils"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "\
                    file://esptool.py;beginline=3;endline=17;md5=4fe73a184ae6e27d0205eb5cf10f31c6 \
                    file://pypic.py;beginline=3;endline=8;md5=bc5f48a766e0c608b725ffff65d5272d \
                    file://updater.py;beginline=3;endline=8;md5=bc5f48a766e0c608b725ffff65d5272d \
                   "

S = "${WORKDIR}"

PR = "r1"

RDEPENDS_${PN} += "python3 python3-pyserial"

SRC_URI = "\
        file://__init__.py \
        file://esptool.py \
        file://pypic.py \
        file://updater.py \
        "

do_install () {
  install -d ${D}/usr/local/share/pycom/eps32/tools/fw_updater
  install -m 755 ${S}/__init__.py  ${D}/usr/local/share/pycom/eps32/tools/fw_updater/
  install -m 755 ${S}/esptool.py   ${D}/usr/local/share/pycom/eps32/tools/fw_updater/
  install -m 755 ${S}/pypic.py     ${D}/usr/local/share/pycom/eps32/tools/fw_updater/
  install -m 755 ${S}/updater.py   ${D}/usr/local/share/pycom/eps32/tools/fw_updater/
}

FILES_${PN} += "/usr/local/share/pycom/eps32/tools/fw_updater/*.py"

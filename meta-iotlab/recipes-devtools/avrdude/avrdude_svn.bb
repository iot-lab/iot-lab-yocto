SUMMARY = "AVRDUDE is a utility to download/upload/manipulate the ROM and EEPROM contents of AVR microcontrollers using the in-system programming technique (ISP)."

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=4f51bb496ef8872ccff73f440f2464a8"

DEPENDS = "bison-native libusb elfutils hidapi libftdi readline"

inherit autotools

PV = "6.3+svn${SRCPV}"
SRC_URI = "svn://svn.savannah.nongnu.org/svn/avrdude/;protocol=http;module=trunk;rev=1425"

S = "${WORKDIR}/trunk/avrdude"

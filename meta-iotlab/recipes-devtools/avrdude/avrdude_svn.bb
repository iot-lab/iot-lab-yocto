SUMMARY = "AVRDUDE is a utility to download/upload/manipulate the ROM and EEPROM contents of AVR microcontrollers using the in-system programming technique (ISP)."

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4f51bb496ef8872ccff73f440f2464a8"

DEPENDS = "bison-native libusb elfutils hidapi libftdi readline"

inherit autotools

PV = "6.3+svn${SRCPV}"
# svn.savannah.nongnu.org no longer serves SVN over HTTP/WebDAV (port 80 is
# closed); use the native svn:// protocol instead. That protocol also drops
# the "/svn/" prefix that only exists in the WebDAV URL layout.
SRC_URI = "svn://svn.savannah.nongnu.org/avrdude/;protocol=svn;module=trunk;rev=1425"

S = "${UNPACKDIR}/trunk/avrdude"

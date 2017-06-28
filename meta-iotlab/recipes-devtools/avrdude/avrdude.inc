DESCRIPTION = "AVRDUDE is software for programming Atmel AVR Microcontrollers."
HOMEPAGE = "http://www.nongnu.org/avrdude/"
SECTION = "console"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

PR = "r1"

DEPENDS = "virtual/libusb0 ncurses elfutils readline libftdi"

SRC_URI = "http://savannah.nongnu.org/download/avrdude/avrdude-${PV}.tar.gz"

inherit autotools


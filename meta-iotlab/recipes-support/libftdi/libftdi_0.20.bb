DESCRIPTION = "libftdi is a library to talk to FTDI chips.\
FT232BM/245BM, FT2232C/D and FT232/245R using libusb,\
including the popular bitbang mode."
HOMEPAGE = "http://www.intra2net.com/en/developer/libftdi/"
SECTION = "libs"

PR = "r1"

LICENSE = "LGPLv2.1 & GPLv2"
LIC_FILES_CHKSUM= "file://COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe \
                   file://COPYING.LIB;md5=db979804f025cf55aabec7129cb671ed \
"

DEPENDS = "virtual/libusb0"
DEPENDS_virtclass-native = "virtual/libusb0-native"

SRC_URI = "http://www.intra2net.com/en/developer/libftdi/download/libftdi-${PV}.tar.gz \
           file://libtool-m4.patch \
"

SRC_URI[md5sum] = "355d4474e3faa81b485d6a604b06951f"
SRC_URI[sha256sum] = "3176d5b5986438f33f5208e690a8bfe90941be501cc0a72118ce3d338d4b838e"

PACKAGECONFIG ??= ""
PACKAGECONFIG[cpp-wrapper] = "--enable-libftdipp,--disable-libftdipp,boost"

inherit autotools binconfig pkgconfig

BBCLASSEXTEND = "native"


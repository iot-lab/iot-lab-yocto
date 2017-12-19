SUMMARY = "Jansson is a C library for encoding, decoding and manipulating JSON data"
HOMEPAGE = "http://www.digip.org/jansson/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8b70213ec164c7bd876ec2120ba52f61"

SRC_URI = "http://www.digip.org/jansson/releases/${PN}-${PV}.tar.gz"

SRC_URI[md5sum] = "16a2c4e84c0a80ca61bd6e619a0f9358"
SRC_URI[sha256sum] = "78215ad1e277b42681404c1d66870097a50eb084be9d771b1d15576575cf6447"

inherit autotools pkgconfig


SUMMARY = "An implementation of RFC 7049 - Concise Binary Object Representation (CBOR)"
HOMEPAGE = "https://github.com/brianolson/cbor_py"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c6918f25f2dd5b9f5079ca26154314e0"

inherit setuptools

PV = "git-src${SRCDATE}-r${SRCPV}"

SRC_URI = "git://github.com/brianolson/cbor_py.git;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/"

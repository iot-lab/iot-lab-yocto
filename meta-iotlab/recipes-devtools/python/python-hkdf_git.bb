SUMMARY = "HMAC-based Extract-and-Expand Key Derivation Function (HKDF)"
HOMEPAGE = "https://github.com/casebeer/python-hkdf"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f553aec9729c2f78a3dcc38d78a3cc80"

inherit setuptools

PV = "git-src${SRCDATE}-r${SRCPV}"

SRC_URI = "git://github.com/casebeer/python-hkdf.git;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/"

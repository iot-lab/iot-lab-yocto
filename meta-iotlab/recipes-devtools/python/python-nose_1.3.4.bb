DESCRIPTION = "nose extends the test loading and running features of unittest, \
making it easier to write, find and run tests."
SECTION = "devel/python"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://lgpl.txt;md5=a6f89e2100d9b6cdffcea4f398e37343"

SRCNAME = "nose"
SRC_URI = "https://pypi.python.org/packages/source/${@SRCNAME[0]}/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "6ed7169887580ddc9a8e16048d38274d"
SRC_URI[sha256sum] = "76bc63a4e2d5e5a0df77ca7d18f0f56e2c46cfb62b71103ba92a92c79fab1e03"

S = "${WORKDIR}/nose-${PV}"

inherit setuptools

BBCLASSEXTEND = "native nativesdk"

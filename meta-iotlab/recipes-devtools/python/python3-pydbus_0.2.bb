## -*-conf-*-
DESCRIPTION = "Pythonic DBus library"
HOMEPAGE = "https://pypi.python.org/pypi/pydbus/"
SECTION = "devel/python"
LICENSE = "LGPL2.1"
LIC_FILES_CHKSUM = "file://README.rst;md5=ee614c3c5f10528cae319f22e83ca509"

PR = "r0"
SRCNAME = "pydbus"

SRC_URI = "https://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "d0e8806c80017de9178bddf49cfca35b"
SRC_URI[sha256sum] = "5a02b07e03d97e3bf2a388c34659ceee6603c57038de4d024b46915ce13dc2a8"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools3

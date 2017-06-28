## -*-conf-*-
DESCRIPTION = "Colored terminal output for Python's logging module"
HOMEPAGE = "https://pypi.python.org/pypi/coloredlogs/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=838993c671c3d6af09496c281ef29ffb"

PR = "r0"
SRCNAME = "coloredlogs"

SRC_URI = "https://pypi.python.org/packages/source/c/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "229fcd7a4a33696bbd3e77b23022518b"
SRC_URI[sha256sum] = "d3efaaaa1ae85c3a1af335f116f634832691873dd87e5872b94bee1dd0a1e43a"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools3

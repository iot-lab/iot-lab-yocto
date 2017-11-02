DESCRIPTION = "A backport of the subprocess module from Python 3.2/3.3 for use on 2.x."
SECTION = "devel/python"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d3e62baa8fb96b11a59d3f2cd335d5c0"

SRCNAME = "subprocess32"
# python-modules used for simplicity
RDEPENDS_${PN} = 'python-modules'

SRC_URI = "https://pypi.python.org/packages/source/${@SRCNAME[0]}/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "824c801e479d3e916879aae3e9c15e16"
SRC_URI[sha256sum] = "1e450a4a4c53bf197ad6402c564b9f7a53539385918ef8f12bdf430a61036590"

S = "${WORKDIR}/${SRCNAME}-${PV}"
inherit distutils

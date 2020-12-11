## -*-conf-*-
DESCRIPTION = "RESTful HTTP Content Negotiation for Flask, Bottle, web.py and webapp2 (Google App Engine)"
HOMEPAGE = "https://pypi.python.org/pypi/mimerender/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=8e5f1d597035f340ff27106d015fc1b3"

PR = "r0"
SRCNAME = "mimerender"
SRC_URI = "https://pypi.python.org/packages/source/m/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "1b59f8a51c56fe7c11272570b54fb4fb"
SRC_URI[sha256sum] = "387dcf22cfcbad8c72f2b0def6659c9fc720234a1a74ad485618b24e6779bc28"

RDEPENDS_${PN} = "python python-mimeparse"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools3

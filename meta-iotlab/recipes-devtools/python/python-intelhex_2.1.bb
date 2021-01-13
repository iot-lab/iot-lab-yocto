UMMARY = "Python library for Intel HEX files manipulations"
HOMEPAGE = "https://github.com/bialix/intelhex"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=1e431dc8a128348f8b7d3f1f6deb1236"

inherit pypi setuptools

SRC_URI[md5sum] = "d035c4d747db128304535515c0f4dbb6"
SRC_URI[sha256sum] = "7c1e136efe97672dcf90feed18fc291977d4f5eccf13d124583bec376c0db44c"

do_install_append() {
    # python3-intelhex recipe clash
    rm -f ${D}${bindir}/bin2hex.py
    rm -f ${D}${bindir}/hex2dump.py
    rm -f ${D}${bindir}/hex2bin.py
    rm -f ${D}${bindir}/hexdiff.py
    rm -f ${D}${bindir}/hexinfo.py
    rm -f ${D}${bindir}/hexmerge.py
    rmdir ${D}${bindir}
}

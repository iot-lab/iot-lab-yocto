DESCRIPTION = "virtualenv-based automation of test activities"
HOMEPAGE = "http://tox.testrun.org"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2d0fc2c2c954dc4d41043e67d4a8d8e7"


SRCNAME = "tox"
SRC_URI = "http://pypi.python.org/packages/source/t/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "f4db4d6a82d6a651e457ba55ef370258"
SRC_URI[sha256sum] = "3125a15da7381f59f835a25bd259a84acb7a69858c81e386c50027bd842bb91b"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

DEPENDS += " \
        python-pip \
        "

RDEPENDS_${PN} += " \
        python-virtualenv \
        python-py \
        python-pytest \
        "

do_install_append() {

    install -d ${D}/${sysconfdir}/${SRCNAME}
    cp ${S}/tox.ini    ${D}/${sysconfdir}/${SRCNAME}
    cp ${S}/setup.py   ${D}/${sysconfdir}/${SRCNAME}
    cp ${S}/README.rst ${D}/${sysconfdir}/${SRCNAME}

    ln -s ${PYTHON_SITEPACKAGES_DIR}/tox ${D}/${sysconfdir}/${SRCNAME}/tox
}

FILES_${PN} += "${sysconfdir}/${SRCNAME}/*"

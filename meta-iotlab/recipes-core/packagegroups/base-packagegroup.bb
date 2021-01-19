DESCRIPTION = "default package group list for images"
LICENSE = "GPLv2"

inherit packagegroup

RDEPENDS_${PN} += " \
    avarice \
    avrdude \
    base-files \
    base-config \
    initrdscripts-settime \
    initrdscripts-volatile \
    openocd \
    openocd-git \
    openocd-ti \
    python-msp430-bsl \
    python-cc2538-bsl \
    python-pyocd \
    python-msp430-bsl \
    python3-pyocd \
    edbg \
    "

# Python3 gateway code dependencies
RDEPENDS_${PN} += " \
    python3 \
    python3-pyserial \
    python3-bottle \
    python3-paste \
    python3-pyelftools \
    python3-gcovr \
    python3-coverage \
    python3-mock \
    python3-pep8 \
    python3-pylint \
    python3-tox \
    python3-testfixtures \
    python3-webtest \
    python3-codecov \
    "

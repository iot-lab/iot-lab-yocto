DESCRIPTION = "A script that communicates with the boot loader of the TI CC2538 and other SoCs (System on Chips)."
HOMEPAGE = "https://github.com/JelmerT/cc2538-bsl"
SECTION = "console"
LICENSE = ""
LIC_FILES_CHKSUM = ""

PR = "r1"

DEPENDS = "gcc-arm-none-eabi"

SRC_URI = "git://github.com/JelmerT/cc2538-bsl.git;protocol=https"

inherit autotools
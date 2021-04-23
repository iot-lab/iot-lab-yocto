DESCRIPTION = "Linux Kernel for var-som-am35 (TI AM3505) board"
SECTION = "kernel"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

COMPATIBLE_MACHINE = "var-som-am35"

PV = "${LINUX_VERSION}+git${SRCPV}"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

BRANCH = "linux-5.4.y"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;nocheckout=1;branch=${BRANCH};name=machine"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://0001-var-som-am35-add-device-tree.patch"
SRC_URI += "file://0002-davinci_emac-allow-setting-the-mac-address-with-an-e.patch"
SRC_URI += "file://defconfig"

KCONFIG_MODE = "--alldefconfig"

LINUX_VERSION ?= "5.4.114"
LINUX_VERSION_EXTENSION_append = "-clochette"

# Modify SRCREV to a different commit hash in a copy of this recipe to
# build a different release of the Linux kernel.
# tag: v5.4.114	de968c0c35d0cb90449f6984fab027879f9d7ceb
SRCREV_machine="de968c0c35d0cb90449f6984fab027879f9d7ceb"

DEPENDS += "u-boot-mkimage-native"

do_deploy_append() {
    uboot-mkimage -A arm -O linux -T kernel -C none -a 80008000 -e 80008000 -n "${DISTRO_NAME}/${PV}/${MACHINE}" -d ${DEPLOYDIR}/${KERNEL_IMAGETYPE}-${MACHINE}-${MACHINE}.dtb.bin ${DEPLOYDIR}/uImage-${KERNEL_IMAGE_NAME}.bin
    ln -sf uImage-${KERNEL_IMAGE_NAME}.bin ${DEPLOYDIR}/uImage-${MACHINE}.bin
    ln -sf uImage-${KERNEL_IMAGE_NAME}.bin ${DEPLOYDIR}/uImage
}


SUMMARY = "Linux Kernel for var-som-am35 (TI AM3505) board"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

# Also matches the var-som-am35-mtd-rw machine
COMPATIBLE_MACHINE = "var-som-am35"

inherit kernel

LINUX_VERSION = "6.12.112"
PV = "${LINUX_VERSION}+git"

# tag: v6.12.112
SRCREV = "7aba70ab2e8d8ab3cd45abcaa15e1119a00dc42a"

FILESEXTRAPATHS:prepend := "${THISDIR}/linux-var-som-am35:"

SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;protocol=https;branch=linux-6.12.y \
    file://0001-var-som-am35-add-device-tree.patch \
    file://0002-davinci_emac-allow-setting-the-mac-address-with-an-e.patch \
    file://defconfig \
    "

S = "${WORKDIR}/git"

KERNEL_LOCALVERSION = "-clochette"

DEPENDS += "u-boot-tools-native"

# The gateway U-Boot loads a uImage containing the zImage with the device
# tree appended (KERNEL_DEVICETREE_BUNDLE in the machine configuration).
do_deploy:append() {
    uboot-mkimage -A arm -O linux -T kernel -C none -a 80008000 -e 80008000 \
        -n "${DISTRO_NAME}/${PV}/${MACHINE}" \
        -d ${DEPLOYDIR}/${KERNEL_IMAGETYPE}-${MACHINE}.dtb${KERNEL_DTB_BIN_EXT} \
        ${DEPLOYDIR}/uImage-${KERNEL_IMAGE_NAME}${KERNEL_IMAGE_BIN_EXT}
    ln -sf uImage-${KERNEL_IMAGE_NAME}${KERNEL_IMAGE_BIN_EXT} ${DEPLOYDIR}/uImage-${MACHINE}${KERNEL_IMAGE_BIN_EXT}
    ln -sf uImage-${KERNEL_IMAGE_NAME}${KERNEL_IMAGE_BIN_EXT} ${DEPLOYDIR}/uImage
}

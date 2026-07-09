DESCRIPTION = "U-Boot netboot script: dhcp -> tftp kernel+dtb -> nfsroot"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# rpi2/rpi3/rpi4 use 32-bit U-Boot (bootz/zImage), rpi5 uses 64-bit (booti/Image).
# UBOOT_ARCH is resolved per-machine automatically by kernel-arch.bbclass.
COMPATIBLE_MACHINE = "raspberrypi2|raspberrypi3|raspberrypi4|raspberrypi5"

DEPENDS = "u-boot-mkimage-native"
INHIBIT_DEFAULT_DEPS = "1"

SRC_URI = "file://boot.source.${MACHINE}"
# raspberrypi3 covers both the 3B and 3B+ hardware revisions in Yocto, but
# they need different device trees over TFTP, so we build both variants.
SRC_URI:append:raspberrypi3 = " file://boot.source.raspberrypi3-plus"

inherit kernel-arch deploy nopackages

do_compile() {
    mkimage -A ${UBOOT_ARCH} -O linux -T script -C none -n "uboot-netboot script" \
        -d "${UNPACKDIR}/boot.source.${MACHINE}" boot.scr
}

do_compile:append:raspberrypi3() {
    mkimage -A ${UBOOT_ARCH} -O linux -T script -C none -n "uboot-netboot script (3B+)" \
        -d "${UNPACKDIR}/boot.source.raspberrypi3-plus" boot.scr-3bplus
}

do_deploy() {
    install -d ${DEPLOYDIR}
    # named boot.scr-netboot to avoid clashing with rpi-u-boot-scr's own
    # local-media boot.scr when RPI_USE_U_BOOT is also enabled
    install -m 0644 boot.scr ${DEPLOYDIR}/boot.scr-netboot
}

do_deploy:append:raspberrypi3() {
    install -m 0644 boot.scr-3bplus ${DEPLOYDIR}/boot.scr-netboot-3bplus
}

addtask deploy after do_compile before do_build

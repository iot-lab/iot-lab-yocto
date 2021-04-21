KMACHINE_var-som-am35 ?= "var-som-am35"

COMPATIBLE_MACHINE_var-som-am35 ?= "var-som-am35"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://0001-var-som-am35-add-device-tree.patch"
SRC_URI += "file://0002-davinci_emac-allow-setting-the-mac-address-with-an-e.patch"
SRC_URI += "file://defconfig"

DEPENDS += "u-boot-mkimage-native"

do_deploy_append() {
    uboot-mkimage -A arm -O linux -T kernel -C none -a 80008000 -e 80008000 -n "${DISTRO_NAME}/${PV}/${MACHINE}" -d ${DEPLOYDIR}/zImage-var-som-am35-var-som-am35.dtb.bin ${DEPLOYDIR}/uImage
}

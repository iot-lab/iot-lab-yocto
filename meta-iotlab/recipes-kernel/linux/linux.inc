## -*-conf-*-
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

# Override SRC_URI in a copy of this recipe to point at a different source
# tree if you do not want to build from Linus' tree.
SRC_URI = "git://github.com/iot-lab/iot-lab-linux.git;user=git;protocol=ssh;branch=3.19.0"
#SRC_URI = "git:///path/iot-lab-linux;protocol=file;branch=3.19.0"

LINUX_VERSION ?= "3.19.0"

# Modify SRCREV to a different commit hash in a copy of this recipe to
# build a different release of the Linux kernel.
# SRCREV = "0dfec072568b7ee137f8509b7bd80d20149468be"
SRCREV = "${AUTOREV}"
# Add 'SRCDATE' for AUTOREV to get commit date in version
# => This apparently results in using the date of the current day,
#    not that of the commit date, causing a rebuild of the package
#    even if the source has not changed.
#PV = "${LINUX_VERSION}-src${SRCDATE}+git${SRCPV}"

PV = "${LINUX_VERSION}+git${SRCPV}"

# needed to use in-tree defconfigs (see linux-yocto-custom.bb)
KCONFIG_MODE="--alldefconfig"

# Append device tree dtb file in uImage file
do_deploy_append() {
        cat ${DEPLOYDIR}/${KERNEL_IMAGE_BASE_NAME}.bin ${DEPLOYDIR}/${DTB_NAME}.dtb > ${DEPLOYDIR}/${KERNEL_IMAGE_BASE_NAME}_with_dtb.bin
        ln -sf ${KERNEL_IMAGE_BASE_NAME}_with_dtb.bin ${DEPLOYDIR}/${KERNEL_IMAGETYPE}_with_dtb
}

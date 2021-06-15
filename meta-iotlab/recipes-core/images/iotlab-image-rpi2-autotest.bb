include iotlab-image.inc

EXTRA_IMAGE_FEATURES += "debug-tweaks"

# Include modules in rootfs
IMAGE_INSTALL += " \
		kernel-modules \
		linux-node-packagegroup \
		linux-firmware-bcm43430 \
    		linux-firmware-rtl8192cu \
		"

include iotlab-image.inc

EXTRA_IMAGE_FEATURES += "debug-tweaks"

# Include modules in rootfs
IMAGE_INSTALL += " \
		kernel-modules \
		linux-node-packagegroup \ 
		"

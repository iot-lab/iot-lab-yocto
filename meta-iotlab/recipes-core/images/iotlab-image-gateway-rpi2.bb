DESCRIPTION = "IoT-LAB image for Raspberry Pi 2 Gateway"
include iotlab-image.inc

# Include modules in rootfs
IMAGE_INSTALL += " \
    kernel-modules \
    gateway-packagegroup \
    mjpg-streamer \
    sudo \
	"

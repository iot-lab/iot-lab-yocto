DESCRIPTION = "IoT-LAB image for RPI5 gateways"
include iotlab-image.inc

# Include modules in rootfs
IMAGE_INSTALL += " \
    kernel-modules \
    gateway-packagegroup \
    mjpg-streamer \
    sudo \
    rtl-sdr \
    ykush \
    "

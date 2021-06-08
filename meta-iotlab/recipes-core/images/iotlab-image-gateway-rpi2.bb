DESCRIPTION = "IoT-LAB image for RPI2 gateway"
include iotlab-image.inc

# Include modules in rootfs
IMAGE_INSTALL += " \
    kernel-modules \
    gateway-packagegroup \
    mjpg-streamer \
    sudo \
    rtl-sdr \
    ykush \
    linux-firmware-bcm43430 \
    linux-firmware-rtl8192cu \
    lldpd \
    "

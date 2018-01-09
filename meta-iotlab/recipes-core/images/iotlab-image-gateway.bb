DESCRIPTION = "IoT-LAB image for Gateways"
include iotlab-image.inc

IMAGE_INSTALL += " \
        gateway-packagegroup \
        u-boot-fw-utils \
        "

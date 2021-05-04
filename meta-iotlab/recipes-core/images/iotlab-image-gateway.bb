DESCRIPTION = "IoT-LAB image for Gateways"
include iotlab-image.inc

IMAGE_INSTALL += " \
        libubootenv \
        gateway-packagegroup \
        "

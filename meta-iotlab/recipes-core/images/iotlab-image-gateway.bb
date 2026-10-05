DESCRIPTION = "IoT-LAB image for var-som-am35 (A8) gateways"
include iotlab-image.inc

COMPATIBLE_MACHINE = "var-som-am35"

IMAGE_INSTALL += " \
        libubootenv \
        gateway-packagegroup \
        "

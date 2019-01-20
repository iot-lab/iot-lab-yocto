DESCRIPTION = "IoT-LAB image for Open A8 used by users"
include iotlab-image.inc

IMAGE_INSTALL += " \
        open-a8-packagegroup \
        u-boot-fw-utils \
        flash-scripts-open-a8 \
        "


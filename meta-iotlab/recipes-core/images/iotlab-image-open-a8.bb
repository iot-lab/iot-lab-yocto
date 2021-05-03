DESCRIPTION = "IoT-LAB image for Open A8 used by users"
include iotlab-image.inc

IMAGE_INSTALL += " \
        kernel-modules \
        kernel-module-jool \
        linux-node-packagegroup \
        u-boot-fw-utils \
        flash-scripts-open-a8 \
        "

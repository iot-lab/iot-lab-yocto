DESCRIPTION = "IoT-LAB image for Open A8 used by users"
include iotlab-image.inc

IMAGE_INSTALL += " \
        linux-node-packagegroup \
        flash-scripts-open-a8 \
        "

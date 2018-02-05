DESCRIPTION = "IoT-LAB image for Open A8 autotest"
include iotlab-image.inc

EXTRA_IMAGE_FEATURES += "debug-tweaks"

IMAGE_INSTALL += "open-a8-packagegroup"

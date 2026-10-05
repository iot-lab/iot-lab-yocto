DESCRIPTION = "IoT-LAB image for Open A8 autotest"
include iotlab-image.inc

COMPATIBLE_MACHINE = "var-som-am35"

EXTRA_IMAGE_FEATURES += "debug-tweaks"

IMAGE_INSTALL += "linux-node-packagegroup"

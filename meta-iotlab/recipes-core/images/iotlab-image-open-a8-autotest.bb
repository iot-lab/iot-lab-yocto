DESCRIPTION = "IoT-LAB image for Open A8 autotest"
include iotlab-image.inc

COMPATIBLE_MACHINE = "var-som-am35"

# debug-tweaks was split in walnascar: root login without password
EXTRA_IMAGE_FEATURES += "allow-empty-password allow-root-login empty-root-password post-install-logging"

IMAGE_INSTALL += "linux-node-packagegroup"

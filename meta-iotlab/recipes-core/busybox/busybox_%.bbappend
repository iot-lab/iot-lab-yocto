FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# NOTE: we also supply our own defconfig in 'files/'
#
# It makes busybox vi more functional by setting:
#
# CONFIG_FEATURE_VI_REGEX_SEARCH=y
# CONFIG_FEATURE_VI_DOT_CMD=y
# CONFIG_FEATURE_VI_READONLY=y
# CONFIG_FEATURE_VI_SETOPTS=y
# CONFIG_FEATURE_VI_SET=y
# CONFIG_FEATURE_VI_ASK_TERMINAL=y
#
# How to make yours:
# - get poky's version of busybox source code
# - copy defconfig from poky into source tree with '.config' name
# - run `make menuconfig`
# - enable/disable some goodies (see list above)
# - copy here
#
# Or to update to a later busybox (easier):
# - copy defconfig from here into source tree
# - run `make oldconfig`, respond to questions
# - copy here

SRC_URI += "file://async-dhclient.patch"

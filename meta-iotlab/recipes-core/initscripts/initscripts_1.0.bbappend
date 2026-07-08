# tag to identify a recipe as an extension of another recipe
PR:append = "-v0"

# checkroot.sh and read-only-rootfs-hook.sh scripts are loaded from here
# and not from original initscripts recipe
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# The pseudo version pinned by poky (walnascar-5.2.4) predates upstream
# support for the openat2 syscall. Newer host tar (>=1.35, ships a CVE fix
# that switched hardlink-preserving copies to openat2) then trips pseudo's
# syscall interception, breaking do_package with:
#   "got *at() syscall for unknown directory, fd N"
#   "unknown base path for fd N" / tar: ... Bad address
# Bump to the first upstream pseudo release with openat2 support.
SRCREV = "6c0d8c6b81ca7c2ef2b5a9a996605e1a51814442"

# Both already included upstream as of this SRCREV (glibc238.patch: pseudo
# commit 865ca5b "Add wrapper functions to operate correctly with glibc 2.38
# onwards"; 0001-configure-Prune-PIE-flags.patch: same pie-stripping sed now
# baked into upstream's configure); applying either again on top would
# conflict with the now-equivalent upstream code.
SRC_URI:remove = "file://glibc238.patch"
SRC_URI:remove = "file://0001-configure-Prune-PIE-flags.patch"

# older-glibc-symbols.patch (native/nativesdk only) still applies a needed
# fix, but its Makefile.in/pseudo_wrappers.c hunks were written against the
# pinned SRCREV and no longer match after the intervening upstream commits
# (extra pseudo_client_scanf.o object, openat2 wrappers). Use our own copy
# rebased onto this SRCREV instead of poky's.
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI:remove:class-native = "file://older-glibc-symbols.patch"
SRC_URI:remove:class-nativesdk = "file://older-glibc-symbols.patch"
SRC_URI:append:class-native = " file://older-glibc-symbols.patch"
SRC_URI:append:class-nativesdk = " file://older-glibc-symbols.patch"

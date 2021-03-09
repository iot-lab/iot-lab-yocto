require linux.inc

LINUX_VERSION_EXTENSION_append = "-iotlab"

# Override COMPATIBLE_MACHINE to include your machine in a copy of this recipe
# file. Leaving it empty here ensures an early explicit build failure.
COMPATIBLE_MACHINE_var-som-am35 = "var-som-am35"

KBUILD_DEFCONFIG_var-som-am35 ?=  "var-som-am35_defconfig"

SRC_URI_append_var-som-am35 += "file://var-som-am35-standard.scc"

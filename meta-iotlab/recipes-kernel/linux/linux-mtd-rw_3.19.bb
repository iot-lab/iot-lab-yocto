require linux.inc

LINUX_VERSION_EXTENSION_append = "-iotlab-mtd-rw"

# Override COMPATIBLE_MACHINE to include your machine in a copy of this recipe
# file. Leaving it empty here ensures an early explicit build failure.
COMPATIBLE_MACHINE_var-som-am35-mtd-rw = "var-som-am35-mtd-rw"

KBUILD_DEFCONFIG_var-som-am35-mtd-rw ?=  "var-som-am35-mtd-rw_defconfig"

SRC_URI_append_var-som-am35-mtd-rw += "file://var-som-am35-standard.scc"

require u-boot.inc
require u-boot-iotlab-release.inc

DEPENDS += "dtc-native"

PV = "v2015.01+git${SRCPV}"
PR[vardepsxeclude]="DATETIME"

EXTRA_OEMAKE_append = " KCFLAGS=-fgnu89-inline"

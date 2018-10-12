SUMMARY = "MJPG-streamer takes JPGs from Linux-UVC compatible webcams, filesystem or other input plugins and streams them as M-JPEG via HTTP to webbrowsers, VLC and other software. It is the successor of uvc-streamer, a Linux-UVC streaming application with Pan/Tilt"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"

PV = "0.4+git${SRCPV}"
SRCREV = "8cc9d22c1e79905d529a248ccf05bbf0625e0bf3"
SRC_URI = "git://github.com/jacksonliam/mjpg-streamer.git;protocol=https \
           file://0001-slightly-unbreak-raspi-cmake.patch;striplevel=2 \
           file://d0dcc2e0bbe84ef0937def2d7d226eee88150739.patch \
          "

PATCHTOOL = "git"

DEPENDS = "libgphoto2 v4l-utils libsdl udev"

# Add support for raspicam on rpi platforms
DEPENDS_append_rpi = " ${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "", "userland", d)}"
# https://lists.yoctoproject.org/pipermail/yocto/2014-June/019933.html
ASNEEDED_rpi = ""


S = "${WORKDIR}/git/mjpg-streamer-experimental"

inherit cmake

OECMAKE_GENERATOR="Unix Makefiles"
EXTRA_OECMAKE = "-DENABLE_HTTP_MANAGEMENT=ON"

EXTRA_OECMAKE_append_rpi = " ${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "-DHAS_RASPI=OFF", "-DHAS_RASPI=ON", d)}"

SRC_URI += " \
    file://vchiq-udev-rule \
"

do_install() {
    oe_runmake install DESTDIR=${D}

    install -d                                 ${D}${sysconfdir}/udev/rules.d/
    install -m 0644 ${WORKDIR}/vchiq-udev-rule ${D}${sysconfdir}/udev/rules.d/10-vchiq-permissions.rules
}

pkg_postinst_${PN} () {
  # Add www-data to video group
  if test "x$D" != "x"; then
    OPT="--root $D"   # Installing into a sysroot
  else
    OPT=""
  fi
  usermod $OPT -a -G video www-data
}

FILES_${PN} += "${libdir}/*.so"

# And make it rpi specific due to depending on rpi binaries
PACKAGE_ARCH_rpi = "${MACHINE_ARCH}"

LICENSE = "NTP"

PR:append = "-iotlab0"
# inspired by: https://github.com/sarnold/meta-alt-desktop-extras/blob/master/recipes-support/ntp/ntp_4.2.6p5.bbappend

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://ntp_wait_synced.sh"

# Add Configure
do_configure:prepend() {
    sed -i \
        -e "s|time.server.example.com|networkgateway|" \
        -e "s|# server|server|" \
        -e "s|^server 127|#server 127|" \
        -e "s|^fudge 127|#fudge 127|" \
        ${UNPACKDIR}/ntp.conf
}

# Add ntpq tool
PACKAGES =+ "${PN}-waitsync"

do_install:append() {
    find ${UNPACKDIR}
    install -m 0755 ${UNPACKDIR}/ntp_wait_synced.sh    ${D}/${sbindir}/
}

FILES:${PN}-waitsync += "${sbindir}/ntp_wait_synced.sh"

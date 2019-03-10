LICENSE = "NTP"

PR_append = "-iotlab0"
# inspired by: https://github.com/sarnold/meta-alt-desktop-extras/blob/master/recipes-support/ntp/ntp_4.2.6p5.bbappend

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://ntp_wait_synced.sh"

# Add Configure
do_configure_prepend() {
    sed -i \
        -e "s|time.server.example.com|networkgateway|" \
        -e "s|# server|server|" \
        -e "s|^server 127|#server 127|" \
        -e "s|^fudge 127|#fudge 127|" \
        ${WORKDIR}/ntp.conf
}

# Add ntpq tool
PACKAGES =+ "${PN}-ntpq ${PN}-waitsync"
FILES_${PN}-ntpq += "${sbindir}/ntpq"
RDEPENDS_${PN}-waitsync = "${PN}-ntpq"

do_install_append() {
    find ${WORKDIR}
    install -m 0755 ${B}/ntpq/ntpq ${D}/${sbindir}/
    install -m 0755 ${WORKDIR}/ntp_wait_synced.sh    ${D}/${sbindir}/
}

FILES_${PN}-waitsync += "${sbindir}/ntp_wait_synced.sh"

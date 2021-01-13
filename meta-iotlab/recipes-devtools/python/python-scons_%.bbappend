do_install_append() {
    # python3-scons recipe clash
    rm -f ${D}${bindir}/scons-time
    rm -f ${D}${bindir}/scons
    rm -f ${D}${bindir}/sconsign
}


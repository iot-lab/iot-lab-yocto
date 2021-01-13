do_install_append() {
    # python3-pyflakes recipe clash
    rm -f ${D}${bindir}/pyflakes
    rmdir ${D}${bindir}
}


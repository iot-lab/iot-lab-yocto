# add ast to python-core
FILES_${PN}-core += "${libdir}/python2.7/ast.*"

# remove useless tests
RDEPENDS_${PN}-modules_remove = "${PN}-tests ${PN}-sqlite3-tests"

# add wsgiref package (prepend to avoid python-misc)
PACKAGES =+ "${PN}-wsgiref"
RDEPENDS_${PN}-wsgiref = "${PN}-core ${PN}-netserver"
FILES_${PN}-wsgiref = "${libdir}/python2.7/wsgiref"
RDEPENDS_${PN}-modules += "${PN}-wsgiref"

# add asyncore package (prepend to avoid python-misc)
PACKAGES =+ "${PN}-asyncore"
RDEPENDS_${PN}-asyncore = "${PN}-core ${PN}-netserver"
FILES_${PN}-asyncore = "${libdir}/python2.7/asyncore.* ${libdir}/python2.7/asynchat.*"
RDEPENDS_${PN}-modules += "${PN}-asyncore"

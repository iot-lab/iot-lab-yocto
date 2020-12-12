SUMMARY = "Pyzmq provides Zero message queue access for the Python language"
HOMEPAGE = "http://zeromq.org/bindings:python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING.BSD;md5=11c65680f637c3df7f58bbc8d133e96e"
DEPENDS = "zeromq"

SRC_URI = "file://0001-fix-bad-RPATH.patch"
SRC_URI[md5sum] = "d92fa329c20c72f4831a6aaca8057b2a"
SRC_URI[sha256sum] = "9d1d69da7ee78dce8721a1617c7938ded1cd1df76a6c1abf19acebb1a5ccc2bf"

inherit pypi setuptools3 pkgconfig

RDEPENDS_${PN} += "python3-multiprocessing"

FILES_${PN}-dbg =+ "${PYTHON_SITEPACKAGES_DIR}/zmq/backend/cython/.debug"

do_compile_prepend() {
    echo [global] > ${S}/setup.cfg
    echo zmq_prefix = ${STAGING_DIR_HOST} >> ${S}/setup.cfg
    echo have_sys_un_h = True >> ${S}/setup.cfg
    echo skip_check_zmq = True >> ${S}/setup.cfg
    echo libzmq_extension = False >> ${S}/setup.cfg
    echo no_libzmq_extension = True >> ${S}/setup.cfg
}

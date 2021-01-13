SUMMARY = "WebSocket client for python. hybi13 is supported."
HOMEPAGE = "https://github.com/websocket-client/websocket-client.git"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=18b09a20dd186af4fd60f1a08311628c"

inherit pypi setuptools
SRCREV = "d97eadb070d685aac207fcf6d663a013db42d86a"

# for now, prepopulate this in the downloads directory
SRC_URI = "git://github.com/websocket-client/websocket-client.git"

S = "${WORKDIR}/git"
PYPI_PACKAGE = "websocket_client"

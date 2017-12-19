# Copyright (c) 2012-2014 LG Electronics, Inc.

DESCRIPTION = "c-ares is a C library that resolves names asynchronously."
HOMEPAGE = "http://daniel.haxx.se/projects/c-ares/"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://ares_init.c;beginline=1;endline=3;md5=53f5ecf4c22c37cf1ddd1ef8f8eccce0"

SRC_URI = "http://c-ares.haxx.se/download/${BP}.tar.gz"
SRC_URI[md5sum] = "d2e010b43537794d8bedfb562ae6bba2"
SRC_URI[sha256sum] = "03f708f1b14a26ab26c38abd51137640cb444d3ec72380b21b20f1a8d2861da7"

inherit autotools pkgconfig


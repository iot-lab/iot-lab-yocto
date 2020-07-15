SUMMARY = "Python style guide checker"
SECTION = "devel/python"
HOMEPAGE= "https://pypi.org/project/pep8/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://README.rst;md5=d8ebbbe831259ce010179d2f310b0f3e"

PYPI_PACKAGE = "pep8"

SRC_URI[md5sum] = "603821d06db945c71d811b5a8d78423c"
SRC_URI[sha256sum] = "fe249b52e20498e59e0b5c5256aa52ee99fc295b26ec9eaa85776ffdb9fe6374"

inherit pypi setuptools3

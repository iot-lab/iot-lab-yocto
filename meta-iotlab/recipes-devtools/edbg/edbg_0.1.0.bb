DESCRIPTION = "edbg: Simple utility for programming Atmel MCUs through EDBG interface"
HOMEPAGE = "https://github.com/ataradov/edbg"
SECTION = "console"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://git/edbg.h;md5=6394409c44d1ac9a3928fb6996e4b8b3"

SRC_URI = "git://github.com/ataradov/edbg.git;protocol=https"
SRCREV = "80c50d03aac831f87f513a5d5455df1286bcb540"

DEPENDS = "udev"

S = "${WORKDIR}"

SRCS = "${S}/git/dap.c \
        ${S}/git/edbg.c \
        ${S}/git/target.c \
        ${S}/git/target_atmel_cm0p.c \
        ${S}/git/target_atmel_cm3.c \
        ${S}/git/target_atmel_cm4.c \
        ${S}/git/target_atmel_cm7.c \
        ${S}/git/dbg_lin.c"

LIBS += "-ludev"

CFLAGS += "-W -Wall -Wextra -O2 -std=gnu11"

do_compile() {
	${CC} ${CFLAGS} ${SRCS} ${LIBS} -o ${S}/git/edbg
}

do_install() {
	install -d ${D}${bindir}
	install -m 755 ${S}/git/edbg ${D}${bindir}
}

INSANE_SKIP_${PN} = "ldflags"
INSANE_SKIP_${PN}-dev = "ldflags"

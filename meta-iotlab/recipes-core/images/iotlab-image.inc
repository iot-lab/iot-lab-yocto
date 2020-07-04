DESCRIPTION = "IoT-LAB base image, supports target devices"
LICENSE = "GPLv2"
inherit core-image


IMAGE_FEATURES += "          \
        ssh-server-openssh   \
        package-management   \
        tools-sdk            \
        tools-debug          \
        read-only-rootfs     \
        "

re_add_rw_to_rootfs() {
    # revert 'ro' modification done by core-image by calling
    #   oe-core/meta/classes/image.bbclass
    #
    # we want 'read-only-rootfs' for not writing in shared nfs directories
    # but not readonly filesystem
    sed -i -e '/^[#[:space:]]*\/dev\/root/{s/ ro / defaults /}' ${IMAGE_ROOTFS}/etc/fstab

    # move opkg status files to an nfs shared folder
    mkdir -p ${IMAGE_ROOTFS}/var/lib_nfs_shared/
    mv ${IMAGE_ROOTFS}/var/lib/opkg ${IMAGE_ROOTFS}/var/lib_nfs_shared/
    # ln should be relative to work on rootfs command
    ln -s ../lib_nfs_shared/opkg ${IMAGE_ROOTFS}/var/lib/opkg

}
# do it after inheriting core-image
ROOTFS_POSTPROCESS_COMMAND += "re_add_rw_to_rootfs; "

ROOTFS_RO_UNNEEDED = "${VIRTUAL-RUNTIME_update-alternatives} ${ROOTFS_BOOTSTRAP_INSTALL}"

DISTRO_FEATURES += "nfs ipv6"

IMAGE_INSTALL +="               \
        lsb                     \
        tzdata                  \
        cronie                  \
        logrotate               \
        tmux                    \
        screen                  \
        iptables                \
        iproute2                \
        socat                   \
        iputils                 \
        rsync                   \
        ntp                     \
        ntp-waitsync            \
        ntp-utils               \
        python                  \
        python-pyserial         \
        python-dev              \
        python-misc             \
        python-modules          \
        python-setuptools       \
        python-pip              \
        python-magic            \
        python-intelhex         \
        python3                 \
        python3-pyserial        \
        python3-dev             \
        python3-misc            \
        python3-modules         \
        python3-pip             \
        python3-setuptools      \
        vim                     \
        "

IMAGE_INSTALL +="               \
        base-files              \
        i2c-tools               \
        coreutils               \
        tzdata-europe           \
        rsyslog                 \
        dhcp-client             \
        usbutils                \
        "

# pour le super ps
IMAGE_INSTALL +="               \
        gawk                    \
        findutils               \
        curl                    \
        wget                    \
        lsof                    \
        procps                  \
        htop                    \
        "

IMAGE_INSTALL += "              \
        mtd-utils               \
        \
        cmake                   \
        flex                    \
        bison                   \
        \
        git                     \
        git-perltools           \
        \
        \
        \
        htop                    \
        \
        gcov                    \
        gcov-symlinks           \
        \
        \
        tcpdump                 \
        \
        valgrind                \
        \
        "

IMAGE_INSTALL += "              \
        bash                    \
        bind-utils              \
        oml2-dev                \
        jansson                 \
        jansson-dev             \
        c-ares                  \
        c-ares-dev              \
        libev                   \
        libev-dev               \
        libevent                \
        libevent-dev            \
        boost                   \
        boost-dev               \
        jemalloc                \
        jemalloc-dev            \
        cunit                   \
        cunit-dev               \
        openssl                 \
        openssl-dev             \
        \
        \
        \
        nfs-utils-client        \
        \
        openssh-ssh             \
        openssh-scp             \
        openssh-sftp-server     \
        \
        curl                    \
        \
        "
# Broken
IMAGE_INSTALL_BROKEN += "       \
        libgcov-dev             \
        openocd                 \
        oml2-dev                \
        gcov-symlinks           \
        "

# www-data user requires admin rights for controlling the USB ports
# on the RPI3 gateway image
do_install_append () {
    echo "www-data ALL= NOPASSWD: /usr/bin/ykushcmd" >> ${D}${sysconfdir}/sudoers.d/www-data
}

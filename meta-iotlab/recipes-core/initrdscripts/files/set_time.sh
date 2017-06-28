#! /bin/sh

DESC="NTP set_time.sh"
NAME=set_time.sh

set -x

date
timeout 30 /usr/sbin/ntpd -g -q -d
date
/etc/init.d/hwclock.sh reload
date

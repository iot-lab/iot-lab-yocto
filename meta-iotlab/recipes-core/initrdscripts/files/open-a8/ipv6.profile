#!/bin/sh

# Put in /etc/profile.d/
IPV6_CONFIG="/var/local/config/ipv6"

if [ -f ${IPV6_CONFIG} ]; then
  source ${IPV6_CONFIG}
fi

#! /bin/sh

# set default values to /var/run/ files

#
# /etc/resolv.conf
#
grep -e "nameserver" -e "domain" /proc/net/pnp >> /var/run/resolv.conf

#
# /etc/hosts
#
NFS_SERVER_IP=$(grep -oE "([0-9]{1,3}\.){3}[0-9]{1,3}" /proc/net/nfsfs/servers)
NETWORK_GATEWAY=$(ip route show dev eth0 | sed -n '/default/ {s/.*via //;s/ dev.*//;p}')
# copy default and add other entries
cp   /etc/default/hosts         /var/run/hosts
echo "$NFS_SERVER_IP nfsserver" >> /var/run/hosts
echo "$NETWORK_GATEWAY networkgateway" >> /var/run/hosts


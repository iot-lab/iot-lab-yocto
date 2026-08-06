# poky's own interfaces file ships a "predictable name" mapping stanza
# that its own comment admits is broken:
#
#   # Busybox ifupdown won't process /en* correctly
#   auto /en*=eth
#   iface eth inet dhcp
#
# BusyBox ifupdown doesn't understand the "/pattern/=stanza" mapping syntax
# at all - it tries to literally ifup an interface named "/en*", which `ip`
# then rejects ("not a valid ifname"). eth0 already has its own explicit
# stanza above it, so this block is dead weight - drop it to quiet the
# error, no behaviour actually changes.
do_install:append () {
	sed -i '/^# Busybox ifupdown/,/^iface eth inet dhcp$/d' ${D}${sysconfdir}/network/interfaces
}

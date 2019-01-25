#!/bin/bash

# Reset iC880a PIN
SX1301_RESET_BCM_PIN=25
echo "$SX1301_RESET_BCM_PIN"  > /sys/class/gpio/export 
echo "out" > /sys/class/gpio/gpio$SX1301_RESET_BCM_PIN/direction 
echo "0"   > /sys/class/gpio/gpio$SX1301_RESET_BCM_PIN/value 
sleep 0.2
echo "1"   > /sys/class/gpio/gpio$SX1301_RESET_BCM_PIN/value 
sleep 0.2
echo "0"   > /sys/class/gpio/gpio$SX1301_RESET_BCM_PIN/value
sleep 0.2
echo "$SX1301_RESET_BCM_PIN" > /sys/class/gpio/unexport 

# Fire up the forwarder.
cd /opt/lora
./lora_pkt_fwd

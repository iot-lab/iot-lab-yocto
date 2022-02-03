DESCRIPTION = "IoT-LAB image for Open A8 used by users"
include iotlab-image.inc

# Include modules in rootfs
IMAGE_INSTALL += " \
    kernel-modules \
    gateway-packagegroup \
    mjpg-streamer \
    sudo \
    rtl-sdr \
    ykush \
    lora-gateway-utils \
    lora-packet-forwarder \
    lora-gateway-bridge \
    mosquitto \
    segger-jlink \	
	"

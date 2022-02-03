DESCRIPTION = "IoT-LAB image for RPI3 gateways"
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
    uhubctl \
	"

#
# Makefile for building IoT-LAB images and packages
#
#

TARGET ?= rpi4

IMAGES = iotlab-image-gateway

# Define variables specific to the given target
ifeq ($(TARGET), rpi2)
  BUILD_DIR = build-rpi2
  TARGET_ARCH = cortexa7t2hf-neon-vfpv4
  TARGET_IMG = raspberrypi2
  KERNEL_IMG = zImage

  # Use a mapping between image target of make with image name in yocto
  iotlab-image-gateway = iotlab-image-gateway-rpi2
else ifeq ($(TARGET), rpi3)
  BUILD_DIR = build-rpi3
  TARGET_ARCH = cortexa7t2hf-neon-vfpv4
  TARGET_IMG = raspberrypi3
  KERNEL_IMG = zImage

  # Use a mapping between image target of make with image name in yocto
  iotlab-image-gateway = iotlab-image-gateway-rpi3
else ifeq ($(TARGET), rpi4)
  BUILD_DIR = build-rpi4
  TARGET_ARCH = cortexa7t2hf-neon-vfpv4
  TARGET_IMG = raspberrypi4
  KERNEL_IMG = zImage

  # Use a mapping between image target of make with image name in yocto
  iotlab-image-gateway = iotlab-image-gateway-rpi4
else ifeq ($(TARGET), rpi5)
  BUILD_DIR = build-rpi5
  TARGET_ARCH = cortexa76
  TARGET_IMG = raspberrypi5
  KERNEL_IMG = Image

  # Use a mapping between image target of make with image name in yocto
  iotlab-image-gateway = iotlab-image-gateway-rpi5
else
  $(error Unsupported target, '$(TARGET)')
endif

PKGS_DIR = $(BUILD_DIR)/tmp/deploy/ipk/$(TARGET_ARCH)
IMGS_DIR = $(BUILD_DIR)/tmp/deploy/images/$(TARGET_IMG)

.PHONY: init submodules $(BUILD_DIR)/conf/local.conf

ifeq (0,$(BUILD_IN_DOCKER))
  # BUILD_IN_DOCKER set to 0 means we are running inside the container
  IN_DOCKER = 1
endif

# only init when not in docker
init: $(if $(IN_DOCKER),,submodules $(BUILD_DIR)/conf/local.conf)

$(BUILD_DIR)/conf/local.conf:
	sed -i "s/^BB_NUMBER_THREADS.*$$/BB_NUMBER_THREADS ?= \"$$(( $$(nproc) * 2 ))\"/" $@
	sed -i "s/^PARALLEL_MAKE.*$$/PARALLEL_MAKE ?= \"-j $$(( $$(nproc) * 2 ))\"/"      $@
ifdef BUILD_DOWNLOAD_DIR
	sed -i "s/^\DL_DIR.*$$/DL_DIR ?= \"$(subst /,\/,$(BUILD_DOWNLOAD_DIR))\/downloads\"/" $@
endif

submodules:
	git submodule update --init --force --remote

# # # # # # # # # # # # # # # # # # # # #
# Building images and packages targets  #
# # # # # # # # # # # # # # # # # # # # #

.PHONY: target-info build-all build-img-% iotlab-image-%

target-info:
	@echo Using target: $(TARGET)
	@echo ""
	@echo "Available image targets:"
	@for img in $(IMAGES); do echo "  - $$img"; done;

build-all: $(IMAGES)

$(IMAGES): %: build-img-% clean-img-%

DOCKER_IMAGE ?= fitiotlab/iot-lab-yocto
DOCKER_IMAGE_VERSION ?= latest
# Use BUILD_IN_DOCKER=0 env to disable build in docker for sub-make, to avoid
# recursive calls to docker
DOCKER_CMD = docker run --rm --hostname yocto       \
    -v $(shell pwd):/shared                         \
    $(if $(BUILD_DOWNLOAD_DIR),-v $(BUILD_DOWNLOAD_DIR):$(BUILD_DOWNLOAD_DIR),) \
    -e BUILD_IN_DOCKER=0                            \
    -e TARGET=$(TARGET)                             \
    -u $(shell id -u):$(shell id -g)                \
    $(DOCKER_IMAGE):$(DOCKER_IMAGE_VERSION)

.PHONY: build-pkg-%
build-pkg-%: init
ifeq (1,$(BUILD_IN_DOCKER))
	$(DOCKER_CMD) make $@
else
	@# build package
	time bash -c \
                "unset ga_auto_remove GA_AUTO_REMOVE; source ./poky/oe-init-build-env $(BUILD_DIR); bitbake -k $*"
	@echo ""
	@echo ""
	@echo "$*" ipk package files should be found here:
	@ls $(PKGS_DIR)/$**
endif

.PHONY: build-pkg-%-native
build-pkg-%-native: init
ifeq (1,$(BUILD_IN_DOCKER))
	$(DOCKER_CMD) make $@
else
	@# build native package
	time bash -c \
                "unset ga_auto_remove GA_AUTO_REMOVE; source ./poky/oe-init-build-env $(BUILD_DIR); bitbake -k $*-native"
endif

.PHONY: clean-pkg-%
clean-pkg-%: init
ifeq (1,$(BUILD_IN_DOCKER))
	$(DOCKER_CMD) make $@
else
	@# clean package
	time bash -c \
                "unset ga_auto_remove GA_AUTO_REMOVE; source ./poky/oe-init-build-env $(BUILD_DIR); bitbake -c cleanall $*"
endif

.PHONY: build-uboot
build-uboot: init
ifeq (1,$(BUILD_IN_DOCKER))
	$(DOCKER_CMD) make $@
else
	@# build u-boot (and, on rpi5, the netboot boot.scr)
	time bash -c \
                "unset ga_auto_remove GA_AUTO_REMOVE; source ./poky/oe-init-build-env $(BUILD_DIR); bitbake -k virtual/bootloader"
	@echo ""
	@echo ""
	@echo "u-boot files should be found here:"
	@ls $(IMGS_DIR)/u-boot*
endif

.PHONY: clean-uboot
clean-uboot: init
ifeq (1,$(BUILD_IN_DOCKER))
	$(DOCKER_CMD) make $@
else
	@# clean u-boot
	time bash -c \
                "unset ga_auto_remove GA_AUTO_REMOVE; source ./poky/oe-init-build-env $(BUILD_DIR); bitbake -c cleanall virtual/bootloader"
endif

.PHONY: build-kernel
build-kernel: init
ifeq (1,$(BUILD_IN_DOCKER))
	$(DOCKER_CMD) make $@
else
	@# build kernel
	time bash -c \
                "unset ga_auto_remove GA_AUTO_REMOVE; source ./poky/oe-init-build-env $(BUILD_DIR); bitbake -k virtual/kernel"
	@echo ""
	@echo ""
	@echo "$*" Kernel files should be found here:
	@ls $(IMGS_DIR)/$(KERNEL_IMG)*
endif

.PHONY: clean-kernel
clean-kernel: init
ifeq (1,$(BUILD_IN_DOCKER))
	$(DOCKER_CMD) make $@
else
	@# clean kernel
	time bash -c \
                "unset ga_auto_remove GA_AUTO_REMOVE; source ./poky/oe-init-build-env $(BUILD_DIR); bitbake -c cleanall virtual/kernel"
endif

.PHONY: build-img-%
build-img-%: init
ifeq (1,$(BUILD_IN_DOCKER))
	$(DOCKER_CMD) make $@
else
	@# build image
	time bash -c \
		"unset ga_auto_remove GA_AUTO_REMOVE; source ./poky/oe-init-build-env $(BUILD_DIR); bitbake -k $($*)"
	@echo ""
	@echo ""
	@echo "$*" image files should be found here:
	@ls $(IMGS_DIR)/$($*)*
endif

.PHONY: clean-img-%
clean-img-%:
	@# keep only the last one
	@# don't match the symlink or the last build image
	rm -f $(shell find $(IMGS_DIR)/ -name '$($*)-$(TARGET_IMG)*' -type f  \
		! -name $(shell readlink $(IMGS_DIR)/$($*)-$(TARGET_IMG).tar.gz) \
		! -name $(shell readlink $(IMGS_DIR)/$($*)-$(TARGET_IMG).manifest) \
                )

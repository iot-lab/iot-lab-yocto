#
# Makefile for building IoT-LAB images and packages
#
#

TARGET ?= a8

IMAGES = iotlab-image
IMAGES += iotlab-image-gateway

EXTRA_BUILDS =

# Define variables specific to the given target
ifeq ($(TARGET), a8)
  BUILD_DIR = build
  TARGET_ARCH = cortexa8hf-neon
  TARGET_IMG = var-som-am35
  KERNEL_IMG = uImage

  # Use a mapping between image target of make with image name in yocto
  iotlab-image = iotlab-image-open-a8
  iotlab-image-gateway = iotlab-image-gateway

  # A8 also has an autotest image
  iotlab-image-autotest = iotlab-image-open-a8-autotest
  IMAGES += iotlab-image-autotest

  # We also build uboot and a mtd-rw version of the kernel for A8
  EXTRA_BUILDS = build-uboot build-kernel-mtd-rw
else ifeq ($(TARGET), rpi3)
  BUILD_DIR = build-rpi3
  TARGET_ARCH = cortexa7hf-neon-vfpv4
  TARGET_IMG = raspberrypi3
  KERNEL_IMG = zImage

  # Use a mapping between image target of make with image name in yocto
  iotlab-image = iotlab-image-rpi3
  iotlab-image-gateway = iotlab-image-gateway-rpi3
else
  $(error Unsupported target, '$(TARGET)')
endif

PKGS_DIR = $(BUILD_DIR)/tmp/deploy/ipk/$(TARGET_ARCH)
IMGS_DIR = $(BUILD_DIR)/tmp/deploy/images/$(TARGET_IMG)
IMGS_MTD_RW_DIR = $(IMGS_DIR)-mtd-rw

.PHONY: init submodules $(BUILD_DIR)/conf/local.conf

init: submodules $(BUILD_DIR)/conf/local.conf

$(BUILD_DIR)/conf/local.conf:
	sed -i "s/^BB_NUMBER_THREADS.*$$/BB_NUMBER_THREADS ?= \"$$(( $$(nproc) * 2 ))\"/" $@
	sed -i "s/^PARALLEL_MAKE.*$$/PARALLEL_MAKE ?= \"-j $$(( $$(nproc) * 2 ))\"/"      $@

submodules:
	git submodule update --init

# # # # # # # # # # # # # # # # # # # # #
# Building images and packages targets  #
# # # # # # # # # # # # # # # # # # # # #

.PHONY: build-all build-img-% iotlab-image-%

list-images:
	@for img in $(IMAGES); do echo $$img; done;

build-all: $(IMAGES) $(EXTRA_BUILDS)

$(IMAGES): %: build-img-% clean-img-%

.PHONY: build-pkg-%
build-pkg-%:
	@# build package
	time bash -c \
                "source ./poky/oe-init-build-env $(BUILD_DIR); bitbake -k $($*)"
	@echo ""
	@echo ""
	ackage files should be found here:
	ls $(PKGS_DIR)

.PHONY: build-pkg-%-native
build-pkg-%-native: init
	@# build native package
	time bash -c \
                "source ./poky/oe-init-build-env $(BUILD_DIR); bitbake -k $($*)-native"

.PHONY: clean-pkg-%
clean-pkg-%: init
	@# clean package
	time bash -c \
                "source ./poky/oe-init-build-env $(BUILD_DIR); bitbake -c cleanall $($*)"

.PHONY: build-uboot
build-uboot: init
	@# build u-Boot
	time bash -c \
                "source ./poky/oe-init-build-env $(BUILD_DIR); bitbake -k virtual/bootloader"
	@echo ""
	@echo ""
	@echo "$*" U-Boot files should be found here:
	ls $(IMGS_DIR)/u-boot*
	ls $(IMGS_DIR)/MLO*

.PHONY: clean-uboot
clean-uboot: init
	@# clean u-boot
	time bash -c \
                "source ./poky/oe-init-build-env $(BUILD_DIR); bitbake -c cleanall virtual/bootloader"

.PHONY: build-kernel
build-kernel: init
	@# build kernel
	time bash -c \
                "source ./poky/oe-init-build-env $(BUILD_DIR); bitbake -k virtual/kernel"
	@echo ""
	@echo ""
	@echo "$*" Kernel files should be found here:
	ls $(IMGS_DIR)/$(KERNEL_IMG)*

.PHONY: clean-kernel
clean-kernel: init
	@# clean kernel
	time bash -c \
                "source ./poky/oe-init-build-env $(BUILD_DIR); bitbake -c cleanall virtual/kernel"

.PHONY: build-kernel-mtd-rw
build-kernel-mtd-rw: init
	@# build mtd writeable Kernel
	time bash -c \
                "source ./poky/oe-init-build-env $(BUILD_DIR); MACHINE=$(TARGET_IMG)-mtd-rw bitbake -k virtual/kernel"
	@echo ""
	@echo ""
	@echo "$*" Kernel mtd writeable files should be found here:
	ls $(IMGS_MTD_RW_DIR)/$(KERNEL_IMG)*

.PHONY: clean-kernel-mtd-rw
clean-kernel-mtd-rw: init
	@# clean kernel mtd writeable package
	time bash -c \
                "source ./poky/oe-init-build-env $(BUILD_DIR); MACHINE=$(TARGET_IMG)-mtd-rw bitbake -c cleanall virtual/kernel"

.PHONY: build-img-%
build-img-%: init
	@# build image
	time bash -c \
		"source ./poky/oe-init-build-env $(BUILD_DIR); bitbake -k $($*)"
	@echo ""
	@echo ""
	@echo "$*" image files should be found here:
	ls $(IMGS_DIR)/$**

.PHONY: clean-img-%
clean-img-%:
	@# keep only the last one
	@# don't match the symlink or the last build image
	rm -f $(shell find $(IMGS_DIR)/ -name '$($*)-$(TARGET_IMG)*' -type f  \
		! -name $(shell readlink $(IMGS_DIR)/$($*)-$(TARGET_IMG).tar.gz) \
		! -name $(shell readlink $(IMGS_DIR)/$($*)-$(TARGET_IMG).manifest) \
                )

#
# Makefile for building IoT-LAB images and packages
#
#

BUILD_DIR = build

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

IMGS_DIR = build/tmp/deploy/images/var-som-am35
IMGS_MTD_RW_DIR = $(IMGS_DIR)-mtd-rw
PKGS_DIR = build/tmp/deploy/ipk/cortexa8hf-neon

IMAGES  = iotlab-image-gateway
IMAGES += iotlab-image-open-a8
IMAGES += iotlab-image-open-a8-autotest

.PHONY: build-all build-pkg-% build-pkg-%-native build-img-% clean-img-% iotlab-image-% clean-pkg-% build-uboot clean-uboot build-kernel clean-kernel build-kernel-mtd-rw clean-kernel-mtd-rw

build-all: $(IMAGES) build-uboot build-kernel-mtd-rw

$(IMAGES): %: build-img-% clean-img-%

build-pkg-%: init
	@# build package
	time bash -c \
                "source ./poky/oe-init-build-env build; bitbake -k $*"
	@echo ""
	@echo ""
	@echo "$*" ipk package files should be found here:
	ls $(PKGS_DIR)/$**

build-pkg-%-native: init
	@# build native package
	time bash -c \
                "source ./poky/oe-init-build-env build; bitbake -k $*-native"

clean-pkg-%: init
	@# clean package
	time bash -c \
                "source ./poky/oe-init-build-env build; bitbake -c cleanall $*"

build-uboot: init
	@# build u-Boot
	time bash -c \
                "source ./poky/oe-init-build-env build; bitbake -k virtual/bootloader"
	@echo ""
	@echo ""
	@echo "$*" U-Boot files should be found here:
	ls $(IMGS_DIR)/u-boot*
	ls $(IMGS_DIR)/MLO*

clean-uboot: init
	@# clean u-boot
	time bash -c \
                "source ./poky/oe-init-build-env build; bitbake -c cleanall virtual/bootloader"

build-kernel: init
	@# build kernel
	time bash -c \
                "source ./poky/oe-init-build-env build; bitbake -k virtual/kernel"
	@echo ""
	@echo ""
	@echo "$*" Kernel files should be found here:
	ls $(IMGS_DIR)/uImage*

clean-kernel: init
	@# clean kernel
	time bash -c \
                "source ./poky/oe-init-build-env build; bitbake -c cleanall virtual/kernel"

build-kernel-mtd-rw: init
	@# build mtd writeable Kernel
	time bash -c \
                "source ./poky/oe-init-build-env build; MACHINE=var-som-am35-mtd-rw bitbake -k virtual/kernel"
	@echo ""
	@echo ""
	@echo "$*" Kernel mtd writeable files should be found here:
	ls $(IMGS_MTD_RW_DIR)/uImage*

clean-kernel-mtd-rw: init
	@# clean kernel mtd writeable package
	time bash -c \
                "source ./poky/oe-init-build-env build; MACHINE=var-som-am35-mtd-rw bitbake -c cleanall virtual/kernel"

build-img-%: init
	@# build image
	time bash -c \
		"source ./poky/oe-init-build-env build; bitbake -k $*"
	@echo ""
	@echo ""
	@echo "$*" image files should be found here:
	ls $(IMGS_DIR)/$**

clean-img-%:
	@# keep only the last one
	@# don't match the symlink or the last build image
	rm -f $(shell find $(IMGS_DIR)/ -name '$*-var-som-am35*' -type f  \
		! -name $(shell readlink $(IMGS_DIR)/$*-var-som-am35.tar.gz) \
		! -name $(shell readlink $(IMGS_DIR)/$*-var-som-am35.manifest) \
                )

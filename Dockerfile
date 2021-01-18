FROM debian:jessie

RUN apt-get update -yq \
 && DEBIAN_FRONTEND=noninteractive \
    apt-get install -yq --no-install-recommends \
    # Yocto requirements
    build-essential \
    chrpath \
    cmake \
    cpio \
    curl \
    debianutils \
    diffstat \
    gawk \
    gcc-multilib \
    git-core \
    iputils-ping \ 
    libbz2-dev \
    libegl1-mesa \
    libreadline-dev \
    libsdl1.2-dev \
    libssl-dev \
    locales \
    ntpdate \
    python3 \
    python3-pip \
    python3-pexpect \
    python3-jinja2 \
    python-dev \
    screen \
    texinfo \
    time \
    unzip \
    wget \
    xterm \
    xz-utils \
    # Oml2 requirements
    check \
    libpopt-dev \
    libsqlite3-dev \
    libtool \
    libxml2-dev \
    libxml2-utils \
    pkg-config \
    python-matplotlib \
    ruby \
    sqlite3 \
    valgrind \
 && apt-get clean && rm -rf /var/lib/apt/lists/*

RUN wget https://www.iot-lab.info/yocto-images/oml2/oml2-2.11.0.tar.gz \
 && tar -xzvf oml2-2.11.0.tar.gz \
 && cd oml2-2.11.0 \
 && ./configure --disable-doc --disable-doxygen-doc --disable-doxygen-dot \
        --disable-android --disable-doxygen-html --disable-option-checking \
 && make \
 && make install \
 && cd .. && rm -rf oml2-2.11.0

ARG USERNAME=dev
ARG PUID=1000
ARG PGID=1000

RUN groupadd -g ${PGID} ${USERNAME} \
 && useradd -u ${PUID} -g ${USERNAME} -d /home/${USERNAME} ${USERNAME} \
 && mkdir /home/${USERNAME} \
 && chown -R ${USERNAME}:${USERNAME} /home/${USERNAME}

RUN sed -i '/en_US.UTF-8/s/^# //g' /etc/locale.gen \
 && locale-gen
ENV LANG en_US.UTF-8  
ENV LANGUAGE en_US.UTF-8 
ENV LC_ALL en_US.UTF-8

USER ${USERNAME}
WORKDIR /home/${USERNAME}



FROM debian:stretch

RUN apt-get update -yq \
 && DEBIAN_FRONTEND=noninteractive \
    apt-get install -yq --no-install-recommends \
    ca-certificates \
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
    autoconf \
    automake \
    libtool \
    gnulib \
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

RUN mkdir /var/www && chown www-data:www-data /var/www

RUN git clone https://github.com/mytestbed/oml.git && \
    cd oml && \
    git checkout tags/v2.11.0 && \
    ./autogen.sh && \
    ./configure --disable-doc --disable-doxygen-doc --disable-doxygen-dot \
        --disable-android --disable-doxygen-html --disable-option-checking && \
    make && \
    make install && \
    cd .. && rm -rf oml

RUN sed -i '/en_US.UTF-8/s/^# //g' /etc/locale.gen \
 && locale-gen
ENV LANG en_US.UTF-8  
ENV LANGUAGE en_US.UTF-8 
ENV LC_ALL en_US.UTF-8

WORKDIR /shared

RUN groupadd -g 1000 dev
RUN useradd -u 1000 -g 1000 -m -N dev

RUN chown -R dev:dev /shared

USER dev

RUN git config --global user.email "admin@iot-lab.info"
RUN git config --global user.name "IoT-LAB"
# bug with ca-certificates
RUN git config --global http.sslverify false

CMD ["/bin/bash"]

#!/bin/bash

set -eux -o pipefail

# ensure your shell script has LD_LIBRARY_PATH and PKG_CONFIG_PATH, then run `sudo ldconfig`
# you might need to install `pinentry` with curses
# might need to run `gpgconf --kill all; gpgconf --launch gpg-agent` as well

# prerequisites
(
    # configure_and_make - needs 2 arguments
    # $1: library name
    # $2: library link
    configure_and_make() {
        export LD_LIBRARY_PATH=/usr/local/lib
        export PKG_CONFIG_PATH=/usr/local/lib/pkgconfig
        mylib=$1
        wget $2
        tar -xv -f ${mylib}.tar.bz2
        pushd ${mylib}
        ./configure --prefix=/usr/local
        make -j$(nproc)
        sudo make install
        popd
        # cleanup
        rm ${mylib}.tar.bz2
        rm -rf ${mylib}
    }

    mylibgpgerror="libgpg-error-1.51"
    configure_and_make "${mylibgpgerror}" "https://gnupg.org/ftp/gcrypt/gpgrt/${mylibgpgerror}.tar.bz2"

    mylibassuan="libassuan-3.0.2"
    configure_and_make "${mylibassuan}" "https://gnupg.org/ftp/gcrypt/libassuan/${mylibassuan}.tar.bz2"

    mylibksba="libksba-1.6.7"
    configure_and_make "${mylibksba}" "https://gnupg.org/ftp/gcrypt/libksba/${mylibksba}.tar.bz2"

    mylibnpth="npth-1.8"
    configure_and_make "${mylibnpth}" "https://gnupg.org/ftp/gcrypt/npth/${mylibnpth}.tar.bz2"
)

# gpg now
(
    mygpg="gnupg-2.4.7"
    wget "https://gnupg.org/ftp/gcrypt/gnupg/${mygpg}.tar.bz2"
    tar -xv -f ${mygpg}.tar.bz2
    pushd ${mygpg}

    if [[ -v LD_LIBRARY_PATH ]]; then
        export LD_LIBRARY_PATH=/usr/local/lib:$LD_LIBRARY_PATH
    else
        export LD_LIBRARY_PATH=/usr/local/lib
    fi

    if [[ -v PKG_CONFIG_PATH ]]; then
        export PKG_CONFIG_PATH=/usr/local/lib/pkgconfig:$PKG_CONFIG_PATH
    else
        export PKG_CONFIG_PATH=/usr/local/lib/pkgconfig
    fi

    ./configure --prefix=/usr/local
    make -j$(nproc)
    sudo make install
    popd
    # cleanup
    rm ${mygpg}.tar.bz2
    rm -rf ${mygpg}
)

# configure gpg
echo "pinentry-program /usr/bin/pinentry-curses" >> ~/.gnupg/gpg-agent.conf
gpgconf --reload gpg-agent

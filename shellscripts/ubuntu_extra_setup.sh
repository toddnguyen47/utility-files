#!/bin/bash

if [ -z "$1" ]; then
  printf "%s\n%s\n" \
    "Please supply an ubuntu version, like 'focal'. You can find the list of versions here:" \
    "https://packages.ubuntu.com/"
  exit -1
fi
ubuntu_version=$1


sudo apt-get update
sudo apt-get dist-upgrade -y
sudo apt-get autoclean -y
sudo apt-get autoremove -y

# Add zsh and oh-my-zsh
sudo apt-get install zsh
bash -c "$(curl -fsSL https://raw.github.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"

# Add gcc-9
printf "%s\n%s\n" \
  "deb http://ppa.launchpad.net/ubuntu-toolchain-r/test/ubuntu focal main" \
  "# deb-src http://ppa.launchpad.net/ubuntu-toolchain-r/test/ubuntu focal main" \
  | sudo tee /etc/apt/sources.list.d/ubuntu-toolchain-r-test-focal.list
sudo apt-get update
sudo apt-get install gcc-9 g++-9

# Add cmake
sudo apt-get install cmake


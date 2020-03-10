#!/bin/bash

curDir=$(PWD)

rm -rf build
mkdir -p build
cd build
cmake ../
make install
./DumbExampleExecutable
cd $curDir

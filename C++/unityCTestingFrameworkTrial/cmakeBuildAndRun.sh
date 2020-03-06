#!/bin/bash

curDir=$(PWD)

mkdir -p build
cd build
cmake ../
make install
./DumbExampleExecutable
cd $curDir

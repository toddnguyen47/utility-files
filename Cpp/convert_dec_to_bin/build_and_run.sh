#!/bin/bash

mkdir -p build
cd build
cmake ../
cmake --build .

cd ..
./run_valgrind.sh ./build/ConvertDecimalToBinary

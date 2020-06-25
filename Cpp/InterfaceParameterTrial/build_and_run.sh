#!/bin/bash

cur_dir=$(pwd)
main_executable="main"

mkdir -p build
cd build
cmake ../
cmake --build .
if [ $? -eq 0 ]; then
  "./src/${main_executable}"
fi

# Clean up
cd "$cur_dir"

@echo off
mkdir build
cd build
cmake -G "Ninja" ../
cmake --build .
ctest -V
cd ..

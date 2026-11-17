mkdir build
cmake -S . -B build -G "Ninja"
cmake --build build
build\tests\TestDumbExample.exe

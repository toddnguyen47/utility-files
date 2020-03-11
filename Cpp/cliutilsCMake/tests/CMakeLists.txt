cmake_minimum_required(VERSION 3.10)

# Copied from cliutils' CMake tutorial
# Tests need to be added as executables first
add_executable(TestDumbExample TestDumbExample.c)

# Should be linked to the main library, defined in src/CMakeLists.txt at
# add_library() line
target_link_libraries(TestDumbExample PRIVATE DumbExampleLib)

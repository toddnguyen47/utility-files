cmake_minimum_required(VERSION 3.10)

set(C_STANDARD 11)
set(C_STANDARD_REQUIRED True)

# Set the project name and version
project(DumbExample VERSION 1.0)

# Add the executable
add_executable(DumbExampleExecutable DumbExample.c TestDumbExample.c)

# Add subdirectory: unity
add_subdirectory(unity)

# Link unity library, defined in `unity/CMakeLists.txt`
target_link_libraries(DumbExampleExecutable PRIVATE unity)

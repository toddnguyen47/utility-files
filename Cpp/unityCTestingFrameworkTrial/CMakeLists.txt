cmake_minimum_required(VERSION 3.10)

# Add the executable
add_executable(DumbExampleExecutable DumbExample.c TestDumbExample.c)

# Set the C Standard to C99
set_property(TARGET DumbExampleExecutable
  PROPERTY C_STANDARD 99
  PROPERTY C_STANDARD_REQUIRED ON)

# Set the project name and version
project(DumbExample VERSION 1.0)

# Add subdirectory: unity_fixture
add_subdirectory(unity_fixture)

# Link unity library, defined in `unity/CMakeLists.txt`
target_link_libraries(DumbExampleExecutable PRIVATE unity)

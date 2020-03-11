cmake_minimum_required(VERSION 3.10)

# Copied from cliutils' CMake tutorial
# Tests need to be added as executables first
add_executable(TestDumbExample TestDumbExample.c)

# Ref: https://cliutils.gitlab.io/modern-cmake/chapters/testing/googletest.html
# Ref: https://cmake.org/cmake/help/latest/command/add_subdirectory.html?highlight=add_subdirectory
target_include_directories(TestDumbExample PUBLIC ../extern)
# Add `unity`
add_subdirectory("${PROJECT_SOURCE_DIR}/extern/unity" "extern/unity")

# Add `unity_fixture` now
add_subdirectory("${PROJECT_SOURCE_DIR}/extern/unity/fixture" "extern/unity/fixture")

# Should be linked to the main library, defined in src/CMakeLists.txt at
# add_library() line
# Should also be linked to unity's library
target_link_libraries(TestDumbExample PRIVATE DumbExampleLib unity unity_fixture)

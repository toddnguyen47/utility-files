cmake_minimum_required(VERSION 3.10)

# Copied from cliutils' CMake tutorial
# Tests need to be added as executables first
#add_executable(TestDumbExample TestDumbExample.c)

set (sources
  SprintfTest.c
  SprintfGroupTestRunner.c
)
add_executable(SprintfTest "${sources}")

# Ref: https://cmake.org/cmake/help/latest/command/add_subdirectory.html?highlight=add_subdirectory
# Add `unity`
add_subdirectory("${PROJECT_SOURCE_DIR}/extern/unity" "extern/unity")

# Add `unity_memory`
#add_subdirectory("${PROJECT_SOURCE_DIR}/extern/unity/memory" "extern/unity/memory")

# Add `unity_fixture`
add_subdirectory("${PROJECT_SOURCE_DIR}/extern/unity/fixture" "extern/unity/fixture")

# Ref: https://cliutils.gitlab.io/modern-cmake/chapters/testing/googletest.html
set(unity_directories
  ${PROJECT_SOURCE_DIR}/extern
)

target_include_directories(SprintfTest
  PUBLIC
    "$<BUILD_INTERFACE:${unity_directories}>"
    "$<INSTALL_INTERFACE:include/unitylib>"
)

# Should be linked to the main library, defined in src/CMakeLists.txt at
# add_library() line
# Should also be linked to unity's library
target_link_libraries(SprintfTest PRIVATE DumbExampleLib unity unity_fixture)

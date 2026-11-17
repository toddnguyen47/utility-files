cmake_minimum_required(VERSION 3.0 FATAL_ERROR)

add_library(unity_fixture STATIC "src/unity_fixture.c")

# unity_fixture needs `unity.h` and `unity_memory.h`
# Ref: https://github.com/google/googletest/blob/master/googletest/CMakeLists.txt#L135
set(unity_and_memory
  "${PROJECT_SOURCE_DIR}/extern/unity"
  "${PROJECT_SOURCE_DIR}/extern/unity/memory/src")

# Ref: https://pabloariasal.github.io/2018/02/19/its-time-to-do-cmake-right/
target_include_directories(unity_fixture
  PUBLIC
    "$<BUILD_INTERFACE:${unity_and_memory}>"
    "$<INSTALL_INTERFACE:include>"
)

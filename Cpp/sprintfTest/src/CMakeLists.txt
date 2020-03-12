cmake_minimum_required(VERSION 3.10)

# Copied from cliutils' CMake Tutorial

# Note that headers are optional, and do not affect add_library, but they will not
# show up in IDEs unless they are listed in add_library.

# We will use our project name before _SOURCE_DIR
# Optionally glob, but only for CMake 3.12 or later:
# file(GLOB HEADER_LIST CONFIGURE_DEPENDS "${ModernCMakeExample_SOURCE_DIR}/include/modern/*.hpp")
set(HEADER_LIST "${DumbExampleProject_SOURCE_DIR}/include/dumbExample/DumbExample.h")

# Make an automatic library - will be static or dynamic based on user setting
add_library(DumbExampleLib DumbExample.c ${HEADER_LIST})

# We need this directory, and users of our library will need it too
target_include_directories(DumbExampleLib
PUBLIC
  "$<BUILD_INTERFACE:${PROJECT_SOURCE_DIR}/include>"
  "$<INSTALL_INTERFACE:include>"
)

# IDEs should put the headers in a nice place
source_group(TREE "${PROJECT_SOURCE_DIR}/include" PREFIX "Header Files" FILES ${HEADER_LIST})

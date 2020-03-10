CMAKE_MINIMUM_REQUIRED(VERSION 3.10)

# Set the project name & version
PROJECT(InterfaceSample VERSION 1.0)

# specify the C++ standard
set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED True)

# set installation directory
set(CMAKE_INSTALL_PREFIX "Z:/usr/GitHub/utility-files/C++/CMakeTutorial/bin")

# should we use our own math functions
option(USE_MYMATH "Use our own sqrt implementation" ON)

# Make our math sqrt library optional
if (USE_MYMATH)
  # Add MathFunctions subdirectory
  add_subdirectory(MathFunctions)

  # Classic way to append
  # Note the use of the variable EXTRA_LIBS to collect up any optional libraries to later be linked into the
  # executable. The variable EXTRA_INCLUDES is used similarly for optional header files
  list(APPEND EXTRA_LIBS mysqrt)

  # Now that we’ve specified usage requirements for MathFunctions we can safely remove our uses of the EXTRA_INCLUDES
  # variable from the top-level CMakeLists.txt
endif()

# Add executable
add_executable(Main src/main.cpp)

# Specify libraries or flags to use when linking a given target and/or its dependents
target_link_libraries(Main PUBLIC ${EXTRA_LIBS})

# Configure a header file to pass the version number to the source code
# Also pass other pre-processor macros
configure_file(src/TutorialConfig.h.in TutorialConfig.h)

# Add directory to list of paths to search for include files
# Now that we’ve specified usage requirements for MathFunctions we can safely remove our uses of the EXTRA_INCLUDES
# variable from the top-level CMakeLists.txt
target_include_directories(Main PUBLIC
  "${PROJECT_BINARY_DIR}"
)

install(TARGETS Main DESTINATION bin)
install(FILES "${PROJECT_BINARY_DIR}/TutorialConfig.h"
  DESTINATION include
)

# We’re going to use the new defines in TutorialConfig.h.in,
# so be sure to set them before that file is configured.
include(CheckSymbolExists)
#set(CMAKE_REQUIRED_LIBRARIES "m")
check_symbol_exists(log "math.h" HAVE_LOG)
check_symbol_exists(exp "math.h" HAVE_EXP)

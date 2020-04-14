add_library(fibonacci SHARED fibonacci.cpp)

# We need this directory, and users of this library will need it too
target_include_directories(fibonacci
  PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})

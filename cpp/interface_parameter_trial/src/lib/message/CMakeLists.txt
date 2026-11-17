add_library(message
  invalid_message.cpp
  valid_message.cpp
)

target_include_directories(message PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})
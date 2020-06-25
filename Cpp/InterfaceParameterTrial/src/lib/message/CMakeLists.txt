add_library(message
  InvalidMessage.cpp
  ValidMessage.cpp
)

target_include_directories(message PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})
add_library(subscriber cond_subscriber.cpp)

target_link_libraries(subscriber PUBLIC message)
target_include_directories(message PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})

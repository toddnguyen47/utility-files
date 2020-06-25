add_library(subscriber CondSubscriber.cpp)

target_link_libraries(subscriber PUBLIC message)
target_include_directories(message PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})

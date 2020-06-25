add_executable(main main.cpp)
add_subdirectory(lib)

target_link_libraries(main message subscriber)

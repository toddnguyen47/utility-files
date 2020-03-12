cmake_minimum_required(VERSION 3.10)

add_executable(MainApp app.c)

# Include DumbExampleLib found in src/
target_link_libraries(MainApp PRIVATE DumbExampleLib)

add_executable(GoogleTestTrialProject main.cpp)

add_subdirectory(fibonacci)

target_link_libraries(GoogleTestTrialProject
  PRIVATE fibonacci)

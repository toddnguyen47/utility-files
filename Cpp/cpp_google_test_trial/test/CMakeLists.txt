cmake_minimum_required(VERSION 3.10)

add_executable(FibonacciUnitTest main_test.cpp
  fibonacci_test.cpp)

# target_include_directories(FibonacciUnitTest 
#   PUBLIC "../src")

target_link_libraries(FibonacciUnitTest gtest_main fibonacci)

# add_test(NAME TestingFibonacci COMMAND FibonacciUnitTest)

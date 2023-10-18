cmake_minimum_required(VERSION 3.10)

set(TESTNAME "FibonacciUnitTest")

add_executable(${TESTNAME} main_test.cpp
  fibonacci_test.cpp)

# target_include_directories(${TESTNAME} 
#   PUBLIC "../src")

target_link_libraries(${TESTNAME} gtest_main fibonacci)
add_test(NAME ${TESTNAME} COMMAND ${TESTNAME})

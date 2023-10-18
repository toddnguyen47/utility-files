cmake_minimum_required(VERSION 3.10)

set(CMAKE_CXX_STANDARD 11)

project(TerminalClockProj VERSION 1.0 DESCRIPTION "Terminal Clock Display")

add_executable(TerminalClock main.cpp terminal_clock.cpp)

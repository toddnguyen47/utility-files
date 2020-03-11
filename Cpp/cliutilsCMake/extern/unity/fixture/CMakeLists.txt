cmake_minimum_required(VERSION 3.0 FATAL_ERROR)

add_library(unity_fixture STATIC "src/unity_fixture.c")

# Inlude `unity` and `unity_fixture`
target_include_directories(unity_fixture
  PUBLIC ../
  PUBLIC ../memory/src)

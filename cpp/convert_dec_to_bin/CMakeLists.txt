cmake_minimum_required(VERSION 3.10)

set(CMAKE_C_STANDARD 11)

project(ConvertDecimalToBinaryProj 
  VERSION 1.0
  DESCRIPTION "Convert Decimal to Binary")

add_executable(ConvertDecimalToBinary main.c convert_dec_to_bin.c)

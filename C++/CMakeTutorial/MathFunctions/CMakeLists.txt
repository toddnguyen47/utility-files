add_library(mysqrt mysqrt.cpp)

# Remember INTERFACE means things that consumers require but the producer doesn’t.
target_include_directories(mysqrt 
    INTERFACE ${CMAKE_CURRENT_SOURCE_DIR})

# for MathFunctions we want to install the library and header file
install(TARGETS mysqrt DESTINATION lib)
# for the application we want to install the executable and configured header
install(FILES mysqrt.h DESTINATION include)

# So we know where `TutorialConfig.h` is located
target_include_directories(mysqrt 
  INTERFACE ${CMAKE_CURRENT_SOURCE_DIR}
  PRIVATE ${CMAKE_BINARY_DIR}
  )

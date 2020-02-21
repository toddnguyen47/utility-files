add_library(mysqrt mysqrt.cpp)

# Remember INTERFACE means things that consumers require but the producer doesn’t.
target_include_directories(mysqrt 
    INTERFACE ${CMAKE_CURRENT_SOURCE_DIR})

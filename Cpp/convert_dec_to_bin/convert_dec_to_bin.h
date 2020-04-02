#ifndef CONVERT_DEC_TO_BIN_H_
#define CONVERT_DEC_TO_BIN_H_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// "public"
char *convertDecimalToBinary(int number);

// "private"
char *reverse_buffer_into_smaller_buffer(char *src, int dest_size);

#endif // CONVERT_DEC_TO_BIN_H_

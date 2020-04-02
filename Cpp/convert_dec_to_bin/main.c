/******************************************************************************

                            Online C Compiler.
                Code, Compile, Run and Debug C program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include "convert_dec_to_bin.h"

char *convertDecimalToBinary(int number);
void printBinary(int decimal);
char *reverse_buffer_into_smaller_buffer(char *src, int dest_size);

int main()
{
  printBinary(10);
  printBinary(4);
  printBinary(20);
  return 0;
}

void printBinary(int decimal)
{
  char *binary = convertDecimalToBinary(decimal);
  printf("%s\n", binary);
  free(binary);
}

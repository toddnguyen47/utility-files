#include "convert_dec_to_bin.h"

/**
 * Please remember to free() after this function call!
 */
char *convertDecimalToBinary(int number)
{
  char *buffer = (char *)malloc(sizeof(char) * 100);
  buffer[0] = '\0';
  int size = 0;
  while (number > 0)
  {
    int mod_num = number % 2;
    number = number >> 1;
    char temp_buffer[100];
    sprintf(temp_buffer, "%d", mod_num);
    buffer = strcat(buffer, temp_buffer);
    size += 1;
  }

  char *binary_buffer = reverse_buffer_into_smaller_buffer(buffer, size);
  free(buffer);

  return binary_buffer;
}

char *reverse_buffer_into_smaller_buffer(char *src, int dest_size)
{
  // Add 1 more to dest_size to account for null terminator
  char *binary_buffer = malloc(sizeof(char) * (dest_size + 1));
  int i;
  for (i = 0; i < dest_size; i++)
    binary_buffer[i] = src[dest_size - i - 1];
  binary_buffer[dest_size] = '\0';
  return binary_buffer;
}

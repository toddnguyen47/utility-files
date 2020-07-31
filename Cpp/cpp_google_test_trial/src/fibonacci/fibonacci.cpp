#include "fibonacci.hpp"

Fibonacci::Fibonacci()
{
  data_.push_back(0);
  data_.push_back(1);
}

int Fibonacci::calculate(int sequence_index)
{
  for (int i = FIBONACCI_STARTING_INDEX_; i < sequence_index; i++)
    data_.push_back(data_.at(i - 1) + data_.at(i - 2));
  return data_[sequence_index - 1];
}

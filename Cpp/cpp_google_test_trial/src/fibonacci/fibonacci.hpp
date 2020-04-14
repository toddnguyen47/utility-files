#ifndef FIBONACCI_H_
#define FIBONACCI_H_

#include <vector>

class Fibonacci
{
public:
  Fibonacci();
  int calculate(int sequence_index);

private:
  int FIBONACCI_STARTING_INDEX_ = 2;
  std::vector<int> data_;
};

#endif // FIBONACCI_H_
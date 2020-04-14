#include "gtest/gtest.h"
#include "fibonacci.hpp"

// Ref: https://github.com/google/googletest/blob/master/googletest/samples/sample1_unittest.cc
namespace
{

/**
 * Ref: https://stackoverflow.com/a/3549665
 */
class FibonacciTest : public ::testing::Test
{
protected:
  Fibonacci *fibonacci;
  virtual void SetUp()
  {
    fibonacci = new Fibonacci();
  }

  virtual void TearDown()
  {
    delete fibonacci;
  }
};

TEST_F(FibonacciTest, TestFirst0Numbers)
{
  EXPECT_EQ(0, fibonacci->calculate(1));
}

TEST_F(FibonacciTest, TestFirst1Numbers)
{
  EXPECT_EQ(1, fibonacci->calculate(2));
}

TEST_F(FibonacciTest, TestFirst2Numbers)
{
  EXPECT_EQ(1, fibonacci->calculate(3));
}

TEST_F(FibonacciTest, TestFirst3Numbers)
{
  EXPECT_EQ(2, fibonacci->calculate(4));
}

TEST_F(FibonacciTest, TestFirst5Numbers)
{
  // 0, 1, 1, 2 --> 1 + 2 = 3
  EXPECT_EQ(3, fibonacci->calculate(5));
}

TEST_F(FibonacciTest, TestFirst7Numbers)
{
  // 0, 1, 1, 2, 3, 5, 8
  EXPECT_EQ(8, fibonacci->calculate(7));
}

TEST_F(FibonacciTest, TestFirst10Numbers)
{
  // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
  // 1, 2, 3, 4, 5, 6, 7,  8,  9, 10
  EXPECT_EQ(34, fibonacci->calculate(10));
}

} // namespace
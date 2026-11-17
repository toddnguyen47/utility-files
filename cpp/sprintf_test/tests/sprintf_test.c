#include "unity/fixture/src/unity_fixture.h"

TEST_GROUP(SprintfGroup);

TEST_SETUP(SprintfGroup) {}

TEST_TEAR_DOWN(SprintfGroup) {}

TEST(SprintfGroup, TestNameWhoaaaa)
{
  char output[5];

  TEST_ASSERT_EQUAL(3, sprintf(output, "hey"));
  TEST_ASSERT_EQUAL_STRING("hey", output);
}

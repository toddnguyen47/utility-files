#include "unity/fixture/src/unity_fixture.h"
TEST_GROUP_RUNNER(SprintfGroup)
{
  RUN_TEST_CASE(SprintfGroup, TestNameWhoaaaa);
}

static void runAllTests()
{
  RUN_TEST_GROUP(SprintfGroup);
}

int main(int argc, char *argv[])
{
  UnityMain(argc, argv, runAllTests);
}

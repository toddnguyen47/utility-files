#include <stdbool.h>
#include "unity.h"
#include "foo.h"

#include "mock_database_layer.h"

void setUp(void){};
void tearDown(void){};

void test_stuff()
{
  TEST_ASSERT_EQUAL(1, return_one());
}

void test_mocking_db_get_id()
{
  int expected_id = 1;
  const char *name = "Jane Doe";

  // MOCK START
  // Start by saying what our expectations are,
  // and what we want to return
  get_id_ExpectAndReturn(name, expected_id);
  // MOCK END

  // Actual function under test
  int actual_id = get_id(name);

  TEST_ASSERT_EQUAL(expected_id, actual_id);
}

void test_db_get_db()
{
  const char *db_name = "Database Name";
  // MOCK START
  // Start by saying what our expectations are,
  // and what we want to return
  open_db_Expect(db_name);
  // MOCK END

  // Actual function under test
  // CMock will check that `open_db` is called and is passed a `db_name`
  open_db(db_name);
}

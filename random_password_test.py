import pytest
from random_password import RandomPassword


@pytest.fixture(scope="function")
def random_password():
    random_password = RandomPassword()
    yield random_password
    print("Teardown now!")


def string_is_all_lowercase(str_input: str) -> bool:
    return all(x.islower() for x in str_input)


def test_generate_lowercase_password(random_password):
    s = random_password.generate_random_lowercase_list()
    assert len(s) > 0
    assert string_is_all_lowercase(s)

def test_password_has_one_uppercase(random_password):
    list1 = random_password.generate_random_lowercase_list()
    list1 = random_password.uppercase_one_char(list1)
    assert len(list1) > 0
    assert any(x.isupper() for x in list1)
    assert random_password._password_length - 1 == len(random_password._index_to_replace)

def test_password_has_one_number(random_password):
    list1 = random_password.generate_random_lowercase_list()
    list1 = random_password.uppercase_one_char(list1)
    list1 = random_password.replace_one_char_with_number(list1)
    assert len(list1) > 0
    assert any(x.isdigit() for x in list1)
    assert random_password._password_length - 2 == len(random_password._index_to_replace)

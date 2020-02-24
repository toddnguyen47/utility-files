import pytest
from random_password import RandomPassword


@pytest.fixture(scope="function")
def random_password():
    random_password = RandomPassword()
    random_password.reset_index_to_replace()
    yield random_password
    print("Teardown now!")


def string_is_all_lowercase(str_input: str) -> bool:
    return all(x.islower() for x in str_input)


def test_generate_lowercase_password(random_password):
    random_password.set_current_random_password_all_lowercase()
    s = random_password.current_random_password_list
    assert len(s) > 0
    assert string_is_all_lowercase(s)


def test_password_has_one_uppercase(random_password):
    random_password.set_current_random_password_all_lowercase()
    random_password.uppercase_one_char()

    list1 = random_password.current_random_password_list
    assert len(list1) > 0
    assert any(x.isupper() for x in list1)
    assert random_password._password_length - \
        1 == len(random_password._index_to_replace)


def test_password_has_two_numbers(random_password):
    random_password.set_current_random_password_all_lowercase()
    random_password.uppercase_one_char()
    random_password.replace_two_chars_with_number()
    list1 = random_password.current_random_password_list
    assert len(list1) > 0
    assert 2 == sum(1 for x in list1 if x.isdigit())
    assert any(x.isdigit() for x in list1)
    assert random_password._password_length - 3 == len(random_password._index_to_replace)


def test_password_has_two_special_chars(random_password):
    random_password.set_current_random_password_all_lowercase()
    random_password.uppercase_one_char()
    random_password.replace_two_chars_with_number()
    random_password.replace_two_chars_with_special_chars()

    list1 = random_password.current_random_password_list
    assert len(list1) > 0
    assert 2 == sum(
        1 for x in list1 if x in random_password.SpecialCharsReplacement()._special_chars_tuple)
    assert 2 == sum(1 for x in list1 if x.isdigit())
    assert any(x.isdigit() for x in list1)
    assert random_password._password_length - 5 == len(random_password._index_to_replace)


def test_five_replacements(random_password):
    # Arrange
    random_password.set_current_random_password_all_lowercase()
    # Act
    random_password.replace_five_lowercase_chars()
    # Assert
    list1 = random_password.current_random_password_list
    assert len(list1) > 0
    assert 2 == sum(
        1 for x in list1 if x in random_password.SpecialCharsReplacement()._special_chars_tuple)
    assert 2 == sum(1 for x in list1 if x.isdigit())
    assert any(x.isdigit() for x in list1)
    assert random_password._password_length - 5 == len(random_password._index_to_replace)

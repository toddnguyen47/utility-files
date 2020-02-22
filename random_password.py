import string
import random
from typing import List


class RandomPassword:
    class IReplacement:
        def replace(self, str_input: str) -> str:
            raise NotImplementedError

    class UpperReplacement(IReplacement):
        def replace(self, str_input: str) -> str:
            return str_input.upper()

    class DigitReplacement(IReplacement):
        def replace(self, str_input: str) -> str:
            return str(random.randrange(9 + 1))

    class SpecialCharsReplacement(IReplacement):
        def __init__(self):
            self._special_chars_tuple = ("!", "@", "#", "$", "%")

        def replace(self, str_input: str) -> str:
            return random.choice(self._special_chars_tuple)

    def __init__(self):
        self._base_filename = "random"
        self._max_passwords = 200
        self._max_files = 4
        self._password_length = 12
        self._index_to_replace = [i for i in range(self._password_length)]
        self.current_random_password_list: List = []

    def generate_password(self):
        pass

    def set_current_random_password(self) -> List:
        self.current_random_password_list = [random.choice(
            string.ascii_lowercase) for _ in range(self._password_length)]

    def replace_five_lowercase_chars(self, list_input: List[str]) -> List:
        pass

    def uppercase_one_char(self):
        self._generic_replace(self.UpperReplacement())

    def replace_two_chars_with_number(self):
        self._generic_replace(self.DigitReplacement(), n_times=2)

    def replace_two_chars_with_special_chars(self):
        self._generic_replace(self.SpecialCharsReplacement(), n_times=2)

    def _generic_replace(self, replacement_object: IReplacement, n_times: int = 1):
        for _ in range(n_times):
            random_index = random.randrange(len(self._index_to_replace))
            char_index_to_replace = self._index_to_replace[random_index]
            self.current_random_password_list[char_index_to_replace] = replacement_object.replace(
                self.current_random_password_list[char_index_to_replace])
            del self._index_to_replace[random_index]


if __name__ == "__main__":
    random_password = RandomPassword()
    # random_password.execute()

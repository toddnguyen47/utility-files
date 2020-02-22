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

    def __init__(self):
        self._special_chars_tuple = ("!", "@", "#", "$", "%")
        self._string_desired = "".join(
            (string.ascii_lowercase, string.digits) + self._special_chars_tuple)
        self._base_filename = "random"
        self._max_passwords = 200
        self._max_files = 4
        self._password_length = 12
        self._index_to_replace = [i for i in range(self._password_length)]

    def generate_password(self):
        pass

    def generate_random_lowercase_list(self) -> List:
        list_of_chars = [random.choice(
            string.ascii_lowercase) for _ in range(self._password_length)]
        return list_of_chars

    def uppercase_one_char(self, list_input: List[str]) -> List:
        return self._generic_replace(list_input, self.UpperReplacement())

    def replace_one_char_with_number(self, list_input: List[str]) -> List:
        return self._generic_replace(list_input, self.DigitReplacement(), n_times=2)

    def _generic_replace(
            self, list_input: List[str], replacement_object: IReplacement,
            n_times: int = 1) -> List:
        for _ in range(n_times):
            random_index = random.randrange(len(self._index_to_replace))
            char_index_to_replace = self._index_to_replace[random_index]
            list_input[char_index_to_replace] = replacement_object.replace(
                list_input[char_index_to_replace])
            del self._index_to_replace[random_index]
        return list_input


if __name__ == "__main__":
    random_password = RandomPassword()
    # random_password.execute()

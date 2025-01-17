import string
from typing import List
import secrets


class RandomPassword:
    class IReplacement:
        def replace(self, str_input: str) -> str:
            raise NotImplementedError

    class UpperReplacement(IReplacement):
        def replace(self, str_input: str) -> str:
            return str_input.upper()

    class DigitReplacement(IReplacement):
        def replace(self, str_input: str) -> str:
            return str(secrets.randbelow(9 + 1))

    class SpecialCharsReplacement(IReplacement):
        def __init__(self):
            self._special_chars_tuple = ("!", "@", "#", "$", "%")

        def replace(self, str_input: str) -> str:
            return secrets.choice(self._special_chars_tuple)

    def __init__(self):
        self._base_filename = "random"
        self._max_passwords = 200
        self._max_files = 4
        self._password_length = 16
        self._max_special_chars = 3
        self._min_special_chars = 2
        self._max_amount_of_numbers = 3
        self._min_amount_of_numbers = 2
        self._static_index_to_replace = [i for i in range(self._password_length)]
        self._index_to_replace: List = []
        self.current_random_password_list: List = []

    def execute(self):
        for i in range(1, self._max_files + 1):
            filename = "".join((self._base_filename, str(i), ".txt"))
            self.output_passwords_to_file(filename)
        print("Finished!")

    def output_passwords_to_file(self, filename: str):
        with open(filename, "w") as text_file:
            for j in range(0, self._max_passwords):
                temp_str = "{:03d}. {}\n".format(j + 1, self.generate_password())
                text_file.write(temp_str)

    def generate_password(self) -> str:
        self.reset_index_to_replace()
        self.set_current_random_password_all_lowercase()
        self.replace_some_lowercase_chars()
        return "".join(self.current_random_password_list)

    def set_current_random_password_all_lowercase(self):
        self.current_random_password_list = [
            secrets.choice(string.ascii_lowercase) for _ in range(self._password_length)
        ]

    def replace_some_lowercase_chars(self):
        self.uppercase_one_char()
        self.replace_chars_with_number()
        self.replace_chars_with_special_chars()

    def reset_index_to_replace(self):
        self._index_to_replace = self._static_index_to_replace.copy()

    def uppercase_one_char(self):
        self._generic_replace(self.UpperReplacement())

    def replace_chars_with_number(self):
        n_times = secrets.choice(
            [self._min_amount_of_numbers, self._max_amount_of_numbers]
        )
        self._generic_replace(self.DigitReplacement(), n_times=n_times)

    def replace_chars_with_special_chars(self):
        n_times = secrets.choice([self._min_special_chars, self._max_special_chars])
        self._generic_replace(self.SpecialCharsReplacement(), n_times=n_times)

    def _generic_replace(self, replacement_object: IReplacement, n_times: int = 1):
        for _ in range(n_times):
            random_index = secrets.randbelow(len(self._index_to_replace))
            char_index_to_replace = self._index_to_replace[random_index]
            self.current_random_password_list[char_index_to_replace] = (
                replacement_object.replace(
                    self.current_random_password_list[char_index_to_replace]
                )
            )
            del self._index_to_replace[random_index]


if __name__ == "__main__":
    random_password = RandomPassword()
    random_password.execute()

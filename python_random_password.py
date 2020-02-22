import string
import random


class RandomPassword:
    def __init__(self):
        self._special_chars_tuple = ("!", "@", "#", "$", "%")
        self._string_desired = "".join(
            (string.ascii_lowercase, string.digits) + self._special_chars_tuple)
        self._base_filename = "random"

    def replace_char(
            self, generated_pw: list, counter: int, max_counter: int,
            reg_chars_index_list: list, list_to_draw_from: list):
        """Replace a character.

        Args:
            generated_pw (int): The list of characters of the current random password
            counter (int): The current counter
            max_counter (int): The max counter
            reg_chars_index_list (list): list of indices of regular characters
            list_to_draw_from (list): These characters will replace normal characters
        """
        diff = max_counter - counter
        for i in range(diff):
            temp_len = len(reg_chars_index_list)

            # Only if the length of reg_chars_index_list is larger than 1
            if (temp_len <= 1):
                break

            # First, get a random index number from the list of indices
            rand_int = random.randint(0, temp_len - 1)
            # Then, store the random index in a variable
            random_index = reg_chars_index_list[rand_int]

            # Delete that index from the regular character index list
            del reg_chars_index_list[rand_int]
            temp_len = len(reg_chars_index_list)

            random_special_char = random.choice(list_to_draw_from)
            generated_pw[random_index] = random_special_char

    def get_password(self, size: int = 12):
        """Generate a password.
        """
        at_least_x_numbers = 2
        at_least_x_special_chars = 2

        # Have at least 2 special characters and 2 numbers
        number_counter = 0
        special_chars_counter = 0

        # Keep track of indices that are regular characters
        # We will use this list later to randomly replace special characters
        reg_chars_index_list = []

        # Generate the random password
        generated_pw = [random.choice(self._string_desired)
                        for _ in range(size)]

        # Count number of numbers and special characters
        for i in range(size):
            char = generated_pw[i]
            if (char.isdigit()):
                number_counter += + 1
            elif char in self._special_chars:
                special_chars_counter += 1
            else:
                # Just a regular character
                reg_chars_index_list.append(i)

        # Less than x numbers
        if number_counter < at_least_x_numbers:
            self.replace_char(generated_pw, number_counter,
                              at_least_x_numbers, reg_chars_index_list, string.digits)

        # If less than x special characters
        if special_chars_counter < at_least_x_special_chars:
            self.replace_char(generated_pw, special_chars_counter,
                              at_least_x_special_chars, reg_chars_index_list, special_chars)

        # Randomly choose a character to capitalize
        # First, get a random index number from the list of indices
        temp_len = len(reg_chars_index_list)
        if (temp_len > 1):
            rand_int = random.randint(0, temp_len - 1)
            # Then, store the random index in a variable
            random_index = reg_chars_index_list[rand_int]
            # Capitalize
            generated_pw[random_index] = generated_pw[random_index].upper()

        generated_pw = "".join(generated_pw)
        return generated_pw

    def output_to_file(self, filename):
        for i in range(1, max_files + 1):
            filename = "".join((self._base_filename, str(i), ".txt2"))
            with open(filename, "w") as text_file:
                for j in range(0, max_passwords):
                    temp_str = "{:03d}. {}\n".format(
                        j + 1, self.get_password())
                    text_file.write(temp_str)


max_passwords = 200
max_files = 4

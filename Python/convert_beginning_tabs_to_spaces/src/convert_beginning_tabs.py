import re
import os

_TMP_FILE = "tmp"
_ENCODING = "utf-8"


class ConvertBeginningTabs:
    def __init__(self) -> None:
        self._pattern = re.compile(r"(^\t+)([\S\s]*)")

    def convert_on_files_with_ext(
        self, file_path: str, num_spaces: int, file_extension: str = ""
    ):
        """Convert beginning tabs to spaces in files in `file_path`.

        Args:
            `file_path (str)`: The path where your files are located. Note that this function will recursively walk
            starting from this file_path.

            `num_spaces (int)`: Number of spaces used to replace beginning tabs with.

            `file_extension (str)`: Optional. If not given, all files will have their beginning tabs converted to
            spaces. If given, only the files with those extensions will have their beginning tabs converted. You do
            not need to include the period (.) in the extension. Simply pass in `java` instead of `.java`. Also,
            this extension is NOT case sensitive.
        """
        file_extension = file_extension.strip().lower()
        for root, _, files in os.walk(file_path):
            for file in files:
                file_name_lowercase = file.lower()
                if file_name_lowercase.endswith(file_extension):
                    full_path = os.path.join(root, file)
                    self._convert_file(full_path, num_spaces)

    def convert_tabs_to_spaces(self, input_line: str, num_spaces: int) -> str:
        matcher = self._pattern.match(input_line)
        return_str = ""

        if matcher is None:
            return_str = input_line
        else:
            tabs_str = matcher.group(1)
            space_per_tab = self._generate_space(num_spaces)
            space_str = tabs_str.replace("\t", space_per_tab)
            return_str = space_str + matcher.group(2)

        return return_str.rstrip()

    # *****************************************************
    # PRIVATE FUNCTIONS
    # *****************************************************

    def _generate_space(self, num_spaces: int) -> str:
        list1 = []
        space_str = ""
        for _ in range(num_spaces):
            list1.append(" ")
        return space_str.join(list1)

    def _convert_file(self, full_path: str, num_spaces: int):
        print("Operating on '{}'".format(full_path.replace("\\", "/")))
        with open(full_path, mode="rb") as input_file:
            with open(_TMP_FILE, mode="wb") as output_file:
                for line in input_file:
                    line = line.decode(encoding=_ENCODING)
                    converted_line = self.convert_tabs_to_spaces(line, num_spaces)
                    output_file.write((converted_line + "\n").encode(_ENCODING))
        self._overwrite_input_file(full_path)

    def _overwrite_input_file(self, full_path: str):
        os.remove(full_path)
        os.rename(_TMP_FILE, full_path)

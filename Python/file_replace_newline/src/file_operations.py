from typing import List
import re

_non_whitespace = re.compile("\S")


class FileOperations:
    def __init__(self, file_path: str):
        self._file_path: str = file_path
        self._lines: List[str] = []

    def execute(self):
        self._read_in_file()
        self._trim_all_lines()
        self._output_back_to_file()

    def _read_in_file(self):
        with open(self._file_path, "r") as file:
            self._lines = [line.rstrip("\n") for line in file]

    def _trim_all_lines(self):
        for index, line in enumerate(self._lines):
            match_obj = re.search(_non_whitespace, line)
            if match_obj is None:
                # If match_obj is None, then the line has no non-whitespace character
                self._lines[index] = self._handle_lines_with_only_whitespace(line)
            else:
                self._lines[index] = self._handle_lines_with_characters(line)
        print(self._lines)

    def _output_back_to_file(self):
        with open(self._file_path, "w") as file:
            for line in self._lines:
                file.write(line)
                file.write("\n")

    def _handle_lines_with_only_whitespace(self, line: str) -> str:
        return line.strip()

    def _handle_lines_with_characters(self, line: str) -> str:
        return line.rstrip()

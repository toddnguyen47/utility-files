"""Convert Go constants to Java constants."""

import re


class Main:
    """Main class"""

    _COMMENT_TO_LOOK_FOR = "// Logging constants"
    _ONE_SPACE = " "
    _RE_TABS_SPACES_CONST = re.compile(r"const[\t ]+\(")
    _RE_CLOSING_PARENTHESES = re.compile(r"[\t ]*\)")
    _RE_TABS_SPACES = re.compile(r"[\t ]+")
    _OUTPUT_JAVA_FILE = "TrafficUtilities.java"

    def main(self):
        """main function."""

    def _handle_one_line(self, java_lines: list[str], line: str) -> list[str]:
        line = self._RE_TABS_SPACES.sub(self._ONE_SPACE, line)
        tokens = line.split(self._ONE_SPACE)
        # convert camelCase to UPPER_SNAKE_CASE
        tokens[0] = self._convert_to_upper_snake_case(tokens[0])
        str1 = f"\tpublic static final String {tokens[0]} = {tokens[-1]};"
        java_lines.append(str1)
        return java_lines

    def _convert_to_upper_snake_case(self, camel_case: str) -> str:
        l1 = []
        for i, _ in enumerate(camel_case):
            if i == 0:
                l1.append(camel_case[i].upper())
            else:
                if camel_case[i].isupper() and camel_case[i - 1].islower():
                    l1.append("_")
                l1.append(camel_case[i].upper())
        return "".join(l1)


if __name__ == "__main__":
    main = Main()
    main.main()

import re


class ConvertBeginningTabs:
    def __init__(self) -> None:
        self._pattern = re.compile(r"(^\t*)([\S\s]*)")

    def convert(self, input_line: str, num_spaces: int) -> str:
        matcher = self._pattern.match(input_line)

        if matcher is None:
            return input_line
        else:
            tabs_str = matcher.group(1)
            space_per_tab = self._generate_space(num_spaces)
            space_str = tabs_str.replace("\t", space_per_tab)
            return space_str + matcher.group(2)

    def _generate_space(self, num_spaces: int) -> str:
        list1 = []
        space_str = ""
        for _ in range(num_spaces):
            list1.append(" ")
        return space_str.join(list1)

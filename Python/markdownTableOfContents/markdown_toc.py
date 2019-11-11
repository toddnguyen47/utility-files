import re
import argparse
import sys


class MarkdownToc:
    """
    Create a Table of Content (TOC) for Markdown files.
    """
    def __init__(self, filepath):
        """
        Constructor.

        :param filepath: The path of the README file.
        """
        self.filepath = filepath
        self.list_of_lines = []
        self.toc_list = []
        self.output_filename = "toc.md"
        self.cur_line = ""

        self.flag_codeblock = False
        self._codeblock_str = "```"

    def invoke(self):
        """
        Main invoke/execute function. Usage:
        `python3 markdown_toc.py /path/to/markdown/file`
        """
        self.read_in_file()
        self.parse_file()
        self.output_to_file()


    def read_in_file(self) -> list:
        """
        Read in the file.

        :return: A list of "\n" separated lines
        """
        with open(self.filepath, "r") as file:
            for line in file:
                self.list_of_lines.append(line.replace("\n", ""))


    def parse_file(self):
        """
        Go through the list of lines and parse the file.

        :returns: A list of Table of Content string
        """
        self.toc_list = ["# Table of Contents"]
        for line in self.list_of_lines:
            self.cur_line = line
            if self.cur_line == self._codeblock_str:
                self.flag_codeblock = not self.flag_codeblock

            self.check_header_line()


    def check_header_line(self):
        if not self.flag_codeblock and self.does_line_start_with_pound():
            toc_line = self.convert_to_toc()
            self.toc_list.append(toc_line)


    def does_line_start_with_pound(self) -> bool:
        """
        Check if the line starts with hashtags "#" and a space.

        :param line: The current line to look at.
        :return: true if line starts with a single pound, false otherwise
        """
        pattern = r"^#+ "
        prog = re.compile(pattern)
        return prog.match(self.cur_line)


    def convert_to_toc(self) -> str:
        """
        Convert to a table of content targeted line

        :param line: The line to convert
        :return: A string converted to a table of content format
        """
        content = self.convert_curline_to_content()
        href = self.convert_curline_to_href()
        temp_str = "{0}{1}".format(content, href)

        return temp_str


    def convert_curline_to_href(self) -> str:
        """
        Convert a line to href by:
            - stripping out all special characters except A-Z, a-z, 0-9, spaces.
            - lowercase everything
            - take out any whitespace on the left and right side
            - replace spaces with -
            - add a "#" in front

        :param line: The line to convert
        :return: The TOC converted line.
        """
        temp_str = self.extract_special_chars_then_lower_case()

        temp_str = "#" + temp_str.strip().replace(" ", "-")
        return "(" + temp_str + ")"


    def extract_special_chars_then_lower_case(self) -> str:
        """
        Extract out special characters, then lowercase it.
        :param line: The line to extract special characters from
        :return: The extracted line
        """
        pattern = r"[A-Za-z0-9_\- ]"
        prog = re.compile(pattern)
        temp_str = ""
        for char in self.cur_line:
            if prog.match(char):
                temp_str = "".join((temp_str, char.lower()))
        return temp_str


    def output_to_file(self):
        """
        Output to a file.
        """
        with open(self.output_filename, "w") as file:
            for line in self.toc_list:
                file.write(line)
                file.write("\n")

        print("Finished writing to {0}".format(self.output_filename))


    def convert_curline_to_content(self) -> str:
        temp_str = self.cur_line.replace("#", "").strip()
        temp_str = "".join((self.get_prefix_whitespace(), "- [", temp_str, "]"))
        return temp_str


    def get_prefix_whitespace(self):
        hashtag_count = self.get_hashtag_count_in_curline()
        list1 = ["  "] * (hashtag_count - 1)

        return "".join(list1)


    def get_hashtag_count_in_curline(self) -> int:
        count = 0
        for ch1 in self.cur_line:
            if ch1 == "#":
                count += 1

        return count


if __name__ == "__main__":
    # Ref: https://stackoverflow.com/a/3853776#comment36687355_3853776
    parser = argparse.ArgumentParser(
        description="Create a TOC for a Markdown file. Example usage:\n" +
        "$ python3 markdown_toc.py /path/to/markdown/file",
        formatter_class=argparse.RawDescriptionHelpFormatter
    )
    parser.add_argument("filepath", help="The path to the markdown file", type=str)

    # If the user does not supply any command line args
    if len(sys.argv) < 2:
        print("ERROR: Please supply a filepath.")
        parser.print_help()
        exit(-1)

    args = parser.parse_args()

    markdown_toc = MarkdownToc(args.filepath)
    markdown_toc.invoke()

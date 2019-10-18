import unittest
import markdown_toc


class TestMarkdownToc(unittest.TestCase):
    """
    Unit test class. Run with the following command:
    `python -m unittest discover ./test/`
    """
    def setUp(self) -> None:
        self.filepath = "test.md"
        self.markdown_toc_obj = markdown_toc.MarkdownToc(self.filepath)

    def test_read_in_file(self):
        self.markdown_toc_obj.read_in_file()
        list_of_lines = self.markdown_toc_obj.list_of_lines
        self.assertEqual("- https://stackoverflow.com/a/23332517", list_of_lines[1])
        self.assertEqual("sudo apt install cmake build-essential", list_of_lines[22])
        self.assertEqual("- You're all set!", list_of_lines[76])

    def test_does_line_start_with_pound(self):
        self.markdown_toc_obj.cur_line = "# References"
        self.assertTrue(self.markdown_toc_obj.does_line_start_with_pound())
        self.markdown_toc_obj.cur_line = "#        References"
        self.assertTrue(self.markdown_toc_obj.does_line_start_with_pound())
        self.markdown_toc_obj.cur_line = "## References"
        self.assertTrue(self.markdown_toc_obj.does_line_start_with_pound())
        self.markdown_toc_obj.cur_line = "##        References"
        self.assertTrue(self.markdown_toc_obj.does_line_start_with_pound())
        self.markdown_toc_obj.cur_line = "##References"
        self.assertFalse(self.markdown_toc_obj.does_line_start_with_pound())

    def test_convert_to_href(self):
        self.markdown_toc_obj.cur_line = "# Eclipse C/C++ Unit Test Launch Configuration"
        self.assertEqual("(#eclipse-cc-unit-test-launch-configuration)",
                         self.markdown_toc_obj.convert_curline_to_href())
        self.markdown_toc_obj.cur_line = "## References"
        self.assertEqual("(#references)",
                         self.markdown_toc_obj.convert_curline_to_href())

    def test_convert_to_toc(self):
        self.markdown_toc_obj.cur_line = "# References"
        self.assertEqual("- [References](#references)", self.markdown_toc_obj.convert_to_toc())
        self.markdown_toc_obj.cur_line = "# Eclipse C/C++ Unit Test Launch Configuration"
        self.assertEqual(
            "- [Eclipse C/C++ Unit Test Launch Configuration](#eclipse-cc-unit-test-launch-configuration)",
            self.markdown_toc_obj.convert_to_toc())
        self.markdown_toc_obj.cur_line = "## Eclipse C/C++ Unit Test Launch Configuration"
        self.assertEqual(
            "    - [Eclipse C/C++ Unit Test Launch Configuration](#eclipse-cc-unit-test-launch-configuration)",
            self.markdown_toc_obj.convert_to_toc())

    def test_get_hashtag_count(self):
        self.markdown_toc_obj.cur_line = "# References"
        self.assertEqual(1, self.markdown_toc_obj.get_hashtag_count_in_curline())
        self.markdown_toc_obj.cur_line = "### References"
        self.assertEqual(3, self.markdown_toc_obj.get_hashtag_count_in_curline())
        self.markdown_toc_obj.cur_line = "## References"
        self.assertEqual(2, self.markdown_toc_obj.get_hashtag_count_in_curline())

    def test_prepend_whitespace(self):
        self.markdown_toc_obj.cur_line = "# References"
        self.assertEqual("", self.markdown_toc_obj.get_prefix_whitespace())
        self.markdown_toc_obj.cur_line = "## References"
        self.assertEqual("    ", self.markdown_toc_obj.get_prefix_whitespace())
        self.markdown_toc_obj.cur_line = "### References"
        self.assertEqual("        ", self.markdown_toc_obj.get_prefix_whitespace())


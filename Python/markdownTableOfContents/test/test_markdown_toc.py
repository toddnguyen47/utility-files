import unittest
import markdown_toc


class TestMarkdownToc(unittest.TestCase):
    """
    Unit test class. Run with the following command:
    `python -m unittest discover ./test/`
    """
    def setUp(self) -> None:
        self.filepath = "/media/LinuxData/todd/usr/Github/UtilityFiles/EclipseGoogleTest/README.md"
        self.markdown_toc_obj = markdown_toc.MarkdownToc(self.filepath)

    def test_read_in_file(self):
        list_of_lines = self.markdown_toc_obj.read_in_file()
        self.assertEqual("- https://stackoverflow.com/a/23332517", list_of_lines[1])
        self.assertEqual("sudo apt install cmake build-essential", list_of_lines[22])
        self.assertEqual("- You're all set!", list_of_lines[76])

    def test_does_line_start_with_pound(self):
        self.assertTrue(self.markdown_toc_obj.does_line_start_with_pound("# References"))
        self.assertTrue(self.markdown_toc_obj.does_line_start_with_pound("#        References"))
        self.assertFalse(self.markdown_toc_obj.does_line_start_with_pound("## References"))
        self.assertFalse(self.markdown_toc_obj.does_line_start_with_pound("##        References"))

    def test_convert_to_href(self):
        self.assertEqual("#eclipse-cc-unit-test-launch-configuration",
                         self.markdown_toc_obj.convert_to_href("# Eclipse C/C++ Unit Test Launch Configuration"))
        self.assertEqual("#references",
                         self.markdown_toc_obj.convert_to_href("# References"))

    def test_convert_to_toc(self):
        self.assertEqual("[References](#references)", self.markdown_toc_obj.convert_to_toc("# References"))
        self.assertEqual(
            "[Eclipse C/C++ Unit Test Launch Configuration](#eclipse-cc-unit-test-launch-configuration)",
            self.markdown_toc_obj.convert_to_toc("# Eclipse C/C++ Unit Test Launch Configuration"))


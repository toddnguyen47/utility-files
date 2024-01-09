"""Fix CSS"""
import re
from pathlib import Path


class Main:
    """Main class"""

    # SETTINGS
    _PATH_INPUT_FILE = "Z:/DocumentsAndStuff/Desktop/bruh.css"

    # CONSTANTS
    _ENCODING = "utf-8"
    _RE_DIGITS_ONLY = re.compile(r"[\d\.]+")
    _PRECEDING_SPACES = "  "

    def main(self):
        """main function"""

        with open(self._PATH_INPUT_FILE, "r", encoding=self._ENCODING) as infile:
            data = infile.read()

        new_lines = []
        for line in data.split("\n"):
            tokens = line.split(":")
            if len(tokens) >= 3:
                new_lines.append(line)
                continue
            first_token = tokens[0].lower().strip()
            # check for font-size first
            if "font-size" in first_token:
                num = tokens[1]
                if "%" not in num:
                    font_size = self._RE_DIGITS_ONLY.search(tokens[1])
                    if font_size:
                        font_size = float(font_size.group(0))
                        if font_size < 1.0:
                            line = self._comment_out_line(line)
            elif "font-family" in first_token:
                font = tokens[1].lower()
                if "serif" in font or "sans-serif" in font or "monospace" in font:
                    line = self._comment_out_line(line)
            elif "text-align" in first_token:
                text_align = tokens[1].lower()
                if "justify" in text_align:
                    line = self._comment_out_line(line)
            new_lines.append(line)

        # output now
        path = Path(self._PATH_INPUT_FILE)
        output_filename = Path(path.parent, path.stem + "-output" + path.suffix)
        with open(output_filename, "w", encoding=self._ENCODING) as outfile:
            outfile.write("\n".join(new_lines))
        print(f"Finished writing to '{output_filename}'")

    def _comment_out_line(self, line: str) -> str:
        """comment out line"""
        return self._PRECEDING_SPACES + "/* " + line.strip() + " */"


if __name__ == "__main__":
    main = Main()
    main.main()

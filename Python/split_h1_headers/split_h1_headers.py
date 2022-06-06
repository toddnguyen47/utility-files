"""Split h1 headers"""

import os

_FILE_PATH = "Z:/DocumentsAndStuff/Desktop/1/blah.xhtml"
_OUTFILE = "Body"

_FILE_PATH_DIRNAME = os.path.dirname(_FILE_PATH)
_, _EXTENSION = os.path.splitext(_FILE_PATH)


def _main():
    """Main Function to execute"""

    with open(_FILE_PATH, "r", encoding="utf-8") as file:
        data = file.read()

    h1_lines = []
    lines = data.splitlines()
    for index, line in enumerate(lines):
        if r"<h1" in line:
            h1_lines.append(index)

    for i in range(len(h1_lines)):
        # If last file
        outfile_name = os.path.join(_FILE_PATH_DIRNAME, _OUTFILE + str(i) + _EXTENSION)
        if i == len(h1_lines) - 1:
            current_lines = lines[h1_lines[i] :]
        else:
            current_lines = lines[h1_lines[i] : h1_lines[i + 1]]
        print(f"Writing to {outfile_name}")
        with open(outfile_name, "w", encoding="utf-8") as outfile:
            outfile.write("\n".join(current_lines))


if __name__ == "__main__":
    _main()

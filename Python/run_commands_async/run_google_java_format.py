"""run google java format async"""

import concurrent.futures
import os
import re
import subprocess
from datetime import datetime

# region configuration

_DIRECTORIES = set([])
_COMMAND = (
    "/Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home/bin/java -jar "
    "/Users/tn6091/opt/google-java-format/google-java-format-1.15.0-all-deps.jar "
    "--skip-javadoc-formatting --replace"
)
_NUM_PROCESSES = 4
_EXTENSION = ".java"
_MULTILINE_COMMENT_CHAR = "*"

# endregion configuration

_RE_MULTIPLE_SPACES = re.compile(" +")
_NUM_SPACES = 2


def _main():
    """main function"""
    with concurrent.futures.ProcessPoolExecutor(max_workers=_NUM_PROCESSES) as executor:
        _run_per_executor(executor)


def _run_per_executor(executor: concurrent.futures.ProcessPoolExecutor):
    for dirpath in _DIRECTORIES:
        for root, _, files in os.walk(dirpath):
            for file in files:
                executor.submit(_run_commands, root, file, _EXTENSION)


def _run_commands(root: str, file: str, extension: str):
    """Run commands"""
    file_str = str(file)
    if extension is not None and file_str.endswith(extension):
        full_path = os.path.join(root, file_str)
        print(full_path)

        cmd = _COMMAND.replace("\n", "")
        cmd = _RE_MULTIPLE_SPACES.sub(" ", cmd)
        cmd = cmd.split(" ")
        cmd.append(full_path)
        subprocess.run(cmd, check=True, capture_output=True)

        _replace_beginning_spaces(full_path)


def _replace_beginning_spaces(full_path: str):
    """replace beginning spaces"""
    new_line_str = "\n"
    with open(full_path, "r+", encoding="utf-8") as file:
        new_lines: list[str] = []
        data = file.read()
        for line in data.split(new_line_str):
            new_line = _handle_per_line(line)
            new_lines.append(new_line)
        file.seek(0)
        file.write(new_line_str.join(new_lines))
        file.truncate()


def _handle_per_line(line: str) -> str:
    """replace beginning spaces"""
    count = 0
    char_list = list(line)
    stop_index = 0
    multiline_comment_char_count = 0
    for idx, char1 in enumerate(char_list):
        if char1.isspace():
            count += 1
        else:
            stop_index = idx
            if _MULTILINE_COMMENT_CHAR != "" and _MULTILINE_COMMENT_CHAR == char1:
                multiline_comment_char_count = 1
            break
    new_count = int(count / _NUM_SPACES)
    new_tokens = (
        ["\t" * new_count]
        + [" " * multiline_comment_char_count]
        + char_list[stop_index:]
    )
    return "".join(new_tokens)


if __name__ == "__main__":
    now = datetime.now().replace(microsecond=0)
    _main()
    end = datetime.now().replace(microsecond=0)
    print(f"Time difference: {end - now}")

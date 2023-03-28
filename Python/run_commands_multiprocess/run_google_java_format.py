"""run google java format async

Usage:

python3 run_google_java_format.py --config <full_path_of_config_file>

"""

import concurrent.futures
import os
import re
import subprocess
from datetime import datetime
import argparse
import yaml

_RE_MULTIPLE_SPACES = re.compile(" +")


class YamlConfig:  # pylint: disable=too-few-public-methods
    """model for config YAML file"""

    command: str
    num_spaces: int
    directories: list[str]
    num_processes: int
    extension: str
    multiline_comment_char: str

    def __init__(self, input_dict: dict[str, any]) -> None:
        self.command = input_dict["command"]
        self.num_spaces = input_dict["num_spaces"]
        self.directories = input_dict["directories"]
        self.num_processes = input_dict["num_processes"]
        self.extension = input_dict["extension"]
        self.multiline_comment_char = input_dict["multiline_comment_char"]


def _main():
    """main function"""

    config_path = _get_command_line_args()
    yaml_config = _get_yaml_config(config_path)

    with concurrent.futures.ProcessPoolExecutor(
        max_workers=yaml_config.num_processes
    ) as executor:
        _run_per_executor(executor, yaml_config)


def _get_command_line_args() -> str:
    """Get command line arguments"""
    parser = argparse.ArgumentParser(
        prog="run_google_java_format",
        description="Run Google's Java format using multiprocesses",
    )
    parser.add_argument(
        "-c", "--config", help="full path of config file", required=True
    )
    args = parser.parse_args()
    return args.config


def _get_yaml_config(full_path: str) -> YamlConfig:
    """get YAML config"""
    with open(full_path, "r", encoding="utf-8") as file:
        data = yaml.safe_load(file)
    yaml_config = YamlConfig(data)
    return yaml_config


def _run_per_executor(
    executor: concurrent.futures.ProcessPoolExecutor, yaml_config: YamlConfig
):
    cmd = yaml_config.command.replace("\n", "")
    cmd = _RE_MULTIPLE_SPACES.sub(" ", cmd)
    cmd = cmd.split(" ")
    for dirpath in yaml_config.directories:
        for root, _, files in os.walk(dirpath):
            for file in files:
                executor.submit(_run_commands, yaml_config, cmd, root, file)


def _run_commands(yaml_config: YamlConfig, cmd: list[str], root: str, file: str):
    """Run commands"""
    file_str = str(file)
    if yaml_config.extension is not None and file_str.endswith(yaml_config.extension):
        full_path = os.path.join(root, file_str)
        new_command = cmd + [full_path]
        print(new_command)
        subprocess.run(new_command, check=True, capture_output=True)

        _replace_beginning_spaces(yaml_config, full_path)


def _replace_beginning_spaces(yaml_config: YamlConfig, full_path: str):
    """replace beginning spaces"""
    new_line_str = "\n"
    with open(full_path, "r+", encoding="utf-8") as file:
        new_lines: list[str] = []
        data = file.read()
        for line in data.split(new_line_str):
            new_line = _handle_per_line(yaml_config, line)
            new_lines.append(new_line)
        file.seek(0)
        file.write(new_line_str.join(new_lines))
        file.truncate()


def _handle_per_line(yaml_config: YamlConfig, line: str) -> str:
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
            if (
                yaml_config.multiline_comment_char != ""
                and yaml_config.multiline_comment_char == char1
            ):
                multiline_comment_char_count = 1
            break
    new_count = int(count / yaml_config.num_spaces)
    remaining_tokens = char_list[stop_index:]
    remaining_tokens_str = "".join(remaining_tokens).strip()
    new_tokens = (
        ["\t" * new_count]
        + [" " * multiline_comment_char_count]
        + [remaining_tokens_str]
    )
    new_str = "".join(new_tokens)
    return new_str


if __name__ == "__main__":
    now = datetime.now()
    _main()
    end = datetime.now()
    print(f"Time difference: {end - now}")

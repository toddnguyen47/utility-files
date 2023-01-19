"""Run commands async"""

import argparse
import os
import subprocess
import concurrent.futures
import re

_re_multiple_spaces = re.compile(" +")


def _main():
    """main function"""
    parser = argparse.ArgumentParser(
        prog="run_commands_async",
        description="Running set of commands on a folder. Every file will be run asynchronously",
    )
    parser.add_argument(
        "--command",
        help="commands to run. supply multiple `--command` flags for each command",
        action="append",
        required=True,
    )
    parser.add_argument(
        "--dirpath", help="directory path to run the commands on", required=True
    )
    parser.add_argument("--ext", help="optional extension to pass")
    parser.add_argument(
        "--num-processes",
        help="number of processes we want to use. defaults to 4",
        default=4,
        type=int,
    )
    args = parser.parse_args()

    commands: list[str] = args.command
    dirpath = args.dirpath
    extension = args.ext
    num_processes = args.num_processes

    with concurrent.futures.ProcessPoolExecutor(max_workers=num_processes) as executor:
        for root, _, files in os.walk(dirpath):
            for file in files:
                executor.submit(_run_commands, root, file, extension, commands)


def _run_commands(root: str, file: str, extension: str, commands: list[str]):
    """Run commands"""
    file_str = str(file)
    if extension is not None and file_str.endswith(extension):
        full_path = os.path.join(root, file_str)
        print(full_path)

        for command in commands:
            cmd = command.replace("\n", "")
            cmd = _re_multiple_spaces.sub(" ", cmd)
            cmd = cmd.split(" ")
            cmd.append(full_path)
            subprocess.run(cmd, check=True, capture_output=True)


if __name__ == "__main__":
    _main()

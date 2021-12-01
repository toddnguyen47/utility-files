"""Execute `jq --slurp` command to convert json lines to JSON"""

import argparse
from typing import List
import subprocess

_PATH_OF_JQ = ""
_CMD = [_PATH_OF_JQ, "--slurp", "."]
_ENCODING = "utf-8"


def _execute_jq(filenames: List[str]):
    """Execute `jq --slurp` command"""
    cmd = list(_CMD)
    for filename in filenames:
        cmd.append(filename)
        print(" ".join(cmd))
        completed_process = subprocess.run(cmd, check=True, capture_output=True)
        with open(filename, "w", encoding=_ENCODING) as outfile:
            outfile.write(completed_process.stdout.decode(_ENCODING))
            print(f"Written to {filename}")
        cmd.pop()


def main():
    """Main function to execute"""
    parser = argparse.ArgumentParser()
    parser.add_argument(
        "filenames", help="JSON Lines files to convert", nargs=argparse.REMAINDER
    )

    args = parser.parse_args()
    _execute_jq(args.filenames)


if __name__ == "__main__":
    main()

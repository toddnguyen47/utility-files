"""Run google java format"""

import argparse
import subprocess
import concurrent.futures
import os
from pathlib import Path
import time


class Main:  # pylint: disable=too-few-public-methods
    """Main class"""

    _CHOICE_FILE = "file"
    _CHOICE_FOLDER = "folder"

    def run(self):
        """main function"""

        parser = argparse.ArgumentParser(
            prog="Google Java Format",
            description="Runs google java format on a file or a particular folder",
        )
        parser.add_argument("--mode", choices=[self._CHOICE_FILE, self._CHOICE_FOLDER])
        parser.add_argument(
            "--java-exec", help="full path to Java 11+ executable (java.exe)"
        )
        parser.add_argument("--google-jar", help="full path to google java format JAR")
        parser.add_argument(
            "--convert-to-tabs",
            help="full path of executable to convert to tabs",
            required=False,
        )
        parser.add_argument("path", help="path to file / folder")
        args = parser.parse_args()

        mode = args.mode
        if mode.lower() == self._CHOICE_FILE.lower():
            self._handle_file(args)
        elif mode.lower() == self._CHOICE_FOLDER.lower():
            self._handle_folder(args)

    def _handle_file(self, args: argparse.Namespace):
        """handle file"""
        java_command = self._get_java_command(args)
        convert_to_tabs_command = self._get_convert_to_tabs_command(args)
        path = args.path.strip()
        self._handle_file_helper(java_command, convert_to_tabs_command, path)

    def _handle_file_helper(
        self, java_command: list[str], convert_to_tabs_command: list[str], path: str
    ):
        """handle one file helper"""
        self._format_one_file(java_command, path)
        self._convert_to_tabs_one_file(convert_to_tabs_command, path)

    def _handle_folder(self, args: argparse.Namespace):
        """handle folder"""
        java_command = self._get_java_command(args)
        convert_to_tabs_command = self._get_convert_to_tabs_command(args)
        with concurrent.futures.ProcessPoolExecutor(max_workers=4) as executor:
            for root, _, files in os.walk(args.path):
                for file in files:
                    if not file.endswith(".java"):
                        continue
                    full_path = str(Path(root, file))
                    executor.submit(
                        self._handle_file_helper,
                        java_command,
                        convert_to_tabs_command,
                        full_path,
                    )
                    time.sleep(0.1)

    def _format_one_file(self, java_command: list[str], path: str):
        """format one file"""
        trimmed_path = path.strip()
        command = java_command + [trimmed_path]
        print(f"formatting: {trimmed_path}")
        subprocess.run(command, check=True)

    def _convert_to_tabs_one_file(self, convert_to_tabs_command: list[str], path: str):
        """convert to spaces one file"""
        trimmed_path = path.strip()
        command = convert_to_tabs_command + [trimmed_path]
        if len(command) > 0:
            subprocess.run(command, check=True)

    def _get_java_command(self, args: argparse.Namespace) -> list[str]:
        java_exec, google_jar = self._get_execs(args)
        command = [
            java_exec,
            "-jar",
            google_jar,
            "--skip-javadoc-formatting",
            "--replace",
        ]
        return command

    def _get_execs(self, args: argparse.Namespace) -> tuple[str, str]:
        """Get executables. Returns (java, google_jar)"""
        return args.java_exec, args.google_jar

    def _get_convert_to_tabs_command(self, args: argparse.Namespace) -> list[str]:
        """get convert to tabs command written in Rust, if avaiable.
        File can be found here: https://github.com/toddnguyen47/convert_beginning_whitespaces_rust
        """
        cvt = args.convert_to_tabs
        if cvt:
            return [
                cvt,
                "--ws-from",
                "space",
                "--comment-char",
                '*',
                "--num-spaces",
                "2",
                "--",
            ]

        return []


if __name__ == "__main__":
    main = Main()
    main.run()

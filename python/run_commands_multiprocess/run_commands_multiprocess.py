"""Run commands async"""

import argparse
import os
import subprocess
import concurrent.futures
import re
import json
from datetime import datetime, timezone
from dataclasses import dataclass


@dataclass
class Config:
    """JSON config class"""

    commands: list[str]
    directory: str
    num_processes: int
    extension: str


class Main: # pylint: disable=too-few-public-methods
    """Main class"""

    _re_multiple_spaces = re.compile(" +")

    def main(self):
        """actual main function to run"""
        parser = argparse.ArgumentParser(
            prog="run_commands_async",
            description="Running set of commands on a folder. Every file will be run asynchronously",
        )
        parser.add_argument(
            "--config",
            help="path to JSON config",
            required=True,
        )
        args = parser.parse_args()

        config = args.config
        parsed_config = self._read_json_config(config)
        print(parsed_config)

        with concurrent.futures.ProcessPoolExecutor(
            max_workers=parsed_config.num_processes
        ) as executor:
            for root, _, files in os.walk(parsed_config.directory):
                for file in files:
                    executor.submit(
                        self._run_commands,
                        root,
                        file,
                        parsed_config.extension,
                        parsed_config.commands,
                    )

    def _run_commands(self, root: str, file: str, extension: str, commands: list[str]):
        """Run commands"""
        file_str = str(file)
        if extension is not None and file_str.endswith(extension):
            full_path = os.path.join(root, file_str)

            for command in commands:
                cmd = command.replace("\n", "")
                cmd = self._re_multiple_spaces.sub(" ", cmd)
                cmd = cmd.split(" ")
                cmd.append(full_path)
                output = subprocess.run(cmd, check=True, capture_output=True)
                print(output.stdout.decode(encoding="utf-8"))

    def _read_json_config(self, file_path: str) -> Config:
        """read in JSON config"""
        with open(file_path, "r", encoding="utf-8") as infile:
            data = json.load(infile)
        config = Config(
            commands=data["commands"],
            directory=data["directory"],
            num_processes=data["numProcesses"],
            extension=data["extension"],
        )
        return config


if __name__ == "__main__":
    m_1 = Main()
    print("Starting")
    start = datetime.now(timezone.utc)
    m_1.main()
    end = datetime.now(timezone.utc)
    print("Finished")
    print(f"Time taken: {end - start}")

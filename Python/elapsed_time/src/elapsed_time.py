import yaml

from sys import platform
from datetime import datetime as dt
from datetime import timedelta
import subprocess
import os

_CONFIG_YAML_FILE_PATH = "config.yaml"
_KEY_WINDOWS_SCRIPT = "windows_script"
_KEY_UNIX_SCRIPT = "unix_script"


class ElapsedTime:
    def __init__(self) -> None:
        self._yaml_file = ""
        self._read_yaml_file()

    def execute(self):
        if platform.lower() == "win32":
            self._execute_timed(self._yaml_file[_KEY_WINDOWS_SCRIPT])
        else:
            self._execute_timed(self._yaml_file[_KEY_UNIX_SCRIPT])

    # *****************************************************
    # PRIVATE FUNCTIONS
    # *****************************************************

    def _execute_timed(self, script_to_run: str):
        start = dt.now()
        full_path = os.path.join(os.getcwd(), script_to_run)
        subprocess.run([full_path])
        end = dt.now()
        elapsed = end - start
        self._output_elapsed_time(elapsed)

    def _output_elapsed_time(self, elapsed: timedelta):
        hours = int(elapsed.seconds / 3600)
        minutes = int(elapsed.seconds / 60) % 60
        seconds = int(elapsed.seconds % 60)
        print(
            "Elapsed time: {:02d}:{:02d}:{:02d}.{:04d}".format(
                hours, minutes, seconds, elapsed.microseconds
            )
        )

    def _read_yaml_file(self):
        with open(_CONFIG_YAML_FILE_PATH) as file:
            self._yaml_file = yaml.safe_load(file)


if __name__ == "__main__":
    elapsed_time = ElapsedTime()
    elapsed_time.execute()

"""Output to files"""

from datetime import datetime
import os.path

from src.utils import logger_color
from src.utils.logger_color import LoggerType

_logger = logger_color.init_logger(__name__, LoggerType.BOTH)
_DATETIME_FORMAT = "%Y-%m-%dT%H-%M-%S"


def write(filename: str, data: str):
    """Output to files"""
    with open(filename, "w", encoding="utf-8") as output_file:
        output_file.write(data)
    _logger.info(f"Outputted to '{filename}'")


def write_prepend_datetime(filename: str, data: str):
    """Output to files with the date and time prepended"""
    current_time = datetime.now().strftime(_DATETIME_FORMAT)
    folder, old_filename = os.path.split(filename)
    file_root, extension = os.path.splitext(old_filename)
    new_filename = "_".join((current_time, file_root))
    new_filename = "".join((new_filename, extension))
    new_fullpath = os.path.join(folder, new_filename)
    write(new_fullpath, data)

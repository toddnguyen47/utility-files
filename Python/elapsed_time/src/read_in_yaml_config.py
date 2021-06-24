import yaml

import os

_CONFIG_YAML_FILE_PATH = os.path.join(
    os.path.dirname(os.path.realpath(__file__)), "config.yaml"
)


def read() -> dict:
    with open(_CONFIG_YAML_FILE_PATH) as file:
        yaml_file = yaml.safe_load(file)
    return yaml_file

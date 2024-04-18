"""Iterate JSON resursively"""

import json

_INPUT_FILE = "my_input_file.json"


def _main():
    """main function"""
    with open(_INPUT_FILE, "r", encoding="utf-8") as infile:
        data = json.load(infile)

    l1 = []
    map1 = {}
    iterate_json(data, l1, map1)


def iterate_json(data: any, keys: list, output_map: dict):
    """iterate JSON recursively"""
    if isinstance(data, dict):
        for key, val in data.items():
            keys.append(key)
            iterate_json(val, keys, output_map)
            # pop off last key now
            del keys[-1]
    elif isinstance(data, list):
        for idx, val in enumerate(data):
            keys.append(str(idx))
            iterate_json(val, keys, output_map)
            # pop off last key now
            del keys[-1]
    else:
        cur_key = ".".join(keys)
        output_map[cur_key] = data


if __name__ == "__main__":
    _main()

from typing import Any
from dataclasses import dataclass
from enum import Enum
import json
import argparse

# sample usage
# python3 convert_lombok_object_to_json.py --logfile full/path/to/logfile.txt


class JsonType(Enum):
    OBJECT = 1
    LIST = 2


@dataclass
class Node:
    json_type: JsonType
    object_values: dict
    array_values: list


def build_tree(input_str: str, json_type: JsonType, idx: int) -> tuple[Node, int]:  # noqa: PLR0912
    node = Node(json_type=json_type, object_values={}, array_values=[])
    current_chars = []
    current_key = ""
    while idx < len(input_str):
        c = input_str[idx]
        if idx > 0 and input_str[idx - 1] == "\\":
            # this character is escaped
            current_chars.append(c)
        elif c == "\\":
            # skip the first `\`
            pass
        elif c == "=":
            # end of key
            current_key = ("".join(current_chars)).strip()
            current_chars = []
        elif c == ",":
            # end of value
            if current_chars != [] and current_key != "":
                value = ("".join(current_chars)).strip()
                parsed_value = parse_value(value)
                node.object_values[current_key] = parsed_value
                current_key = ""
            current_chars = []
        elif c == "(":
            # start of object
            new_node, idx = build_tree(input_str, JsonType.OBJECT, idx + 1)
            if json_type == JsonType.OBJECT:
                node.object_values[current_key] = new_node
                current_key = ""
            elif json_type == JsonType.LIST:
                node.array_values.append(new_node)
            current_chars = []
        elif c in {")", "]"}:
            # end of object or list
            return node, idx
        elif c == "[":
            # start of list
            new_node, idx = build_tree(input_str, JsonType.LIST, idx + 1)
            if json_type == JsonType.OBJECT:
                node.object_values[current_key] = new_node
                current_key = ""
            elif json_type == JsonType.LIST:
                node.array_values.append(new_node)
            current_chars = []
        else:
            current_chars.append(c)

        idx += 1
    return node, idx


def parse_value(value: str) -> Any:
    value_lower = value.lower()
    if is_int(value_lower):
        return int(value_lower)
    elif is_float(value_lower):
        return float(value_lower)
    elif value_lower == "true":
        return True
    elif value_lower == "false":
        return False
    elif value_lower == "null":
        return None
    # nothing else: value is just a string
    return value


def is_int(s: str):
    try:
        int(s)
        return True
    except ValueError:
        return False


def is_float(s: str):
    try:
        float(s)
        return True
    except ValueError:
        return False


def convert_to_json(root_node: Node) -> Any:
    result = {}
    if root_node.json_type == JsonType.OBJECT:
        result = {}
        for key, value in root_node.object_values.items():
            inner_value = value
            if isinstance(inner_value, Node):
                inner_value = convert_to_json(inner_value)
            result[key] = inner_value
    elif root_node.json_type == JsonType.LIST:
        result = []
        for item in root_node.array_values:
            inner_item = item
            if isinstance(inner_item, Node):
                inner_item = convert_to_json(inner_item)
            result.append(inner_item)
    return result


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--logfile", help="Full path to log file")
    args = parser.parse_args()

    with open(args.logfile, "r") as file:
        data = file.readlines()
        first_line = data[0]
        my_tree, _ = build_tree(first_line, JsonType.OBJECT, 0)
        result = convert_to_json(my_tree)
        result_json_str = json.dumps(result)
        print(result_json_str)

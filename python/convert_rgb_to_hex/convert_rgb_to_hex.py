from typing import List
import sys
import argparse


def convert(input_str: str) -> str:
    color_list = input_str.split(",")
    color_hex = '#{:02x}{:02x}{:02x}'.format(
        int(color_list[0]), int(color_list[1]), int(color_list[2]))
    return color_hex


def convert_list(input_list: List, delimiter_char: str) -> str:
    output_list = []
    for color in input_list:
        output_list.append(convert(color))
    return delimiter_char.join(output_list)


def get_input_argument_parser() -> argparse.ArgumentParser:
    # Ref: https://stackoverflow.com/a/3853776#comment36687355_3853776
    parser = argparse.ArgumentParser(
        description="Convert a list of space-separated RGB colors to Hex colors. Note that the "
        + "quotation marks are required to separate the RGB colors! You can also optionally supply "
        + "a delimiter string, which will default to a SPACE if not supplied."
        + "\nExample usage:\n"
        + "$ python3 convert_rgb_to_hex.py \"255, 255, 255\" \"252, 186, 3\" \"57, 179, 98\"\n"
        + "\n"
        + "Sample output:\n"
        + "#ffffff #fcba03 #39b362",
        formatter_class=argparse.RawDescriptionHelpFormatter
    )

    parser.add_argument(
        "RGB_colors", help="RGB colors wrapped in quotation quotes", type=str, nargs='+')

    parser.add_argument("--delimiter", default=" ",
                        help="An optional delimiter for the output string. Defaults to SPACE.")

    return parser


def exit_if_empty_command_line_args(parser: argparse.ArgumentParser):
    # If the user does not supply any command line args
    if len(sys.argv) < 2:
        print("ERROR: Please supply some RGB.")
        parser.print_help()
        exit(-1)


if __name__ == "__main__":
    parser = get_input_argument_parser()
    exit_if_empty_command_line_args(parser)
    args = parser.parse_args()

    output = convert_list(args.RGB_colors, args.delimiter)
    print(output)

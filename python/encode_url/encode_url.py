"""Encode URL: Encode a URL."""

import urllib.parse
import argparse


def _main():
    """Main function"""

    parser = argparse.ArgumentParser(description="URL encode a string")
    parser.add_argument(
        "inputs",
        type=str,
        nargs="+",
        help="string input to encode. space-separated for multiple encoding",
    )
    args = parser.parse_args()

    inputs: list[str] = args.inputs
    for temp_input in inputs:
        temp_input = temp_input.strip()
        encoded = urllib.parse.quote(temp_input)
        print(encoded)


if __name__ == "__main__":
    _main()

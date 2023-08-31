"""Print JSON escaped string"""

import argparse


def _main():
    """Main function"""
    parser = argparse.ArgumentParser(description="Print escaped JSON")
    parser.add_argument("json_str", type=str, help="JSON string")
    args = parser.parse_args()

    # ref: https://stackoverflow.com/questions/1885181/how-to-un-escape-a-backslash-escaped-string#comment47133717_1885197
    json_str1: str = args.json_str
    encoded = json_str1.encode("utf-8")
    decoded = encoded.decode("unicode_escape")

    print("")
    print("Decoded JSON string is:")
    print(decoded)


if __name__ == "__main__":
    _main()

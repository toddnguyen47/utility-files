import argparse
import sys
from file_operations import FileOperations

parser = argparse.ArgumentParser(
    description="Trim whitespace from all lines in the input file"
)
parser.add_argument("file_path", help="Full path to input file")

if __name__ == "__main__":
    args = parser.parse_args()
    if len(sys.argv) < 1:
        parser.print_help()
        exit(-1)

    file_operations = FileOperations(args.file_path)
    file_operations.execute()

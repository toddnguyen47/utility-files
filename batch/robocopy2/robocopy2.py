from subprocess import Popen
import argparse
import time
from datetime import timedelta
import pathlib
import os.path

# Ref: https://stackoverflow.com/a/3430395
current_script_path = pathlib.Path(__file__).parent.absolute()
batch_file = os.path.join(current_script_path, "robocopy2.bat")

def get_arg_parser() -> argparse.Namespace:
    parser = argparse.ArgumentParser(description='Run robocopy2.bat')
    parser.add_argument('src', metavar='N', type=str,
                       help='source folder')
    parser.add_argument('dest', metavar='N', type=str,
                       help='destination folder')
    return parser.parse_args()

def robocopy(src: str, dest: str):
    p = Popen([batch_file, src, dest])
    stdout, stderr = p.communicate()

def execute():
    args = get_arg_parser()
    start_time = time.time()
    robocopy(args.src, args.dest)
    end_time = time.time()
    # Ref: https://stackoverflow.com/a/27793118
    time_elapsed = str(timedelta(seconds=(end_time - start_time)))
    
    print("*****")
    print("Time elapsed: {}".format(time_elapsed))

if __name__ == "__main__":
    execute()

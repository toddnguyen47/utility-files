#!/bin/bash

# Ref: https://stackoverflow.com/a/246128
# Look in the comments made by the user "Adrian Günter" at "Oct 28 '15 at 23:38"
cur_script_dir="$(dirname "$(readlink -f "$0")")"

echo "Current Script Dir: '${cur_script_dir}'"


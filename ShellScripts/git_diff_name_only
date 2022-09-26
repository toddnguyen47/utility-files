#!/bin/bash

# Ref: https://gist.github.com/marcelblijleven/f63f17c34cbc2ade8932b725e6ae854b
_result="$(git --no-pager diff --name-only | tr '\n' ' ')"

# Ref: https://stackoverflow.com/questions/27658675/how-to-remove-last-n-characters-from-a-string-in-bash#comment43737287_27658733
_result2="${_result::${#_result}-1}"
echo "${_result2}"

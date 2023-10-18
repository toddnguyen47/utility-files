#!/bin/bash

# Ref: https://stackoverflow.com/a/20622193/6323360

_INPUT_FILE="raw-id-dump"
_EXTENSION=".txt"
_NUM_LINES="5000"

split -da 4 -l "${_NUM_LINES}" "${_INPUT_FILE}${_EXTENSION}" "${_INPUT_FILE}"-part --additional-suffix=".log"


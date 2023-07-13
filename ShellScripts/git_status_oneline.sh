#!/bin/bash

set -e -o pipefail

extension=""
if [ -n $1 ]; then
    # $1 is not empty, do what you want
    extension=$1
fi

git status --short | grep "${extension}" | awk '{ print $2 }' | tr '\n' ' '
echo ""

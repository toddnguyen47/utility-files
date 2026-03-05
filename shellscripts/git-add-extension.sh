#!/bin/bash

set -eux -o pipefail

# awk extracts the last field (NF = number of fields)
git status --short | grep $1 | awk '{print $NF}' | xargs git add
git status

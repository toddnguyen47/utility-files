#!/bin/bash

set -eux -o pipefail

git ls-remote --heads origin "$1" | wc -l

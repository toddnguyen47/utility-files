#!/bin/bash

set -euxo pipefail

_DIFF_PATH="google-java-format-diff.py"
_GOOGLE_JAVA_FORMAT_PATH="google-java-format-1.13.0-all-deps.jar"

export JAVA_HOME="temurin-11.jdk/Contents/Home"
git diff --staged --unified=0 HEAD^ | \
    python3 "${_DIFF_PATH}" -p1 -i \
        --aosp \
        --google-java-format-jar "${_GOOGLE_JAVA_FORMAT_PATH}" \
        -regex "[\S ]*?(?<!ignoreFile1)(?<!ignoreFile2)\.java"
export JAVA_HOME="temurin-8.jdk/Contents/Home"

#!/bin/bash

set -eux -o pipefail

echo $1

(
  # Format JAVA
  format_java_command='/Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home/bin/java -jar
      "$HOME/opt/google-java-format/google-java-format-1.16.0-all-deps.jar"
      --skip-javadoc-formatting --replace'

  # Convert spaces to tabs
  convert_beginning_spaces='/Users/tn6091/programming/personal/convert_beginning_whitespaces_rust/mybin/convert_beginning_whitespaces_rust
    --ws-from space --comment-char "*" --num-spaces 4"'

  # run all commands now

  python3 run_commands_multiprocess.py \
    --command "${format_java_command}" \
    --command "${convert_beginning_spaces}" \
    --dirpath "$1" \
    --num-processes 8 \
    --ext ".java"
)

exit 0

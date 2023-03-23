#!/bin/bash

echo $1

(
  _java_exec="/Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home/bin/java"
  _google_java_format="$HOME/opt/google-java-format/google-java-format-1.15.0-all-deps.jar"
  _pre_commit_hooks="$HOME/programming/personal/pre-commit-hooks"

  # Format JAVA
  format_java_command='"${_java_exec}" -jar \
      "${_google_java_format}" \
      --skip-javadoc-formatting --replace'

  # Convert spaces to tabs
  export PYTHONPATH="${_pre_commit_hooks}"

  convert_beginning_spaces="python3 \
      "${_pre_commit_hooks}/pre_commit_hooks/convert_beginning_spaces.py" \
      --comment-char \* \
      --tab-size 2"

  python3 run_commands_async.py \
    --command "${format_java_command}" \
    --command "${convert_beginning_spaces}" \
    --dirpath "$1" \
    --num-processes 8 \
    --ext ".java"
)

exit 0

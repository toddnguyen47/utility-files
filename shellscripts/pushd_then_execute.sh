#!/bin/bash

# Ref: https://stackoverflow.com/a/25288289
# Quiet pushd / popd outputs!
pushd() { 
  command pushd "$@" > /dev/null 
}

popd() {
  command popd "$@" > /dev/null
}

_execute() {
  somedir="$HOME"
  pushd "${somedir}"

  _do_some_work

  # Make sure to not pop the stack if there is only one item in the stack
  num_stack="$(dirs -v | wc -l)"
  if [[ num_stack -gt "1" ]]; then
    popd
  fi
}

_do_some_work() {
  printf "%s\n" "Do some work!"
  pwd
}

_execute


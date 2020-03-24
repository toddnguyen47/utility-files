#!/bin/sh

tail_until_pattern_found() {
  # ${1} is the first argument to the function, ${2} is the second, and so on
  local log_filename="${1}"
  local pattern_to_match="${2}"
  local random_filename="$(tr -cd A-Za-z0-9 < /dev/urandom | head -c 30)"

  # Ref: https://stackoverflow.com/a/3786955
  # IFS preserves white space
  # `&` run asynchronously (using forks). Ref: https://bashitout.com/2013/05/18/Ampersands-on-the-command-line.html
  (tail -f "${log_filename}" & echo ${!} > ${random_filename}) | while IFS= read line; do
    command='printf "%s\n" "${line}"'
    eval ${command}
    if eval ${command} | grep -E "${pattern_to_match}" -q; then
      kill $(cat ${random_filename})
    fi
  done

  rm ${random_filename}
}

# Sample usage
#filename=""
#pattern_to_match=""
#tail_until_pattern_found ${filename} ${pattern_to_match}
printf "Finished!\n"


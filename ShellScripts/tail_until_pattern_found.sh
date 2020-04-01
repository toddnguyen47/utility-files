#!/bin/sh

tail_until_pattern_found() {
  # ${1} is the first argument to the function, ${2} is the second, and so on
  local log_filename=$1
  local pattern_to_match=$2
  local pattern_error=$3

  local random_filename="$(tr -cd A-Za-z0-9 < /dev/urandom | head -c 30)"

  # Ref: https://stackoverflow.com/a/3786955
  # IFS preserves white space
  # `&` run asynchronously (using forks).
  # Ref: https://bashitout.com/2013/05/18/Ampersands-on-the-command-line.html
  (tail -f "${log_filename}" & echo ${!} > ${random_filename}) | while IFS= read line; do
    local exit_status=0
    command='printf "%s\n" "${line}"'
    eval ${command}
    if eval ${command} | grep -E "${pattern_to_match}" -q; then
      if [ "${pattern_error_to_match}" != "" ] && eval ${command} | grep -E "${pattern_error_to_match}" -q; then
        exit_status=1
      fi
      kill $(cat ${random_filename})
      return "${exit_status}"
    fi
  done

  # while loop is run in a subshell, so it will have its own exit status
  local exit_status=$?
  rm ${random_filename}
  return "${exit_status}"
}

# Sample usage
filename="file.txt"
pattern_to_match="^Error ->|^Failure ->|^All tests finished in [0-9:]+"
# Leave pattern_error_to_match as empty string ("") if you don't have an error string to match
pattern_error_to_match=""

# Execute!
tail_until_pattern_found "${filename}" "${pattern_to_match}" "${pattern_error_to_match}"
# exit_status is `tail_until_pattern_found` 's return code
exit_status=$?

# Post-execute
printf "Finished!\n"
exit "${exit_status}"

#!/bin/bash

# Echo commands being ran
set -x

# How to use
# Edit the _do_work() function below. Whatever code in the _do_work() function
# will be ran and the time elapsed will be calculated.

_do_work() {
    sleep 5s
}

_run() {
    # Ref: https://unix.stackexchange.com/a/314370
    start_time="$(date -u +%s.%N)"

    _do_work

    # Get elapsed time
    end_time="$(date -u +%s.%N)"
    elapsed="$(bc <<< "$end_time-$start_time")"
    echo "Total of $elapsed seconds elapsed for process"
}

_run

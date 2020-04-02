#!/bin/bash

executable_file=$1

valgrind_outfile="valgrind-out.txt"

# Ref: https://stackoverflow.com/a/44989219
valgrind --leak-check=full \
         --show-leak-kinds=all \
         --track-origins=yes \
         --log-file=${valgrind_outfile} \
         "${executable_file}"
printf "%s\n" "Valgrind's output file is at ${valgrind_outfile}"

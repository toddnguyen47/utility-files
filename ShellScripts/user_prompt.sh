#!/bin/bash

_do_work() {
    echo "Doing some work!"
}

# Ref: https://stackoverflow.com/a/226724
echo "INSERT YOUR PROMPT HERE"
echo "Enter in the number for your selection."
select yn in "Yes" "No"; do
    case $yn in
        Yes ) _do_work; break;;
        No ) exit;;
    esac
done

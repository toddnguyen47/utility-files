#!/bin/bash

python3 -m pipenv run coverage run --source=src/ -m pytest tests/
python3 -m pipenv run coverage html


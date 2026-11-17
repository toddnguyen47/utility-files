#!/bin/bash

# -delete must be last!
find ".venv" ! -name "README.md" ! -name ".gitignore" \
    -delete

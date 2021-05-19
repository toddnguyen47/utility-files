#!/bin/bash

# Ref: https://github.com/asciidoctor/asciidoctor/issues/1907#issuecomment-386540576
# Sample usage

# ```
# ./convert_adoc_to_markdown.sh input_file.adoc output_file.md
# ```

# `--atx-headers` is deprecated, we will use `--markdown-headings=atx` instead
asciidoctor -b docbook -a leveloffset=+1 -o - "$1" | \
pandoc  --markdown-headings=atx --wrap=preserve -t markdown_strict -f docbook - > "$2"


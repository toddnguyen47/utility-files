# Backup

Old code with multiple flags

```python
parser = argparse.ArgumentParser(
    prog="run_commands_async",
    description="Running set of commands on a folder. Every file will be run asynchronously",
)
parser.add_argument(
    "--command",
    help="commands to run. supply multiple `--command` flags for each command",
    action="append",
    required=True,
)
parser.add_argument(
    "--dirpath", help="directory path to run the commands on", required=True
)
parser.add_argument("--ext", help="optional extension to pass")
parser.add_argument(
    "--num-processes",
    help="number of processes we want to use. defaults to 4",
    default=4,
    type=int,
)
```

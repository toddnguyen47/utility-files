import os
import subprocess

_SEPARATOR = ";"
_POWERSHELL_FILE =  os.path.join(os.getcwd(), "envpaths.ps1")

extra_paths = [
    r"C:\Users\i1A771792\opt\bin",
    r"C:\Program Files\7-Zip"
]

env_path = os.environ["PATH"]
env_path_list = env_path.split(_SEPARATOR)

# Prevent duplicates
env_path_set = set()

for path in extra_paths:
    env_path_set.add(path)

for ep in env_path_list:
    env_path_set.add(ep)

env_path = _SEPARATOR.join(env_path_set)
env_path = "\"{}\"".format(env_path)
command = ["powershell", _POWERSHELL_FILE, env_path]
subprocess.run(command)

# Ref: https://stackoverflow.com/a/16426764
param([string]$env_path)

# Ref: https://stackoverflow.com/a/2571200
[Environment]::SetEnvironmentVariable("PATH", $env_path, [System.EnvironmentVariableTarget]::User)

[Environment]::GetEnvironmentVariable("PATH", [System.EnvironmentVariableTarget]::User)

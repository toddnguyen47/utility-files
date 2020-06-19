@echo off
@REM https://stackoverflow.com/a/56931157
@echo Closing all programs...
@REM taskkill /f /t /im explorer.exe
@REM start explorer.exe

for /f "skip=3 tokens=1" %%G in ('tasklist /fi "Username eq %USERNAME%" ') do (
  if not "%%G" == "svchost.exe" (
  if not "%%G" == "cmd.exe" (
  if not "%%G" == "tasklist.exe" (
    echo %%G
    taskkill /f /im %%G
  )
  )
  )
)

explorer.exe

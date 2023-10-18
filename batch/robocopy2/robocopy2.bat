set src_folder=%~nx1
set dest=%2\%src_folder%
if not exist %dest% mkdir %dest%
robocopy %1 %dest% /e /v

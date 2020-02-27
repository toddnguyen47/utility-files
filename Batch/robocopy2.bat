set dest=%2\%1
if not exist %dest% mkdir %dest%
robocopy %1 %dest% /e /v

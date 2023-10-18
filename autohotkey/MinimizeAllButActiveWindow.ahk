#SingleInstance Force

; Windows + M override
; Minimize all but active window
#m::
WinGet, current_win_id ,, A ; Window A = active
WinMinimizeAll
WinActivate ahk_id %current_win_id%
return

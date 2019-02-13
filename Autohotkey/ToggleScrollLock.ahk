; Right Ctrl + / to toggle ScrollLock
RAlt & /::
SendInput {ScrollLock}
IsScrollLockOn := ""
if (1 = GetKeyState("ScrollLock", "T"))
    IsScrollLockOn := "ON"
else
    IsScrollLockOn := "OFF"

TrayTip, "Scroll Lock", "Scroll Lock is %IsScrollLockOn%"
Sleep 1000 ; Display it for a second
HideTrayTip()
return

; Reference: https://www.autohotkey.com/docs/commands/TrayTip.htm
; Copy this function into your script to use it.
HideTrayTip() {
    TrayTip  ; Attempt to hide it the normal way.
    if SubStr(A_OSVersion,1,3) = "10." {
        Menu Tray, NoIcon
        Sleep 200  ; It may be necessary to adjust this sleep.
        Menu Tray, Icon
    }
}

@REM Ref: https://www.howtogeek.com/208420/how-to-check-your-motherboard-model-number-on-your-windows-pc/
echo Remember to run this as admin!
wmic baseboard get product,Manufacturer,version,serialnumber
pause

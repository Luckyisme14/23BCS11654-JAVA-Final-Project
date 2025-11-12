@echo off
echo Compiling Car Rental System...
cd model\src\main
javac -d ..\..\..\out\production\CarRentalModule *.java controller\*.java model\*.java view\*.java
if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    pause
    exit /b 1
)
echo Compilation successful!
echo.
echo Starting Car Rental System GUI...
echo.
java -cp ..\..\..\out\production\CarRentalModule main.Main
pause


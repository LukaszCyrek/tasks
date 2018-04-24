call gradlew build
if "%ERRORLEVEL%" == "0" goto rename
echo.
echo GRADLEW BUILD has errors - breaking work
goto fail

:rename
call C:\tasks\runcrud.bat
start "firefox.exe" http://localhost:8080/v1/task/getTasks

:fail
echo.
echo There were errors
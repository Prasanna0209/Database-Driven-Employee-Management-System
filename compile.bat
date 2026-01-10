@echo off
REM Compile the project
echo Compiling source code...
javac -d bin src/com/employee/model/*.java src/com/employee/util/*.java src/com/employee/dao/*.java src/com/employee/service/*.java src/com/employee/main/*.java

REM Check if compilation was successful
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b %errorlevel%
)

echo Compilation successful!
echo.
echo To run the application, ensure you have the MySQL Connector/J jar file in the 'lib' directory (or modify the classpath).
echo Example usage:
echo java -cp "bin;lib/mysql-connector-j-8.2.0.jar" com.employee.main.Main
pause

@echo off
if not exist out mkdir out
javac -d out src\minibank\*.java
if errorlevel 1 exit /b 1
jar cfe minibank.jar minibank.MiniBank -C out .
echo Build completed. Run: java -jar minibank.jar

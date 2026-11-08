#!/bin/sh
mkdir -p out
javac -d out src/minibank/*.java || exit 1
jar cfe minibank.jar minibank.MiniBank -C out .
echo 'Build completed. Run: java -jar minibank.jar'

#!/bin/bash          
echo Generating...
file="raw.txt"

# 1. Remove whitespaces and capitalize every first letter (\u - to uppercase next character)
# 2. Remove inline comment modifier '\\' and insert 'Problem' to the head and append '.java' file ext.
newfile=$(sed -n -e '1 {s/[[:space:]]*\b\(.\)/\u\1/g
                        s@/*[[:space:]]*\(.*\)@Problem\1\.java@p}' $file)
echo $newfile
cp $file $newfile
git add $newfile
git commit -m \"Add $newfile\"
#!/bin/bash

echo "Git PUSH Started!"
git status
echo "These are the changes in Repository"

read -p "Enter the Commit Message: " userinput
echo "Entered Commit message is $userinput"
read -n 1 -s -r -p "Press any key to continue..."

git config core.autocrlf false
git add .
git commit -m"$userinput"
git push -f origin main

read -n 1 -s -r -p "Press any key to exit..."

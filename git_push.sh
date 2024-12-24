#!/bin/bash

echo "Git PUSH Started!"
git status
echo "These are the changes in Repository"

# Ask the user to review the changes
read -n 1 -s -r -p "Press any key to continue after reviewing the changes..."

# Ask for the commit message
read -p "Enter the Commit Message: " userinput
echo "Entered Commit message is: $userinput"

# Ask the user to confirm before committing
read -n 1 -s -r -p "Press any key to continue with the commit..."

# Make sure to stage only the desired files (optional: replace `git add .` with specific files)
git add .  # Or use `git add <specific-files>` if you want to add only certain files
git commit -m"$userinput"

# Optional: Change `main` to your feature branch (e.g., feature/your-feature)
read -p "Enter the branch to push to (default: main): " branch
branch=${branch:-main}  # If no input, default to 'main'

# Push to the specified branch
git push origin $branch

# Confirm and exit
read -n 1 -s -r -p "Press any key to exit..."

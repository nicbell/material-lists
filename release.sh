#!/bin/sh

echo "Releasing: $1"

projectUrl="https://github.com/nicbell/material-lists"
releaseUrl="$projectUrl/releases/new?tag=$1&title=$1"

# Create tag, push, open release url
git switch main
git pull origin
git tag -a "$1" -m "Release version $1"
git push origin $1
git push --set-upstream origin main

# Open URL for MR.
open "$releaseUrl"
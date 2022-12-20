#!/bin/sh

echo "Releasing: $1"

projectUrl="https://github.com/nicbell/material-lists"
mr="$projectUrl/compare/main...release/$1"

# Create release branch, tag, push
git switch develop
git pull origin
git checkout -b release/$1
git tag -a "$1" -m "Release version $1"
git push origin $1
git push --set-upstream origin release/$1

# Open URL for MR.
open "$mr"

# Generate change log
# TODO
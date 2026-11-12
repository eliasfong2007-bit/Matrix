# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Calendar Versioning](https://calver.org/) of
the following form: YYYY.0M.0D.

## 2026.11.12

### Added

- Designed kernel implementation for Matrix component

### Updated

- Changed design to include kernel methods.

## 2026.11.11

### Added

- Designed abstract class for Matrix component

### Updated

- Changed design to include required secondary methods, secondary methods from
matrix interface.
- Removed ref, rref, and det methods to align with project scope.

## 2026.11.09

### Added

- Designed kernel and enhanced interfaces for Matrix component

### Updated

- Changed design to have types that return rows or columns return a matrix that
can also be manipulated.
- added new secondary methods, including multiply.

## 2026.11.08

Implemented MVP and main method for it including all kernel methods, and
additional method prettyprint.

## 2026.10.27

### Added

Brainstormed component ideas for project.

Here `YYYY.MM.DD` would be the date of your submission, such as 2024.04.21.

You may notice that things are nicely linked in the root CHANGELOG. If you'd
like to accomplish that, you will need to make GitHub releases after each pull
request merge (or at least tag your commits). This is not required.

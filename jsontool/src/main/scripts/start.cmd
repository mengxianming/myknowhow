@echo off
SET params=
if not "%1%" equ "" (
   SET params=%params% %1%
)
if not "%2%" equ "" (
   SET params=%params% %2%
)
if not "%3%" equ "" (
   SET params=%params% %3%
)
if not "%4%" equ "" (
   SET params=%params% %4%
)

java -jar lib/jsontool-0.0.1-SNAPSHOT.jar %params%


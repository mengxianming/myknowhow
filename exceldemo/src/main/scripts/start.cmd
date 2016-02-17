@echo off
SET OUTF=
if not "%1%" equ "" (
   SET "OUTF= > %1%"
   echo output to %1%
)

java -jar lib/exceldemo-0.0.1-SNAPSHOT.jar -f config.json %OUTF%
pause

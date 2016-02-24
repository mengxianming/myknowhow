@echo off
SET OUTF=
if not "%1%" equ "" (
   SET "OUTF= > %1%"
   echo output to %1%
)

java -cp lib/*.jar; my.study.exceldemo.write.WriteMain -f config.json %OUTF%
pause

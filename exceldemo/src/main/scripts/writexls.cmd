@echo off
SET OUTF=
if not "%1%" equ "" (
   SET "OUTF= > %1%"
   echo output to %1%
)

java -Djava.ext.dirs=lib my.study.exceldemo.write.WriteMain -f config.json %OUTF%
pause

@echo off
set JVMFLAGS=-Dfile.encoding=UTF-8
set BASE_HOME=%~dp0%..\

set CLASSPATH=.\lib\*;%CLASSPATH%


set CLASS_MAIN=com.mogoroom.tasktracker.Main

java -cp "%CLASSPATH%" %JVMFLAGS% %CLASS_MAIN% %*

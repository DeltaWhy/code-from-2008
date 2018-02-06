@echo off
cls
set PATH=%PATH%;C:\Program Files\Java\jdk1.5.0_09\bin
set cwd="\\sths-student-fs\home$\361229\Computer Science\Ch10"
del %cwd%\*.class
javac %cwd%\Count.java
set /p p= Press enter to quit...


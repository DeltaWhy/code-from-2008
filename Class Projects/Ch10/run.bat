@echo off
cls
set PATH=%PATH%;C:\Program Files\Java\jdk1.5.0_09\bin
echo set cwd="\\sths-student-fs\home$\361229\Computer Science\Ch10"
set cwd="C:\Documents and Settings\361229\Desktop"
@echo on
java %cwd%\Count
set /p p= Press enter to quit...
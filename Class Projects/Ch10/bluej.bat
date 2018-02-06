@echo off
cls
set cwd="\\sths-student-fs\home$\361229\Computer Science\Ch10"
C:\BlueJ\bluej -h %cwd%
taskkill /f /fi "Windowtitle eq BlueJ*"
set /p p= Press enter to quit...
cd src

javac *.java

FOR /L %%i IN (1,1,9) DO java Main ../test/test0%%i.csv <../test/test0%%i.in >../test/test0%%i.out && java Validator ../test/test0%%i.e ../test/test0%%i.out

FOR /L %%i IN (10,1,16) DO java Main ../test/test%%i.csv <../test/test%%i.in >../test/test%%i.out && java Validator ../test/test%%i.e ../test/test%%i.out

set /p temp="Hit enter to quit"

@echo OFF
set LINES=0
for /f "delims==" %%I in (wall.java) do (
    set /a LINES=LINES+1
)

set /a LINES=LINES-65
more +%LINES% < wall.java

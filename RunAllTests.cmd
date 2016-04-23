cd src

FOR /L %%i IN (1,1,9) DO java Main ../test/test0%%i.csv <../test/test0%%i.in >../test/test0%%i.out && java Validator ../test/test0%%i.e ../test/test0%%i.out

FOR /L %%i IN (10,1,15) DO java Main ../test/test%%i.csv <../test/test%%i.in >../test/test%%i.out && java Validator ../test/test%%i.e ../test/test%%i.out

set /p temp="Hit enter to quit"

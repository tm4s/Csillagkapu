#Tesztelés

##Futtatás

###Teszt futtatása adott bemeneti és kimeneti fájlal:
**java -jar Csillagkapu.jar _[mapName.csv]_ <_[testInoutFile]_ >_[testOutoutFile]_**<br>
pl.: **java -jar Csillagkapu.jar test00.csv \<tes00.in \>test00.out**

###Teszt kiértékelése:
**java -jar Validator.jar _[expectedOutputFile]_ _[realOutputFile]_**<br>
pl.: **java -jar Csillagkapu.jar test00.e test00.out**

##Teszt készítése
Szükséges fileok:
+ testXX.csv _A teszthez tartozó térkép._
+ testXX.in _A teszt során futtatandó parancsok._
+ testXX.e _Az elvárt kimenetetek, címekkel kiegészítve._

<br>
**Formai követelmény: test00 minta szerinti formázás!**

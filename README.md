# WhenAppAndroid2
How To Run
for this project you need to clone 3 repos:

[android app] (https://github.com/NoamCohen48/WhenAppAndroid2)
[Server (ASP WebAPI)] (https://github.com/Roi-Avraham/whenAppServer)
for running the android client:
run the server first and then run the app.

for running the api server:

make sure to install mariadb with username root and password toor.
delete db called WhenUpDB if exist.
you can skip those 2 steps by changing the db settings in WhenAppContext Screenshot_1

delete migration folder.
in package manager run Add-Migration init and Update-Database.
run the server

Dependencies
this project uses:

android
ASP.net
Firebase

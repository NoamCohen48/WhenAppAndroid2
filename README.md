# WhenApp Android
we have created an app "inspired" by WhatsApp for multi platform communication.

### Preview
<img src="https://user-images.githubusercontent.com/92931230/164974162-bfb6b82e-ffaa-4ef6-874c-aa6e72c4ee8a.png" width="1000">
<img src="https://user-images.githubusercontent.com/92931230/164974175-2a0fd857-3ee9-4827-869b-dd7363a85131.png" width="1000">

### Development
The app was created using Android studio, and server side using ASP.NET.
Also used axios, signalR, room and firebase libraries. 

### Features
1. Supports registration and login.
2. Great design.
3. Responsive server.
4. Landscape support.
5. The data is saved both local and on the side of a server.

### Creators
this site was created by noam cohen, shaked cohen and roi avraham.

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

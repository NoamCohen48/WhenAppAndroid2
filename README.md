# WhenApp Android
We have created an app "inspired" by WhatsApp for multi platform communication.

### Preview
<img src="https://user-images.githubusercontent.com/47411973/174489826-a74a391e-1f76-49de-a27a-4a14277b2d0b.png" width="300">
<img src="https://user-images.githubusercontent.com/47411973/174489926-fee402ab-90dd-40ec-9e0e-8ae92552d034.png" width="300">
<img src="https://user-images.githubusercontent.com/47411973/174489943-e99523a0-bd9e-406b-af0f-893bda131ccc.png" width="300">
<img src="https://user-images.githubusercontent.com/47411973/174489955-69e3a80f-14e1-4449-9f19-f2124378fd45.png" width="600">



### Development
The app was created using Android studio, and server side using ASP.NET.
Also used axios, signalR, room and firebase libraries. 

### Features
1. Supports registration and login.
2. Great design.
3. Responsive server.
4. Landscape support.
5. Light and Dark modes.
6. The data is saved both local and on the side of a server.

### Creators
this site was created by noam cohen, shaked cohen and roi avraham.

### How To Run

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

### Dependencies
this project uses:

- android
- ASP.net
- Firebase

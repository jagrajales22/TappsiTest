# TappsiTest

Login Automatic test with Appium for Tappsi Android passenger application in Java.

## Libraries needed

Remember to download Appium Client Libraries from http://appium.io/ in order to connect to Appium server. It is needed to 
download Selenium Stand Alone Serverat http://www.seleniumhq.org/

## Prerequisites

In order to run an automatic test in Appium, it is needed to install a lot of thing. Let's start.

### Appium Server

According to your operative system, Appium Server installation will vary. For Windows and Mac it is possible to download a
server with GUI from http://appium.io/ ,but there is no GUI on Linux. For Linux, Appium should be installed via npm
```
npm install appium
```

### Android Studio

Since this project was focused on Android, it is necessary to download Android SDK or Android Studio, that will give us the
possibility to use Android Virtual Devices for running our tests. After installation, remember to set ANDROID_HOME properly.

### Appium Doctor

Since Appium needs a lot of configuration, it is needed to double check all is configured accordingly using Appium Doctor,
that is available on the Appium Server GUI or could be installed via npm
```
npm install appium-doctor
```
Be sure all is checked.It is possible that this step could lead you to install a lot of things or correct JAVA_HOME or ANDROID_HOME.

### Getting your APK and install it to an emulator

If you don't have a Tappsi passenger APK, you could downloaded from https://apkpure.com/ and install it to your emulator
using ADB
```
adb install '/path_to_your_apk.apk'
```
## Having fun

Automatic tests could take some time to program but after never forget to have fun playing with it!

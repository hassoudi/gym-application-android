# gym-application-android
This app demonstrates how to build an app that utilizes Wearable sensors using the [IBM Wearables SDK](http://wearables.mybluemix.net/) combined with [IBM Bluemix](https://console.ng.bluemix.net/) services. It helps Gym trainees by detecting various exercises they perform and counting the number of repetitions. 
An Android Wear device is used to sense movements and an Android Mobile Phone is used to interpret the data, recognize the movements and display repetiotions count to the trainee. 
A web-based Dashboard is available for the Coach, showing trainees' performance and allowing to send feedback to the trainee using push notifications.

### Utilization of Bluemix
1. [Node.js](https://console.ng.bluemix.net/catalog/starters/sdk-for-nodejs/) - running the dashboard and the cloud backend for the of the.
2. [Mobile Client Access](https://console.ng.bluemix.net/catalog/services/mobile-client-access/) Service - used for fast and easy login using a Facebook (or other) account.
3. [Cloudant NoSQL DB](https://console.ng.bluemix.net/catalog/services/cloudant-nosql-db/) Service - used for secured data storage on the phone and synchronizing it to the cloud. 
4. [Push Notifications](https://console.ng.bluemix.net/catalog/services/push-notifications/) Service - used for sending user-specific notifications from the dashboard.


### Detailed end to end flow
![slide1](https://cloud.githubusercontent.com/assets/13234255/16177453/76944e48-3636-11e6-992f-ce5b8b7b2f82.png)



# Installing the application
You may either:

- Directly download the .apk file from [here](https://github.com/ibm-wearables-sdk-for-mobile/gym-application-android/blob/master/apk/gym_app.apk?raw=true)

or:

- Get the source code by cloning the repository and running the application directly using Android Studio


You can access the coach dashboard [here](http://wearablessdkgym.mybluemix.net/)

**We warmly recommend learning more about IBM Wearables SDK and the Gesture Recognition capabilities for [Android](https://github.com/ibm-wearables-sdk-for-mobile/ibm-wearables-android-sdk) and for [iOS](https://github.com/ibm-wearables-sdk-for-mobile/ibm-wearables-swift-sdk).**

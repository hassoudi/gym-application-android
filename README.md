# gym-application-android
This demo app shows how to build an app that utilizes Wearable sensors using the IBM Wearables SDK combined with Bluemix services.
This simple Gym application detects various exercises the trainee performs and counts the number of repetitions. An Android Wear device is used to sense movements and an Android Mobile Phone is used to interpret the data, recognize the movements and display repetiotions count to the trainee. 
A web-based Dashboard is available for the Coach, showing trainees' performance and allowing to send feedback to the trainee using push notifications.
You can access the dashboard here: http://wearablessdkgym.mybluemix.net/

### Utilization of Bluemix
1. Node.js server hosting - hosting the dashboard and the endpoints for the use of the application.
2. Mobile Client Access Service - used for fast and easy login using a Facebook (or other) account.
3. Cloudant Service - used for secured data storage on the phone and synchronizing it to the cloud. 
4. Push Service - used for sending user-specific notifications from the dashboard.


### Detailed end to end flow
![slide1](https://cloud.githubusercontent.com/assets/13234255/16177453/76944e48-3636-11e6-992f-ce5b8b7b2f82.png)



# Install the application
You can directly download the .apk file from here: https://github.com/ibm-wearables-sdk-for-mobile/gym-application-android/blob/master/apk/gym_app.apk?raw=true

or clone the repository and run the application directly from Android Studio

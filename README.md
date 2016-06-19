# gym-application-android
This ripository contains an end to end sample application of using the IBM Werables SDK with a combination of bluemix services.
The application displays simple gym application that enable to detect (using Android wear device sensors) 
variety of gym exercise movemenets and can track the repeast count.

Another part of the sample application is a dashboard where you can see all the users results and notify users with comments about
their exercises. you can access the dashboard here: http://wearablessdkgym.mybluemix.net/

### The use of Bluemix
1. Mobile Client Access Service - used to perform fast and easy login using the Facebook account
2. Cloudant Service - used to store all the exercise data per user
3. Push Service - used to send user bases norification from the gashboard
4. Node.js server hosting - hosting the dashboard and the endpoints for the use of the application


### Detailed end to end flow
![slide1](https://cloud.githubusercontent.com/assets/13234255/16177453/76944e48-3636-11e6-992f-ce5b8b7b2f82.png)



# Install the application
You can directly download the .apk file from here: https://github.com/ibm-wearables-sdk-for-mobile/gym-application-android/blob/master/apk/gym_app.apk?raw=true

or clone the repository and run the application directly from Android Studio

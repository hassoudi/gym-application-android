### gym-application-android
This ripository contains an end to end sample application of using the IBM Werables SDK with a combination of bluemix services.
The application displays simple gym application that enable to detect (using Android wear device sensors) 
variety of gym exercise movemenets and can track the repeast count.

Another part of the sample application is a dashboard where you can see all the users results and notify users with comments about
their exercises. you can access the dashboard here: http://wearablessdkgym.mybluemix.net/

# The use of Bluemix
1. Mobile Client Access Service - used to perform fast and easy login using the Facebook account
2. Cloudant Service - used to store all the exercise data per user
3. Push Service - used to send user bases norification from the gashboard
4. Node.js server hosting - hosting the dashboard and the endpoints for the use of the application


# Detailed end to end flow

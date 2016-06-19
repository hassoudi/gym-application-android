package com.ibm.mobilefirst.mobileedge.gym;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ibm.mobilefirstplatform.clientsdk.android.core.api.BMSClient;
import com.ibm.mobilefirstplatform.clientsdk.android.core.api.Request;
import com.ibm.mobilefirstplatform.clientsdk.android.core.api.Response;
import com.ibm.mobilefirstplatform.clientsdk.android.core.api.ResponseListener;
import com.ibm.mobilefirstplatform.clientsdk.android.push.api.MFPPush;
import com.ibm.mobilefirstplatform.clientsdk.android.push.api.MFPPushException;
import com.ibm.mobilefirstplatform.clientsdk.android.push.api.MFPPushNotificationListener;
import com.ibm.mobilefirstplatform.clientsdk.android.push.api.MFPPushResponseListener;
import com.ibm.mobilefirstplatform.clientsdk.android.push.api.MFPSimplePushNotification;
import com.ibm.mobilefirstplatform.clientsdk.android.security.facebookauthentication.FacebookAuthenticationManager;

import org.json.JSONObject;

import java.net.MalformedURLException;

public class LogInActivity extends AppCompatActivity {

    public static final String GymApp = "GymApp";
    MFPPush push;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookAuthenticationManager.getInstance().register(this);

        setContentView(R.layout.activity_log_in);

        findViewById(R.id.facebook_login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request request = new Request("/protected", Request.GET);
                request.send(LogInActivity.this, new ResponseListener() {
                    @Override
                    public void onSuccess (Response response) {
                        onLoginSuccess();
                    }
                    @Override
                    public void onFailure (Response response, Throwable t, JSONObject extendedInfo) {
                        if (null != t) {
                            Log.d(GymApp, "onFailure :: " + t.getMessage());
                        } else if (null != extendedInfo) {
                            Log.d(GymApp, "onFailure :: " + extendedInfo.toString());
                        } else {
                            Log.d(GymApp, "onFailure :: " + response.getResponseText());
                        }
                    }
                });
            }


        });

        try {
            BMSClient.getInstance().initialize(getApplicationContext(), "http://WearablesSDKGym.mybluemix.net", "905dd0f5-d2f2-47e1-8f5c-c4f088d20b91");

            push = MFPPush.getInstance();
            push.initialize(getApplicationContext());

            //Register the device
            push.register(new MFPPushResponseListener<String>() {
                @Override
                public void onSuccess(String deviceId) {
                    Log.d(GymApp, "onSuccess :: " + "Registered " + deviceId + " device");
                }
                @Override
                public void onFailure(MFPPushException ex) {
                    Log.d(GymApp, "onFailure :: " + "can't register device");
                    ex.printStackTrace();
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //connect to the android wear device
        GymApplication gymApplication = (GymApplication) getApplication();
        gymApplication.connectToAndroidWear();
    }

    private void onLoginSuccess() {
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FacebookAuthenticationManager.getInstance().onActivityResultCalled(requestCode, resultCode, data);
    }


    @Override
    protected void onResume(){
        super.onResume();

        //handles the notification when it arrives
        MFPPushNotificationListener notificationListener = new MFPPushNotificationListener() {
            @Override
            public void onReceive (final MFPSimplePushNotification message){
            }
        };

        if(push != null) {
            push.listen(notificationListener);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (push != null) {
            push.hold();
        }
    }
}

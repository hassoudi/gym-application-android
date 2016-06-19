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
import com.ibm.mobilefirstplatform.clientsdk.android.security.api.AuthorizationManager;
import com.ibm.mobilefirstplatform.clientsdk.android.security.facebookauthentication.FacebookAuthenticationManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;

public class LogInActivity extends AppCompatActivity {

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
                        //Log.d("Myapp", "onSuccess :: " + response.getResponseText());
                        //Log.d("MyApp", AuthorizationManager.getInstance().getUserIdentity().toString());

                        onLoginSuccess();
                        System.out.println(AuthorizationManager.getInstance().getUserIdentity().getDisplayName());
                    }
                    @Override
                    public void onFailure (Response response, Throwable t, JSONObject extendedInfo) {
                        if (null != t) {
                            Log.d("Myapp", "onFailure :: " + t.getMessage());
                        } else if (null != extendedInfo) {
                            Log.d("Myapp", "onFailure :: " + extendedInfo.toString());
                        } else {
                            Log.d("Myapp", "onFailure :: " + response.getResponseText());
                        }
                    }
                });
            }


        });

        try {
            BMSClient.getInstance().initialize(getApplicationContext(), "http://WearablesSDKGym.mybluemix.net", "905dd0f5-d2f2-47e1-8f5c-c4f088d20b91");


            push = MFPPush.getInstance();
            push.initialize(getApplicationContext());

            //Register Android devices
            push.register(new MFPPushResponseListener<String>() {
                @Override
                public void onSuccess(String deviceId) {
                    //handle success here
                }
                @Override
                public void onFailure(MFPPushException ex) {
                    //handle failure here
                }
            });

            //sendUpdateRequest();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //TODO: temp
        //onLoginSuccess();


        //sentTestRequest();


        //connect to the android wear device
        GymApplication gymApplication = (GymApplication) getApplication();
        gymApplication.connectToAndroidWear();
    }

    private void sendUpdateRequest() {

        Request request = new Request("/updateData", Request.POST);
        request.setQueryParameter("user","cirill6");
        request.setQueryParameter("id",AuthorizationManager.getInstance().getDeviceIdentity().getId());

        JSONArray results = new JSONArray();

        results.put(11);
        results.put(12);
        results.put(13);


        request.setQueryParameter("results", "33,44,55");

        request.send(LogInActivity.this, new ResponseListener() {
            @Override
            public void onSuccess (Response response) {
                Log.d("Myapp", "onSuccess :: " + response.getResponseText());
                //Log.d("MyApp", AuthorizationManager.getInstance().getUserIdentity().toString());

                //onLoginSuccess();
                //System.out.println(AuthorizationManager.getInstance().getUserIdentity().getDisplayName());
            }
            @Override
            public void onFailure (Response response, Throwable t, JSONObject extendedInfo) {
                if (null != t) {
                    Log.d("Myapp", "onFailure :: " + t.getMessage());
                } else if (null != extendedInfo) {
                    Log.d("Myapp", "onFailure :: " + extendedInfo.toString());
                } else {
                    Log.d("Myapp", "onFailure :: " + response.getResponseText());
                }
            }
        });
    }

    private void sentTestRequest() {

        Request request = new Request("/allUsers", Request.GET);
        request.send(LogInActivity.this, new ResponseListener() {
            @Override
            public void onSuccess (Response response) {
                Log.d("Myapp", "onSuccess :: " + response.getResponseText());
                //Log.d("MyApp", AuthorizationManager.getInstance().getUserIdentity().toString());

                //onLoginSuccess();
                //System.out.println(AuthorizationManager.getInstance().getUserIdentity().getDisplayName());
            }
            @Override
            public void onFailure (Response response, Throwable t, JSONObject extendedInfo) {
                if (null != t) {
                    Log.d("Myapp", "onFailure :: " + t.getMessage());
                } else if (null != extendedInfo) {
                    Log.d("Myapp", "onFailure :: " + extendedInfo.toString());
                } else {
                    Log.d("Myapp", "onFailure :: " + response.getResponseText());
                }
            }
        });
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

        //Handles the notification when it arrives
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

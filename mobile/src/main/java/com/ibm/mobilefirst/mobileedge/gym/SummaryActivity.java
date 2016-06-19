package com.ibm.mobilefirst.mobileedge.gym;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ibm.mobilefirstplatform.clientsdk.android.core.api.Request;
import com.ibm.mobilefirstplatform.clientsdk.android.core.api.Response;
import com.ibm.mobilefirstplatform.clientsdk.android.core.api.ResponseListener;
import com.ibm.mobilefirstplatform.clientsdk.android.security.api.AuthorizationManager;

import org.json.JSONObject;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        GymApplication gymApplication = (GymApplication) getApplication();



        /*
        setExercisesData(R.id.exercise1,0,gymApplication.exerciseCounts[0]);
        setExercisesData(R.id.exercise2,1,gymApplication.exerciseCounts[1]);
        setExercisesData(R.id.exercise3,2,gymApplication.exerciseCounts[2]);
        */

        setExercisesData(R.id.exerceise_name1,R.id.repeats1,R.id.done1,0,gymApplication.exerciseCounts[0]);
        setExercisesData(R.id.exerceise_name2,R.id.repeats2,R.id.done2,1,gymApplication.exerciseCounts[1]);
        setExercisesData(R.id.exerceise_name3,R.id.repeats3,R.id.done3,2,gymApplication.exerciseCounts[2]);


        sendUpdateRequest();
    }

    private void sendUpdateRequest() {

        Request request = new Request("/updateData", Request.POST);
        request.setQueryParameter("user",AuthorizationManager.getInstance().getUserIdentity().getDisplayName());
        request.setQueryParameter("id", AuthorizationManager.getInstance().getDeviceIdentity().getId());

        /*
        JSONArray results = new JSONArray();

        results.put(11);
        results.put(12);
        results.put(13);
        */

        GymApplication application = (GymApplication) getApplication();

        String resultsStr = String.format("%d,%d,%d",application.exerciseCounts[0],application.exerciseCounts[1],application.exerciseCounts[2]);
        request.setQueryParameter("results", resultsStr);

        request.send(SummaryActivity.this, new ResponseListener() {
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


    private void setExercisesData(int nameResourceId, int repeatsResourceId, int doneResourceId, int exerciseNumber, int finalRepeats) {

        ExerciseData data = new ExerciseData(getResources(),exerciseNumber);

        ((TextView)findViewById(nameResourceId)).setText(data.name);
        ((TextView)findViewById(repeatsResourceId)).setText(Integer.toString(finalRepeats));

        if (finalRepeats == 0){
            findViewById(doneResourceId).setVisibility(View.INVISIBLE);
        }
    }


    /*
    private void setExercisesData(int resourceId, int exerciseNumber, int repeats) {
        ExerciseData data = new ExerciseData(getResources(),exerciseNumber);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(resourceId);
        ((TextView)relativeLayout.findViewById(R.id.exerceise_name)).setText(data.name);
        ((TextView)relativeLayout.findViewById(R.id.repeats)).setText(Integer.toString(repeats));

        if (repeats == 0){
            relativeLayout.findViewById(R.id.doneImage).setVisibility(View.INVISIBLE);
        }
    }
    */
}

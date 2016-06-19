package com.ibm.mobilefirst.mobileedge.gym;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ibm.mobilefirstplatform.clientsdk.android.security.api.AuthorizationManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.startWorkoutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationManager.getInstance().startWorkout(MainActivity.this);
            }
        });

        TextView userName = (TextView) findViewById(R.id.user_name);
        TextView workoutDate = (TextView) findViewById(R.id.workout_date);

        userName.setText(AuthorizationManager.getInstance().getUserIdentity().getDisplayName());

        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        String date = df.format(Calendar.getInstance().getTime());

        workoutDate.setText(date);

        //setExercisesData(R.id.exercise1,0);
        //setExercisesData(R.id.exercise2,1);
        //setExercisesData(R.id.exercise3,2);

        setExercisesData(R.id.exerceise_name1,R.id.repeats1,0);
        setExercisesData(R.id.exerceise_name2,R.id.repeats2,1);
        setExercisesData(R.id.exerceise_name3,R.id.repeats3,2);
    }


    /*
    private void setExercisesData(int resourceId, int exerciseNumber) {

        ExerciseData data = new ExerciseData(getResources(),exerciseNumber);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(resourceId);

        ((TextView)relativeLayout.findViewById(R.id.repeats)).setText(Integer.toString(data.repeats));
        ((TextView)relativeLayout.findViewById(R.id.exerceise_name)).setText(data.name);
    }

    */

    private void setExercisesData(int nameResourceId, int repeatsResourceId, int exerciseNumber) {

        ExerciseData data = new ExerciseData(getResources(),exerciseNumber);

        ((TextView)findViewById(repeatsResourceId)).setText(Integer.toString(data.repeats));
        ((TextView)findViewById(nameResourceId)).setText(data.name);
    }


}

package com.ibm.mobilefirst.mobileedge.gym;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    public static final String IMAGE = "image";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";


    ImageView exerciseImage;
    TextView exerciseName;
    TextView exerciseDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        exerciseImage = (ImageView) findViewById(R.id.exerciseImage);
        exerciseName = (TextView) findViewById(R.id.exerciseName);
        exerciseDescription = (TextView) findViewById(R.id.exerciseDescription);
        
        //set up all the data
        final int exerciseNumber = getIntent().getExtras().getInt(NavigationManager.EXERCISE_NUMBER);
        ExerciseData exerciseData = new ExerciseData(getResources(),exerciseNumber);


        setTitle(exerciseData.name);

//        exerciseImage.setImageResource(getResources().obtainTypedArray(R.array.exercise_images).getResourceId(exerciseNumber,-1));
//        exerciseName.setText(getResources().getStringArray(R.array.exercise_name)[exerciseNumber]);
//        exerciseDescription.setText(getResources().getStringArray(R.array.exercise_description)[exerciseNumber]);

        exerciseImage.setImageResource(exerciseData.imageResourceId);
        exerciseName.setText(exerciseData.name);
        exerciseDescription.setText(exerciseData.description);


        findViewById(R.id.startExerciseButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationManager.getInstance().onExerciseIntroFinished(StartActivity.this, exerciseNumber);
            }
        });
    }
}

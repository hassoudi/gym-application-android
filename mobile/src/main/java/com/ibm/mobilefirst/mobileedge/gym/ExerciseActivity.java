package com.ibm.mobilefirst.mobileedge.gym;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ibm.mobilefirst.mobileedge.interpretation.InterpretationListener;

public class ExerciseActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView counterTextView;
    int repeats;
    int exerciseNumber;

    GymApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        application = (GymApplication) getApplication();

        exerciseNumber = getIntent().getExtras().getInt(NavigationManager.EXERCISE_NUMBER);

        ExerciseData exerciseData = new ExerciseData(getResources(),exerciseNumber);

        repeats = exerciseData.repeats;

        //setup the display data
        ((TextView)findViewById(R.id.statusLine)).setText(String.format(getString(R.string.total_repeats),exerciseData.repeats));
        ((TextView)findViewById(R.id.exerciseName)).setText(exerciseData.name);
        ((ImageView)findViewById(R.id.exerciseImage)).setImageResource(exerciseData.imageResourceId);

        setTitle(exerciseData.name);

        //setup progress bar
        progressBar = (ProgressBar) findViewById(R.id.exerciseProgress);
        progressBar.setMax(exerciseData.repeats);
        progressBar.setProgress(0);

        counterTextView = (TextView) findViewById(R.id.currentRepeats);


        //((RotateDrawable)progressBar.getProgressDrawable());

        //progressBar.setProgressDrawable(getProgressBarDrawable());

        //progressBar.setP

        //progressBar.setProgressDrawable();

        //getResources().getDrawable(R.drawable.progress_bar_blue,null);

        View.OnClickListener exerciseFinished = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onExerciseFinished();
            }
        };

        findViewById(R.id.skip_exercise_button).setOnClickListener(exerciseFinished);
    }

    private void updateCounter(){
        progressBar.incrementProgressBy(1);
        counterTextView.setText(Integer.toString(progressBar.getProgress()));

        if (progressBar.getProgress() == progressBar.getMax()){

            showExerciseEnded();
            //onExerciseFinished();
        }
    }


    private void showExerciseEnded(){
        findViewById(R.id.well_done_text).setVisibility(View.VISIBLE);
        findViewById(R.id.skip_exercise_button).setVisibility(View.INVISIBLE);
        findViewById(R.id.statusLine).setVisibility(View.INVISIBLE);

        Button nextButton = (Button) findViewById(R.id.next_exercise_button);
        nextButton.setVisibility(View.VISIBLE);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onExerciseFinished();
            }
        });
    }


    private void onExerciseFinished(){

        //save the exercise data
        application.exerciseCounts[exerciseNumber] = progressBar.getProgress();

        //continue to next screen
        NavigationManager.getInstance().onExerciseFinished(ExerciseActivity.this, exerciseNumber);
    }

    @Override
    protected void onResume() {
        super.onResume();

        application.startExerciseGestureDetection(exerciseNumber, new InterpretationListener() {
            @Override
            public void onInterpretationDetected(String s, Object o) {
                updateCounter();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        application.stopExerciseGestureDetection();
    }

    /*
    private Drawable getProgressBarDrawable(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getResources().getDrawable(R.drawable.progress_bar_blue, getTheme());
        } else {
            return getResources().getDrawable(R.drawable.progress_bar_blue);
        }
    }
    */
}

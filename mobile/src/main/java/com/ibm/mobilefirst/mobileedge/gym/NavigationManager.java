package com.ibm.mobilefirst.mobileedge.gym;

import android.content.Context;
import android.content.Intent;

/**
 * Controls the navigation flow of the application
 */
public class NavigationManager {

    public static final String EXERCISE_NUMBER = "exercise_number";
    final int NUMBER_OF_EXERCISES = 3;

    private static NavigationManager ourInstance = new NavigationManager();

    public static NavigationManager getInstance() {
        return ourInstance;
    }

    private NavigationManager() {
    }

    public void startWorkout(Context context){
        showExerciseIntro(context,0);
    }

    public void onExerciseIntroFinished(Context context, int exerciseNumber){
        showExerciseTrain(context,exerciseNumber);
    }

    public void onExerciseFinished(Context context, int exerciseNumber){
        if (exerciseNumber < NUMBER_OF_EXERCISES - 1){
            showExerciseIntro(context,++exerciseNumber);
        }
        else {
            showResultPage(context);
        }
    }



    private void showExerciseIntro(Context context, int exerciseNumber){
        Intent intent = new Intent(context, StartActivity.class);
        intent.putExtra(EXERCISE_NUMBER,exerciseNumber);
        context.startActivity(intent);
    }

    private void showExerciseTrain(Context context, int exerciseNumber){
        Intent intent = new Intent(context, ExerciseActivity.class);
        intent.putExtra(EXERCISE_NUMBER,exerciseNumber);
        context.startActivity(intent);
    }

    private void showResultPage(Context context){
        context.startActivity(new Intent(context,SummaryActivity.class));
    }
}

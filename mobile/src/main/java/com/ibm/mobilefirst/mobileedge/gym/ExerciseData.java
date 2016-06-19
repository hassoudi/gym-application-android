package com.ibm.mobilefirst.mobileedge.gym;

import android.content.res.Resources;

/**
 * Holds single exercise data
 */
public class ExerciseData {

    public int number;
    public String description;
    public String name;
    public int repeats;
    public int imageResourceId;

    public ExerciseData(Resources resources, int exerciseNumber){
        number = exerciseNumber;
        imageResourceId = resources.obtainTypedArray(R.array.exercise_images).getResourceId(exerciseNumber,-1);
        name = resources.getStringArray(R.array.exercise_name)[exerciseNumber];
        description = resources.getStringArray(R.array.exercise_description)[exerciseNumber];
        repeats = resources.getIntArray(R.array.exercise_repeats)[exerciseNumber];
    }
}

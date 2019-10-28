package com.HenrikJangefelt.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class Workout contains an ArrayList of Exercises as well as a String attribute called workoutname.
 */
public class Workout implements Serializable {

    private ArrayList<Exercise> exerciseList = new ArrayList<>();
    private String workoutName;

    public Workout(String workoutName) {
        this.workoutName = workoutName;
    }

    public ArrayList<Exercise> getExerciseList() {
        return exerciseList;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String toString() {

        return String.format("%s (Workout have %s exercises)", workoutName, exerciseList.size());
        //return workoutName + "\n" + exerciseList.toString();
    }


}

package com.HenrikJangefelt;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: ta bort comparable? Eller ha det bara i workouts
public class Workout implements Comparable<Workout>, Serializable {

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

    public void addExercise(String exerciseName, int amountOfReps, int amountOfSets, Exercise.Muscle targetedMuscle) {
        exerciseList.add(new Exercise(exerciseName, amountOfReps, amountOfSets, targetedMuscle));
    }

    public void removeExercise(int indexPosition) {
        exerciseList.remove(indexPosition);
    }

    // TODO, ha bara logick, inga prints?
    public void showExercises(int workoutIndex) {
        for (int i = 0; i < exerciseList.size(); i++) {
            System.out.printf("\t%s.%s %s\n", workoutIndex + 1, i + 1, exerciseList.get(i).toString());
        }
    }

    // TODO: skriv även ut exercises som tillhör???
    public String toString() {
        return workoutName + " (total number of exercises: " + exerciseList.size() + ")";
    }


    // TODO: kan ta bort? Eller ha kvar enbart för workout (sorteras bara efter namn)
    @Override
    public int compareTo(Workout o) {
        return 0;
    }
}

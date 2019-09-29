package com.HenrikJangefelt;

import java.util.ArrayList;

// TODO: ta bort comparable?
public class Workout implements Comparable<Workout> {

    private ArrayList<Exercise> exerciseList = new ArrayList<>();
    private String workoutName;
    //private int totalAmountOfReps;
    //private int totalAmountOfSets;
    //private int totalAmountOfExercises;

    // TODO: Sätt till private (ha funktion public ArrayList<Exercise> getExerciseList() { return exerciseList }


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



    // TODO: lägg logik för att lägga till övningar här?

    public void removeExercise(int indexPosition) {
        exerciseList.remove(indexPosition);
    }

    //public void addExercise(String exerciseName, int amountOfReps, int amountOfSets) {
    public void addExercise(String exerciseName, int amountOfReps, int amountOfSets, Exercise.Muscle targetedMuscle) { // TEST

        Exercise exercise = new Exercise(exerciseName, amountOfReps, amountOfSets, targetedMuscle);
        exerciseList.add(exercise);
    }


    public void showExercises(int workoutIndex) {

        for (int i = 0; i < exerciseList.size(); i++) {
            System.out.printf("\t%s.%s %s\n", workoutIndex + 1, i + 1 , exerciseList.get(i).toString());
        }
    }
    /*public void showExercises(int workoutIndex) {

        for (int i = 0; i < exerciseList.size(); i++) {
            System.out.printf("\t%s.%s %s\n", workoutIndex, i + 1 , exerciseList.get(i).getExercise());
        }
    }*/

    // TODO: Clean up
    /*public void showExercises() {

        for (int i = 0; i < exerciseList.size(); i++) {
            //System.out.println(exerciseList.get(i).getExercise());
        }
    }*/




    // Vad som ska vissas nåär man printar workout
    public String toString() {
        //return workoutName;
        // TODO: getExervciseName
        return getWorkoutName();
    }


    // TODO: kan ta bort?
    @Override
    public int compareTo(Workout o) {
        return 0;
    }
}

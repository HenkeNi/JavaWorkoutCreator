package com.HenrikJangefelt;

// TODO: rätt att den ärver från workout??? Behöver workout name?
public class Exercise {

    private String exerciseName;
    private int numberOfReps;
    private int numberOfSets;
    private Muscle targetedMuscle;
    // private float weightUsed;
    // private // enum muscleGroup


    enum Muscle {
        CHEST,
        BACK,
        SHOULDERS,
        BICEPS,
        TRICEPS,
        ABS,
        LEGS,
        UNKOWN
    }


    public Exercise(String exerciseName, int numberOfReps, int numberOfSets, Muscle targetedMuscle) {
    //public Exercise(String exerciseName, int numberOfReps, int numberOfSets) {

        this.exerciseName = exerciseName;
        this.numberOfReps = numberOfReps;
        this.numberOfSets = numberOfSets;
        this.targetedMuscle = targetedMuscle; // TEST
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getExercise() {
        //return exerciseName + " (Reps: " + numberOfReps + ", Sets: " + numberOfSets + ")"; // TODO: Fixa, inte jätte snyggt
        return exerciseName + " (Reps: " + numberOfReps + ", Sets: " + numberOfSets + ", Targeted Muscle: " + targetedMuscle + ")";
    }

    public String toString() {
        return exerciseName + " (Reps: " + numberOfReps + ", Sets: " + numberOfSets + ", Targeted Muscle: " + targetedMuscle + ")";
    }

    // Set
    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setNumberOfReps(int numberOfReps) {
        this.numberOfReps = numberOfReps;
    }

    public void setNumberOfSets(int numberOfSets) {
        this.numberOfSets = numberOfSets;
    }

    public void setTargetedMuscle(Muscle targetedMuscle) {
        this.targetedMuscle = targetedMuscle;
    }

    // TEST (Lägg i konstruktorn)
    public String getTargetedMuscle() {
        //return Muscle.valueOf(targetedMuscle.toString());
        return targetedMuscle.toString();
        //return "Main muscle worked: " + targetedMuscle.toString();
    }



}

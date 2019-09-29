package com.HenrikJangefelt;

// TODO: Ta bort comparable
public class Exercise implements Comparable<Exercise> {

    private String exerciseName;
    private Muscle targetedMuscle;
    private int numberOfReps;
    private int numberOfSets;
    // private float weightUsed; // TODO: add weight used?


    enum Muscle {
        CHEST("Chest"),
        BACK("Back"),
        SHOULDERS("Shoulders"),
        BICEPS("Bicep"),
        TRICEPS("Tricep"),
        ABS("Abs"),
        LEGS("Legs"),
        UNKOWN("Unspecified");

        public String label;
        private Muscle(String label) {
            this.label = label;
        }
    }


    public Exercise(String exerciseName, int numberOfReps, int numberOfSets, Muscle targetedMuscle) {
        this.exerciseName = exerciseName;
        this.numberOfReps = numberOfReps;
        this.numberOfSets = numberOfSets;
        this.targetedMuscle = targetedMuscle; // TEST
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getNumberOfReps() {
        return numberOfReps;
    }

    public int getNumberOfSets() {
        return numberOfSets;
    }

    // TODO: ta bort
    /*public String getExercise() {
        return exerciseName + " (Reps: " + numberOfReps + ", Sets: " + numberOfSets + ", Targeted Muscle: " + targetedMuscle.label + ")";
    }*/


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

    public String toString() {
        return exerciseName + " (Reps: " + numberOfReps + ", Sets: " + numberOfSets + ", Targeted Muscle: " + targetedMuscle + ")";
    }


    // TEST (Lägg i konstruktorn)
    public String getTargetedMuscle() {
        //return Muscle.valueOf(targetedMuscle.toString());
        return targetedMuscle.toString();
        //return "Main muscle worked: " + targetedMuscle.toString();
    }

    // TODO: kan ta bort?
    @Override
    public int compareTo(Exercise o) {
        return 0;
    }


}

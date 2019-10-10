package com.HenrikJangefelt;

import java.io.Serializable;

// TODO: Ta bort comparable
public class Exercise implements Serializable {

    enum Muscle {
        CHEST("Chest"),
        BACK("Back"),
        SHOULDERS("Shoulders"),
        BICEPS("Biceps"),
        TRICEPS("Triceps"),
        ABS("Abs"),
        LEGS("Legs"),
        UNKOWN("Unspecified"); // TODO: Ta bort

        public String label;

        Muscle(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private String exerciseName;
    private Muscle targetedMuscle;
    private int numberOfReps;
    private int numberOfSets;
    // private float weightUsed; // TODO: add weight used?


    public Exercise(String exerciseName, int numberOfReps, int numberOfSets, Muscle targetedMuscle) {
        this.exerciseName = exerciseName;
        this.numberOfReps = numberOfReps;
        this.numberOfSets = numberOfSets;
        this.targetedMuscle = targetedMuscle;
    }


    public int getNumberOfReps() {
        return numberOfReps;
    }

    public int getNumberOfSets() {
        return numberOfSets;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getTargetedMuscle() { return targetedMuscle.label; } //toString();

    public void setNumberOfReps(int numberOfReps) {
        this.numberOfReps = numberOfReps;
    }

    public void setNumberOfSets(int numberOfSets) {
        this.numberOfSets = numberOfSets;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setTargetedMuscle(Muscle targetedMuscle) {
        this.targetedMuscle = targetedMuscle;
    }


    public String toString() {
        return exerciseName +
                " (Reps: " + numberOfReps +
                ", Sets: " + numberOfSets +
                ", Targeted Muscle: " + targetedMuscle +
                ")";
    }
}

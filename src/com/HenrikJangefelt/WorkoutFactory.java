package com.HenrikJangefelt;

// TODO: mer logik i denna klass
// TODO: abstract?
public class WorkoutFactory {

    // TODO: enum för olika typer av workouts?


    // TODO: rätt att göra dem till static???
    public static Workout createWorkout(String workoutName) {
        return new Workout(workoutName);
    }

    public static Exercise createExercise(String exerciseName, int amountOfReps, int amountOfSets, Exercise.Muscle targetedMuscle) {
        return new Exercise(exerciseName, amountOfReps, amountOfSets, targetedMuscle);
    }


    // TODO: EN GEMENSAM GENERIC?


}

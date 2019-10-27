package com.HenrikJangefelt.factories;

import com.HenrikJangefelt.models.Exercise;
import com.HenrikJangefelt.models.UserInput;
import com.HenrikJangefelt.models.Workout;
import com.HenrikJangefelt.view.View;

/**
 * WorkoutFactory is incharge of creating workouts and exercises.
 */
public class WorkoutFactory {

    static View view = View.getInstance();

    /**
     * Method for creating a Workout.
     * @return Returns a Workout object.
     */
    public static Workout createWorkout() {

        view.showMessage("Enter workout's name:");
        Workout workout = new Workout(view.getUserInput(UserInput.InputType.STRING).getStringValue());

        return workout;
    }

    /**
     * Method responsible for creating an Exercise.
     * @return Returns an Exercise object.
     */
    public static Exercise createExercise() {

        view.showMessage("Exercise name");
        String exerciseName = view.getUserInput(UserInput.InputType.STRING).getStringValue();

        view.showMessage("Number of reps:");
        int numberOfReps = view.getUserInput(UserInput.InputType.INT).getIntValue();

        view.showMessage("Number of sets:");
        int numberOfSets = view.getUserInput(UserInput.InputType.INT).getIntValue();

        return new Exercise(exerciseName, numberOfReps, numberOfSets, getMuscleGroup());
    }


    /**
     * Method that displays each muscle group.
     * @return Returns selected muscle group.
     */
    public static Exercise.Muscle getMuscleGroup() {

        view.showMenu(Exercise.Muscle.class, "Choose targeted muscle for exercise:");
        return view.getMenuItem(Exercise.Muscle.class);
    }


}

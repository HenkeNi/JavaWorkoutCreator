package com.HenrikJangefelt.sortby;

import com.HenrikJangefelt.models.Workout;
import java.util.Comparator;

/**
 * <h1>SortWorkout</h1>
 * The class SortWorkout contains nested classes responsible for sorting an ArrayList of Workout in various ways.
 */
public class SortWorkout {

    /**
     * SortWorkoutName sorts objects of Workout type by their name.
     */
    public static class SortWorkoutName implements Comparator<Workout> {

        @Override
        public int compare(Workout o1, Workout o2) {
            return o1.getWorkoutName().compareTo(o2.getWorkoutName());
        }
    }

    /**
     * SortWorkoutExercises sorts objects of Workout type by their number of exercises.
     */
    public static class SortWorkoutExercises implements Comparator<Workout> {

        @Override
        public int compare(Workout o1, Workout o2) {
            return o1.getExerciseList().size() - o2.getExerciseList().size();
        }
    }



}



package com.HenrikJangefelt.sortby;

import com.HenrikJangefelt.models.Exercise;

import java.util.Comparator;

/**
 * <h1>SortExercise</h1>
 * The class SortExercise contains nested classes responsible for sorting an ArrayList of exercises in various ways.
 *
 * @author Henrik Jangefelt Nilsson
 */
public class SortExercise {

    /**
     * SortExerciseName sorts objects of Exercise type by their name.
     */
    public static class SortExerciseName implements Comparator<Exercise> {

        @Override
        public int compare(Exercise o1, Exercise o2) {

            return o1.getExerciseName().toLowerCase().compareTo(o2.getExerciseName().toLowerCase());
        }
    }

    /**
     * SortExerciseReps sort objects of Exercise type by the amount of reps.
     */
    public static class SortExerciseReps implements Comparator<Exercise> {

        @Override
        public int compare(Exercise o1, Exercise o2) {

            return o1.getNumberOfReps() - o2.getNumberOfReps();
        }
    }

    /**
     * SortExerciseSets sort objects of Exercise type by the amount of sets.
     */
    public static class SortExerciseSets implements Comparator<Exercise> {

        @Override
        public int compare(Exercise o1, Exercise o2) {

            return o1.getNumberOfSets() - o2.getNumberOfSets();
        }
    }

    /**
     * SortExerciseMuscle sort objects of Exercise type by their muscle group.
     */
    public static class SortExercisesMuscle implements Comparator<Exercise> {

        @Override
        public int compare(Exercise o1, Exercise o2) {
            return o1.getTargetedMuscle().compareTo(o2.getTargetedMuscle());
        }
    }

}



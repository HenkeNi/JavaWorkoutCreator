package com.HenrikJangefelt.sortby;

import com.HenrikJangefelt.models.Exercise;

import java.util.Comparator;

// TODO: static eller vanliga?
// TODO: lägg sortBy i controllers??
public class SortExercise {

    public static class SortExerciseName implements Comparator<Exercise> {

        @Override
        public int compare(Exercise o1, Exercise o2) {

            return o1.getExerciseName().toLowerCase().compareTo(o2.getExerciseName().toLowerCase());
        }
    }

    public static class SortExerciseReps implements Comparator<Exercise> {

        @Override
        public int compare(Exercise o1, Exercise o2) {

            return o1.getNumberOfReps() - o2.getNumberOfReps();
        }
    }

    public static class SortExerciseSets implements Comparator<Exercise> {

        @Override
        public int compare(Exercise o1, Exercise o2) {

            return o1.getNumberOfSets() - o2.getNumberOfSets();
        }
    }

    public static class SortExercisesMuscle implements Comparator<Exercise> {

        @Override
        public int compare(Exercise o1, Exercise o2) {
            return o1.getTargetedMuscle().compareTo(o2.getTargetedMuscle());
        }
    }

}



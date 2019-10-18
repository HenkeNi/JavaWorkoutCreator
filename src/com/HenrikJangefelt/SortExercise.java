package com.HenrikJangefelt;

import java.util.Comparator;

// TODO: static eller vanliga?
public class SortExercise {

    static class SortExerciseName implements Comparator<Exercise> {

        @Override
        public int compare(Exercise o1, Exercise o2) {

            return o1.getExerciseName().toLowerCase().compareTo(o2.getExerciseName().toLowerCase());
        }
    }

    static class SortExerciseReps implements Comparator<Exercise> {

        @Override
        public int compare(Exercise o1, Exercise o2) {

            return o1.getNumberOfReps() - o2.getNumberOfReps();
        }
    }

    static class SortExerciseSets implements Comparator<Exercise> {

        @Override
        public int compare(Exercise o1, Exercise o2) {

            return o1.getNumberOfSets() - o2.getNumberOfSets();
        }
    }

    static class SortExercisesMuscle implements Comparator<Exercise> {

        @Override
        public int compare(Exercise o1, Exercise o2) {
            return o1.getTargetedMuscle().compareTo(o2.getTargetedMuscle());
        }
    }

}



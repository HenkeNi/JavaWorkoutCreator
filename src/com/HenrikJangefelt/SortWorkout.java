package com.HenrikJangefelt;

import java.util.Comparator;

public class SortWorkout {

    static class SortWorkoutName implements Comparator<Workout> {

        @Override
        public int compare(Workout o1, Workout o2) {
            return o1.getWorkoutName().compareTo(o2.getWorkoutName());
        }
    }


    static class SortWorkoutExercises implements Comparator<Workout> {

        @Override
        public int compare(Workout o1, Workout o2) {
            return o1.getExerciseList().size() - o2.getExerciseList().size();
        }
    }



}



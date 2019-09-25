package com.HenrikJangefelt;

import java.util.Comparator;

public class SortWorkoutName implements Comparator<Workout> {


    @Override
    public int compare(Workout o1, Workout o2) {
        return o1.getWorkoutName().compareTo(o2.getWorkoutName());
    }
}

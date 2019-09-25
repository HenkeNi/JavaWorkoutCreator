package com.HenrikJangefelt;

import java.util.Comparator;

// TODO: kan ta bort?
public class SortExercise {
}

class SortExerciseName implements Comparator<Exercise> {

    @Override
    public int compare(Exercise o1, Exercise o2) {
        return o1.getExerciseName().compareTo(o2.getExerciseName());
    }
}

class SortExerciseReps implements Comparator<Exercise> {

    @Override
    public int compare(Exercise o1, Exercise o2) {
        return o1.getNumberOfReps() - o2.getNumberOfReps();
    }
}

class SortExerciseSets implements Comparator<Exercise> {

    @Override
    public int compare(Exercise o1, Exercise o2) {
        return o1.getNumberOfSets() - o2.getNumberOfSets();
    }
}

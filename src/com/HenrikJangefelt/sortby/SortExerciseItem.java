package com.HenrikJangefelt.sortby;

/**
 * Menu items for sorting exercises.
 */
public enum SortExerciseItem {

    NAME("Name"),
    REPS("Reps"),
    SETS("Sets"),
    MUSCLE("Muscle"),
    BACK("Go Back");

    private String description;

    SortExerciseItem(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}

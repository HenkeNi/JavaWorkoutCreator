package com.HenrikJangefelt.sortby;

/**
 * Menu items for sorting workouts.
 */
public enum SortWorkoutItem {

    NAME("Name"),
    EXERCISES("Number of Exercises"),
    BACK("Go Back");

    private String description;

    SortWorkoutItem(String description) { this.description = description; }

    @Override
    public String toString() {
        return description;
    }
}

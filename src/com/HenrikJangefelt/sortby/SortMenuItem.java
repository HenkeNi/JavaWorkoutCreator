package com.HenrikJangefelt.sortby;

/**
 * Menu items for sorting menu.
 */
public enum SortMenuItem {

    WORKOUTS("Workouts"),
    EXERCISES("Exercises"),
    BACK("Go Back");

    private String description;

    SortMenuItem(String description) { this.description = description; }

    @Override
    public String toString() {
        return description;
    }
}

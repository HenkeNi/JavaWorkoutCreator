package com.HenrikJangefelt;

public enum MainMenuItem {

    WORKOUTS("Workouts"),
    FRIENDS("Friends"),
    AVAILABLE_STAFF("Check Available Staff"),
    HELP("Help"),
    EXIT("Exit");

    private String description;

    MainMenuItem(String description) { this.description = description; }

    @Override
    public String toString() {
            return description;
        }

}

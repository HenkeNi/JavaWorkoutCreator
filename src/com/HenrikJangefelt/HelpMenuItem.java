package com.HenrikJangefelt;

public enum HelpMenuItem {

    HOW_CREATE_WORKOUT("How to create a workout"),
    HOW_EDIT_WORKOUT("How to edit a workout"),
    HOW_SEARCH_WORKOUT("How to search for a workout"),
    GO_BACK("Go Back");

    private String description;

    HelpMenuItem(String description) { this.description = description; }

    @Override
    public String toString() {
        return description;
    }
}

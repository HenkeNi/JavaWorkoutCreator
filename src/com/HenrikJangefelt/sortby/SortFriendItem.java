package com.HenrikJangefelt.sortby;

/**
 * Menu items for sorting friends.
 */
public enum SortFriendItem {

    FIRST_NAME("Fist name"),
    LAST_NAME("Last name"),
    BACK("Go Back");

    private String description;

    SortFriendItem(String description) { this.description = description; }

    @Override
    public String toString() {
        return description;
    }
}

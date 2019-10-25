package com.HenrikJangefelt;

public enum SubMenuItem {

    ADD("Add"),
    EDIT("Edit"),
    SHOW("Show"),
    SORT("Sort"),
    SEARCH("Search"),
    REMOVE("Remove"),
    BACK("Go Back");

    private String description;

    SubMenuItem(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

}

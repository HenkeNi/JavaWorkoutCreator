package com.HenrikJangefelt;

public enum EditExerciseItem {

    EDIT_NAME("Change exercise name"),
    EDIT_REPS("Change number of reps"),
    EDIT_SETS("Change number of sets"),
    EDIT_MUSCLE("Change worked muscle"),
    GO_BACK("Go back");

    private String description;

    EditExerciseItem(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}

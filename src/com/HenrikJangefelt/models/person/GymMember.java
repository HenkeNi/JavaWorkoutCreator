package com.HenrikJangefelt.models.person;

import com.HenrikJangefelt.Introduce;
import com.HenrikJangefelt.models.Workout;
import com.HenrikJangefelt.view.View;

import java.io.Serializable;
import java.util.ArrayList;

public class GymMember extends Person {

    View view = View.getInstance();

    private ArrayList<Workout> workoutList = new ArrayList<>();
    private ArrayList<Person> friendList = new ArrayList<>();

    public GymMember(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public ArrayList<Workout> getWorkoutList() {
        return workoutList;
    }

    public ArrayList<Person> getFriendList() {
        return friendList;
    }

    @Override
    public void personalIntroduction() {
        view.showMessage("\t \"My name is " + getFullName() + " and I'm a Gym member\"");
    }

}
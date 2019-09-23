package com.HenrikJangefelt;

import java.util.ArrayList;

// TODO: can/should only create one instance of this object
public class PersonalTrainer extends Person {

    final int MAX_AMOUNT_OF_CLIENTS = 10;
    GymMember[] clientList = new GymMember[MAX_AMOUNT_OF_CLIENTS];
    ArrayList<Workout> personalWorkoutList = new ArrayList<>(); // TODO: lägg i person??

    public PersonalTrainer(String firstName, String lastName) {

        super(firstName, lastName);
    }





}

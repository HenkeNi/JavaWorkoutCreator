package com.HenrikJangefelt;

import java.util.ArrayList;

// TODO: TA BORT???
// TEST (Obs this class should only be created once(Static??)
public class CurrentUser extends GymMember {

    //static CurrentUser currentUser = new CurrentUser(String firstName, String lastName);

    ArrayList<GymMember> gymBuddies = new ArrayList<>();

    public CurrentUser(String firstName, String lastName) {
        super(firstName, lastName);
    }




}

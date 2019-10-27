//package com.HenrikJangefelt;
package com.HenrikJangefelt.models.person;


import com.HenrikJangefelt.Introduce;
import com.HenrikJangefelt.models.Workout;
import com.HenrikJangefelt.view.View;

import java.util.ArrayList;

public class PersonalTrainer extends Person implements Introduce {

    View view = View.getInstance();


    final int MAX_AMOUNT_OF_CLIENTS = 10;
    GymMember[] clientList = new GymMember[MAX_AMOUNT_OF_CLIENTS];
    ArrayList<Workout> clientWorkoutList = new ArrayList<>();

    public PersonalTrainer(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public void introduceYourself() {
        view.showMessage("My name is " + getFullName() + " and I'm a Personal Trainer");
    }
}

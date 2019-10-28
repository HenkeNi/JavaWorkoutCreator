//package com.HenrikJangefelt;
package com.HenrikJangefelt.models.person;


import com.HenrikJangefelt.Introduce;
import com.HenrikJangefelt.models.Workout;
import com.HenrikJangefelt.view.View;

import java.util.ArrayList;

public class PersonalTrainer extends Person implements Introduce {

    View view = View.getInstance();

    private final int MAX_AMOUNT_OF_CLIENTS = 10;
    private GymMember[] clientList = new GymMember[MAX_AMOUNT_OF_CLIENTS];

    public PersonalTrainer(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public int getMAX_AMOUNT_OF_CLIENTS() { return MAX_AMOUNT_OF_CLIENTS; }

    public GymMember[] getClientList() { return clientList; }

    public void setClientList(GymMember[] clientList) { this.clientList = clientList; }

    @Override
    public void introduceYourself() {
        view.showMessage("\t \"My name is " + getFullName() + " and I'm a Personal Trainer\"");
    }
}

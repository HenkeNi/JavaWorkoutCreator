package com.HenrikJangefelt.factories;

import com.HenrikJangefelt.models.UserInput;
import com.HenrikJangefelt.models.person.GymMember;
import com.HenrikJangefelt.models.person.Person;
import com.HenrikJangefelt.models.person.PersonalTrainer;
import com.HenrikJangefelt.models.person.StaffMember;
import com.HenrikJangefelt.view.View;

/**
 * Class responsible for creating new friends.
 */
public class FriendFactory {

    public enum FriendType {
        GYM_MEMBER("Gym member"),
        STAFF_MEMBER("Staff member"),
        PERSONAL_TRAINER("Personal trainer");

        private String description;

        FriendType(String description) { this.description = description; }

        @Override
        public String toString() {
            return description;
        }
    }

    static View view = View.getInstance(); // Get instance to View class


    /**
     * Method that creates a friend of either gymMember, staffMember or personalTrainer type.
     * @return Returns either of the three sub classes of Person.
     */
    public static Person createFriend() {

        view.showMenu(FriendType.class, "Choose what kind of friend to add:");

        FriendType friendType = view.getMenuItem(FriendType.class);

        view.showMessage("First name:");
        String firstName = view.getUserInput(UserInput.InputType.STRING).getStringValue();

        view.showMessage("Last name:");
        String lastName = view.getUserInput(UserInput.InputType.STRING).getStringValue();

        switch (friendType) {
            case GYM_MEMBER:
                return new GymMember(firstName, lastName);
            case STAFF_MEMBER:
                return new StaffMember(firstName, lastName);
            case PERSONAL_TRAINER:
                return new PersonalTrainer(firstName, lastName);
        }
        return new GymMember(firstName, lastName);
    }




}

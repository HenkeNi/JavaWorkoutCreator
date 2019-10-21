package com.HenrikJangefelt;

import com.HenrikJangefelt.person.GymMember;
import java.util.Comparator;

public class SortFriend {


    static class SortFriendFirstName implements Comparator<GymMember> {

        @Override
        public int compare(GymMember o1, GymMember o2) {
            return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
        }
    }

    static class SortFriendLastName implements Comparator<GymMember> {

        @Override
        public int compare(GymMember o1, GymMember o2) {
            return o1.getLastName().compareToIgnoreCase(o2.getLastName());
        }
    }



}

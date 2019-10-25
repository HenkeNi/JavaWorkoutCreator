package com.HenrikJangefelt.sortby;

import com.HenrikJangefelt.models.person.GymMember;
import java.util.Comparator;

public class SortFriend {


    public static class SortFriendFirstName implements Comparator<GymMember> {

        @Override
        public int compare(GymMember o1, GymMember o2) {
            return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
        }
    }

    public static class SortFriendLastName implements Comparator<GymMember> {

        @Override
        public int compare(GymMember o1, GymMember o2) {
            return o1.getLastName().compareToIgnoreCase(o2.getLastName());
        }
    }



}

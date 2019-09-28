package com.HenrikJangefelt;

import java.util.Comparator;

public class SortFriend implements Comparator<GymMember> {

    @Override
    public int compare(GymMember o1, GymMember o2) {
        return o1.getFullName().compareTo(o2.getFullName());
    }
}

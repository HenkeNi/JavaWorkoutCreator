package com.HenrikJangefelt.sortby;

import com.HenrikJangefelt.models.person.Person;
import java.util.Comparator;

/**
 * <h1>SortFriend</h1>
 * The class SortFriend contains nested classes responsible for sorting an ArrayList of Person in various ways.
 */
public class SortFriend {

    /**
     * SortFriendFirstName sorts objects of Person type by their first name.
     */
    public static class SortFriendFirstName implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
        }
    }

    /**
     * SortFriendFirstName sorts objects of Person type by their last name.
     */
    public static class SortFriendLastName implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            return o1.getLastName().compareToIgnoreCase(o2.getLastName());
        }
    }



}

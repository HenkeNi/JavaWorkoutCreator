package com.HenrikJangefelt.models;

import com.HenrikJangefelt.models.person.StaffMember;
import java.util.ArrayList;

public class Gym {

    private ArrayList<StaffMember> staffMembers = new ArrayList<>();

    public ArrayList<StaffMember> getStaffMembers() {
        return staffMembers;
    }
}

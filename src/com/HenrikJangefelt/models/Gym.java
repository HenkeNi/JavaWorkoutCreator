package com.HenrikJangefelt.models;

import com.HenrikJangefelt.models.person.StaffMember;
import java.util.ArrayList;

public class Gym {

    private ArrayList<StaffMember> staffMembers = new ArrayList<>();

    public Gym() {
        StaffMember arnoldSchwarzenegger = new StaffMember("Arnold", "Schwarzenegger", 6, 15);
        StaffMember sylvesterStallone = new StaffMember("Sylvester", "Stallone", 15, 23);

        staffMembers.add(arnoldSchwarzenegger);
        staffMembers.add(sylvesterStallone);
    }

    public ArrayList<StaffMember> getStaffMembers() {
        return staffMembers;
    }
}

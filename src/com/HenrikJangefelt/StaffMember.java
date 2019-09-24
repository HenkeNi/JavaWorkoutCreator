package com.HenrikJangefelt;

public class StaffMember extends Person {

    int shiftStartHour;
    int shiftEndHour;

    public StaffMember(String firstName, String lastName, int shiftStartHour, int shiftEndHour) {
        super(firstName, lastName);
        this.shiftStartHour = shiftStartHour;
        this.shiftEndHour = shiftEndHour;

        //StaffMember arnoldSchwarzenegger = new StaffMember("Arnold", "Schwarzenegger", 6, 15);
        //StaffMember sylvesterStallone = new StaffMember("Sylvester", "Stallone", 15, 23);
    }


    public int getShiftStartHour() {
        return shiftStartHour;
    }

    public int getShiftEndHour() {
        return shiftEndHour;
    }


    // toString istället = (returnerar allt!?)
    public String getFullWorkShift() {
        return "starts: " + shiftStartHour + ", ends: " + shiftEndHour;
    }

}

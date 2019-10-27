//package com.HenrikJangefelt;
package com.HenrikJangefelt.models.person;

import com.HenrikJangefelt.Introduce;
import com.HenrikJangefelt.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StaffMember extends Person implements Introduce {

    View view = View.getInstance();

    private int shiftStartHour;
    private int shiftEndHour;
    private int staffNumber; // Eller nåt???

    public StaffMember(String firstName, String lastName) {
        super(firstName, lastName);
        //this.shiftStartHour = shiftStartHour;
        //this.shiftEndHour = shiftEndHour;

        //StaffMember arnoldSchwarzenegger = new StaffMember("Arnold", "Schwarzenegger", 6, 15);
        //StaffMember sylvesterStallone = new StaffMember("Sylvester", "Stallone", 15, 23);
    }


    public int getShiftStartHour() {
        return shiftStartHour;
    }

    public int getShiftEndHour() {
        return shiftEndHour;
    }

    public void setShiftStartHour(int shiftStartHour) {
        this.shiftStartHour = shiftStartHour;
    }

    public void setShiftEndHour(int shiftEndHour) {
        this.shiftEndHour = shiftEndHour;
    }

    // toString istället = (returnerar allt!?)


    public String getFullWorkShift() {
        return "starts: " + shiftStartHour + " ends: " + shiftEndHour;
    }

    @Override
    public void introduceYourself() {
        view.showMessage("My name is " + getFullName() + " and I'm a Personal Trainer");
    }


    // TEST, inner class
    private class workDay {

        int startHour;
        int dailyWorkHours;


        //Date[] workWeek = new Date[5];

        //DayOfWeek friday = new DayOfWeek();

        /*Date currentDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        System.out.println(simpleDateFormat.format(currentDate));*/

        /*if (simpleDateFormat == simpleDateFormat.format(.Fr)) {
            System.out.println();
        }*/

        /*Date date = new Date();
        date.getTime();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(dayOfWeek);

        switch (dayOfWeek) {
            case 1:
                System.out.println("Sunday");
                break;
            case 2:
                System.out.println("Monday");
                break;
            case 3:
                System.out.println("Tuesday");
                break;
            case 4:
                System.out.println("Wednesday");
                break;
            case 5:
                System.out.println("Thursday");
                break;
            case 6:
                System.out.println("Friday");
                break;
            case 7:
                System.out.println("Saturday");
                break;
        }*/
    }

}

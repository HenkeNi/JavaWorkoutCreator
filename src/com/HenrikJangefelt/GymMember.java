package com.HenrikJangefelt;

import java.util.ArrayList;

// TODO: renam???
// GymGoer, lifter, trainee,
public class GymMember extends Person {

    // TODO: Sätt till privata
    ArrayList<Workout> workoutList = new ArrayList<>();
    ArrayList<GymMember> gymBuddies = new ArrayList<>();

    public GymMember(String firstName, String lastName) {

        super(firstName, lastName);
    }


    public void removeWorkout(int indexPosition) {
        workoutList.remove(indexPosition);
    }

    public void addWorkout(String workoutName) {
        Workout newWorkout = new Workout(workoutName);
        workoutList.add(newWorkout);
    }

    /*public void showWorkouts() {
        for (int i = 0; i < workoutList.size(); i++) {
            System.out.printf("\t%s. %s\n", i + 1 , workoutList.get(i));

        }
    }*/

    public void showWorkouts() {

        System.out.println("Current Workouts:");

        // If no workouts in workoutList
        if (workoutList.isEmpty()) {
            System.out.println("\t-Empty");
            return;
        }

        for (int i = 0; i < workoutList.size(); i++) {
            System.out.printf("%s. %s\n", i + 1 , workoutList.get(i).getWorkoutName());
            workoutList.get(i).showExercises(i);

                /*for (int j = 0; j < workoutList.size(); j++) {
                    System.out.printf("\t%s. %s\n", j + 1 , workoutList.get(j));
                }*/

        }
    }

    public void removeGymBuddy(int indexPosition) {
        gymBuddies.remove(indexPosition);
    }

    public void addGymBuddy(String firstName, String lastName) {
        GymMember gymBuddy = new GymMember(firstName, lastName);
        gymBuddies.add(gymBuddy);
    }

    public void showGymBuddies() {
        for (int i = 0; i < gymBuddies.size(); i++) {
            System.out.printf("\t%s. %s\n", i + 1, gymBuddies.get(i).getFullName());
        }
    }

}

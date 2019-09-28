package com.HenrikJangefelt;

import java.util.ArrayList;

// TODO: renam???
// GymGoer, lifter, trainee,
public class GymMember extends Person {

    // TODO: ha bara logik i klasserna!!
    private ArrayList<Workout> workoutList = new ArrayList<>();
    private ArrayList<GymMember> friendList = new ArrayList<>();

    // TODO: lägg till email och lösen?? NEJ: måste ange det när man lägger till frineds då....
    public GymMember(String firstName, String lastName) {

        super(firstName, lastName);
    }


    public ArrayList<Workout> getWorkoutList() {
        return workoutList;
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

    // TODO: Fix
    // TODO returnera värde/workouts. ..
    /*public void searchWorkout(String searchedWorkout) {

        int matchingWorkouts = 0;
        int relatedWorkouts = 0;

        for (int i = 0; i < workoutList.size(); i++) {

            if (workoutList.get(i).getWorkoutName().equalsIgnoreCase(searchedWorkout)) {
                if (matchingWorkouts == 0) {
                    System.out.println("Workout(s) Found:");
                }
                System.out.println(workoutList.get(i).getWorkoutName());
                workoutList.get(i).showExercises(i + 1);
                matchingWorkouts++;

                // TODO: FIxa för många upperCase?
                //} else if (workout.contains(searchedWorkout) &&  !workout.equals(searchedWorkout)) {
            } else if (workoutList.get(i).getWorkoutName().toUpperCase().contains(searchedWorkout.toUpperCase()) && matchingWorkouts == 0) {

                // Only get related workouts in no exact match are found
                if (relatedWorkouts == 0) {
                    System.out.printf("Workout that contains '%s' found:\n", searchedWorkout);
                }
                System.out.println(workoutList.get(i).getWorkoutName());
                workoutList.get(i).showExercises(i + 1);
                relatedWorkouts++;
            }
        }

        // If no matching or related workout found
        if (matchingWorkouts == 0 && relatedWorkouts == 0) {
            System.out.println("No workouts found with that name");
        }
    }*/

    public ArrayList<Workout> getSearchedWorkout(String searchedWorkout) {

        ArrayList<Workout> matchingWorkouts = new ArrayList<>();

        for (int i = 0; i < workoutList.size(); i++) {

            if (workoutList.get(i).getWorkoutName().equalsIgnoreCase(searchedWorkout)) {

                matchingWorkouts.add(workoutList.get(i));

            }
        }
        return matchingWorkouts;
    }



    public ArrayList<Workout> getRelatedSearchedWorkout(String searchedWorkout) {

        ArrayList<Workout> relatedWorkouts = new ArrayList<>();

        for (int i = 0; i < workoutList.size(); i++) {

            if (workoutList.get(i).getWorkoutName().toUpperCase().contains(searchedWorkout.toUpperCase())) {
                relatedWorkouts.add(workoutList.get(i));
            }
        }

        return relatedWorkouts;

            //workoutList.get(i).showExercises(i + 1);
    }


    public ArrayList<GymMember> getFriendList() {
        return friendList;
    }

    public void addFriend(String firstName, String lastName) {
        friendList.add(new GymMember(firstName, lastName));
    }

    public void deleteFriend(int indexPosition) {
        friendList.remove(indexPosition);
    }

    public void showFriends() {
        for (int i = 0; i < friendList.size(); i++) {
            System.out.printf("\t%s. %s\n", i + 1, friendList.get(i).getFullName());
        }
    }



}

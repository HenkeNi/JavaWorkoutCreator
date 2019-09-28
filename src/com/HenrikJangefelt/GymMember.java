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

    /*
    public void removeWorkout(int indexPosition) {
        workoutList.remove(indexPosition);
    }*/


    public String deleteWorkout(int workoutIndex, int exerciseIndex) {

        if (exerciseIndex == 0) {
            Workout deletedWorkout = getWorkoutList().remove(workoutIndex - 1);
            return "Workout " + deletedWorkout.getWorkoutName() + " was deleted!";
        } else {
            // If exercise is the last in workout, then delete the whole workout
            if (getWorkoutList().get(workoutIndex - 1).exerciseList.size() <= 1) {
                Workout deletedWorkout = getWorkoutList().remove(workoutIndex - 1);
                return "Workout " + deletedWorkout.getWorkoutName() + " was deleted!";
            } else {
                getWorkoutList().get(workoutIndex - 1).removeExercise(exerciseIndex - 1); // Delete exercise
                return "Exercise " + workoutList.get(workoutIndex - 1).exerciseList.get(exerciseIndex - 1).getExerciseName() + " was deleted!";
            }
        }
    }


    public void addWorkout(String workoutName) {
        Workout newWorkout = new Workout(workoutName);
        workoutList.add(newWorkout);
    }



    public void showWorkouts(ArrayList<Workout> workouts) {

        for (int i = 0; i < workouts.size(); i++) {
            System.out.printf("%s. %s\n", i + 1, workouts.get(i).getWorkoutName());
            workouts.get(i).showExercises(i);
        }
    }

    // Returns workouts that match searched workout
    public ArrayList<Workout> getSearchedWorkout(String searchedWorkout) {

        ArrayList<Workout> matchingWorkouts = new ArrayList<>();
        for (int i = 0; i < workoutList.size(); i++) {

            if (workoutList.get(i).getWorkoutName().equalsIgnoreCase(searchedWorkout)) {
                matchingWorkouts.add(workoutList.get(i));
            }
        }
        return matchingWorkouts;
    }

    // Returns workouts that contains the seatched workout
    public ArrayList<Workout> getRelatedSearchedWorkout(String searchedWorkout) {

        ArrayList<Workout> relatedWorkouts = new ArrayList<>();
        for (int i = 0; i < workoutList.size(); i++) {

            if (workoutList.get(i).getWorkoutName().toUpperCase().contains(searchedWorkout.toUpperCase())) {
                relatedWorkouts.add(workoutList.get(i));
            }
        }
        return relatedWorkouts;
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

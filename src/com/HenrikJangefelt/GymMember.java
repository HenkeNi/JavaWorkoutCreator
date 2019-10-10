package com.HenrikJangefelt;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Renam???
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
            if (getWorkoutList().get(workoutIndex - 1).getExerciseList().size() <= 1) {
                Workout deletedWorkout = getWorkoutList().remove(workoutIndex - 1);
                return "Workout " + deletedWorkout.getWorkoutName() + " was deleted!";
            } else {
                getWorkoutList().get(workoutIndex - 1).removeExercise(exerciseIndex - 1); // Delete exercise
                return "Exercise " + workoutList.get(workoutIndex - 1).getExerciseList().get(exerciseIndex - 1).getExerciseName() + " was deleted!";
            }
        }
    }


    /*public void addWorkout(String workoutName) {
        Workout newWorkout = new Workout(workoutName);
        workoutList.add(newWorkout);
    }*/



    // TODO: Creata a flexible method for showing objects (workouts / friends)
    // public <T extends ArrayList<T>> void show(ArrayList<T> list) {
    public <T extends Object> void show(ArrayList<T> list) {

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
            if (list.equals(workoutList)) {
                System.out.println("Wokrout list");
                workoutList.get(i).showExercises(i);
            }

            //if (list == Workout)
        }
    }



    public void showWorkouts(ArrayList<Workout> workouts) {

        for (int i = 0; i < workouts.size(); i++) {
            System.out.printf("%s. %s\n", i + 1, workouts.get(i).toString());
            workouts.get(i).showExercises(i);
        }
    }

    public void showFriends(ArrayList<GymMember> friends) {
        for (int i = 0; i < friends.size(); i++) {
            System.out.printf("\t%s. %s\n", i + 1, friends.get(i).getFullName());
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




    // TODO: Gör så att den bara kan ta in generic objekts
    public <T extends Object> ArrayList<T> getSearchedObject(ArrayList<T> objectList, String searchWord) {

        ArrayList<T> matches = new ArrayList<>();

        for (int i = 0; i < objectList.size(); i++) {

            if (objectList == workoutList && workoutList.get(i).getWorkoutName().equalsIgnoreCase(searchWord)) {
                matches.add((T) workoutList.get(i));
            }

            if (objectList == friendList && friendList.get(i).getFullName().equalsIgnoreCase(searchWord)) {
                matches.add((T) friendList.get(i));
            }
        }
        return matches;
    }

    public <T extends Object> ArrayList<T> getRelatedSearchedObject(ArrayList<T> objectList, String searchWord) {

        ArrayList<T> relatedMatches = new ArrayList<>();

        for (int i = 0; i < objectList.size(); i++) {

            if (objectList == workoutList && workoutList.get(i).getWorkoutName().toUpperCase().contains(searchWord.toUpperCase())) {
                relatedMatches.add((T) workoutList.get(i));
            }

            if (objectList == friendList && friendList.get(i).getFullName().toUpperCase().contains(searchWord.toUpperCase())) {
                relatedMatches.add((T) friendList.get(i));
            }
        }
        return relatedMatches;
    }





    // TODO: Combine getSearcchedFriend/Workout && getRelated.../Friend/Workout && showWorkout//Friend

    public ArrayList<GymMember> getSearchedFriend(String searchedFriend) {

        ArrayList<GymMember> matchingFriends = new ArrayList<>();
        for (int i = 0; i < friendList.size(); i++) {

            if (friendList.get(i).getFullName().equalsIgnoreCase(searchedFriend)) {
                matchingFriends.add(friendList.get(i));
            }
        }
        return matchingFriends;
    }

    // Returns workouts that contains the seatched workout
    public ArrayList<GymMember> getRelatedSearchedFriend(String searchedFriend) {

        ArrayList<GymMember> relatedFriends = new ArrayList<>();
        for (int i = 0; i < friendList.size(); i++) {

            if (friendList.get(i).getFullName().toUpperCase().contains(searchedFriend.toUpperCase())) {
                relatedFriends.add(friendList.get(i));
            }
        }
        return relatedFriends;
    }



}

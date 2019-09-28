package com.HenrikJangefelt;

import java.util.*;

// TODO: dum idé att alla metoder tar in GymMember. Ja: onödigt? .Nej: Programmet funkar för att lägga till workouts till andra???

// TODO: egen class för StaffSchedule

// TODO: FIxa try and catch for inputs!!
// TODO: ta in userInout som String och omvandla sedan (Fixar bugg med nextLine inte läses in?) TODO: FIx isNumber

// TODO: Försök lägga till mer enums?,

// TOOD: sätt så mycket som möjligt till private?

// TODO: Settings, chang your username, ändra/se sitt password/email. Rensa alla arrays

// TODO: ange veckodagar för staff

// TODO: kolla om det finns mer att lägga i de olika klasserna (workouts etc.)


// TODO: (TAnkar) menyalternativ som en array av enums eller liknande ???

// TODO: sätt 0 som return i alla switch menyer?

// TODO: Bygg efter singelton (kolla upp!!)

// TODO: Final check- kolla att man bara kan ange siffror, samt bara rätt intervall för tex arrayer...

public class TrainingProgram {

    Scanner input = new Scanner(System.in);

    static GymMember currentUser = new GymMember("", ""); // TEST (Static) TODO: (ta bort från konstruktorn i Person???)

    public TrainingProgram() {
        showMainMenu(currentUser);
    }

    // TODO: lägg till funktion för att köra passet?
    public void showMainMenu(GymMember gymMember) {

        int menuSelection = 0;
        String userInput = "";


        do {
            System.out.println("Main Menu:\n1. Workouts\n2. Friends\n3. Check Available Staff\n4. Help(TODO)\n0. Exit");
            //userInput = input.nextLine();

            do {
                menuSelection = isNumber(input.nextLine());
            } while (menuSelection == 0);
            //menuSelection = isNumber(userInput);
            //int numberInt = checkIfValidNumber(userInput);

            switch (menuSelection) {
                case 0:
                    return;
                case 1:
                    showWorkoutMenu(gymMember);
                    break;
                case 2:
                    showFriendsMenu();
                    break;
                case 3:
                    checkAvailableStaff();
                    break;
                case 4:
                    help();
                    break;
            }
        } while (menuSelection != 0);
    }



    private void showWorkoutMenu(GymMember gymMember) {

        do {
            System.out.println("Workout menu:\n1. Add Workout\n2. Edit Workout\n3. Show Workouts \n4. Search Workout\n5. Sort Workout \n0. Go Back");
            int menuSelection = isNumber(input.nextLine());

            switch (menuSelection) {
                case 0:
                    return;
                case 1:
                    createWorkout(gymMember);
                    break;
                case 2:
                    showEditWorkoutMenu(gymMember);
                    break;
                case 3:
                    gymMember.showWorkouts();
                    break;
                case 4:
                    searchWorkout(gymMember);
                    break;
                case 5:
                    sortMenu(gymMember);
                    break;
            }
        } while (true);
    }


    public void createWorkout(GymMember gymMember) {

        System.out.println("Enter workout's name:");
        //input.nextLine(); // TODO: Kan ta bort?
        gymMember.addWorkout(input.nextLine());

        addExercise(currentUser,gymMember.getWorkoutList().size() - 1); // Add new exercise to the last workout in the workoutList

    }


    public void addExercise(GymMember gymMember, int workoutIndex) {

        do {
            System.out.println("Exercise name:");
            String exerciseName = input.nextLine();

            System.out.println("Number of reps:");
            int numberOfReps = isNumber(input.nextLine());

            System.out.println("Number of sets:");
            int numberOfSets = isNumber(input.nextLine());

            gymMember.getWorkoutList().get(workoutIndex).addExercise(exerciseName, numberOfReps, numberOfSets, selectMuscleGroup());


            System.out.printf("Workout: \'%s\' currently consist of %s exercises\n", gymMember.getWorkoutList().get(workoutIndex).getWorkoutName(), gymMember.getWorkoutList().get(workoutIndex).exerciseList.size());
            System.out.println("1. Add another exercise\n0. Go Back");

            int menuSelection = isNumber(input.nextLine());

            switch (menuSelection) {
                case 0:
                    return;
            }
        } while (true);
    }


    // TODO: Bara kunna ange tal mellan 1-7
    // Choose worked muscle group for exercise
    public Exercise.Muscle selectMuscleGroup() {
        System.out.println("Choose targeted muscle for exercise:\n" +
                "1. Chest\n" +
                "2. Back\n" +
                "3. Shoulders\n" +
                "4. Biceps\n" +
                "5. Triceps\n" +
                "6. Abs\n" +
                "7. Legs");

        int muscleSelection = isNumber(input.nextLine());

        switch (muscleSelection) {
            case 1:
                return Exercise.Muscle.CHEST;
            case 2:
                return Exercise.Muscle.BACK;
            case 3:
                return Exercise.Muscle.SHOULDERS;
            case 4:
                return Exercise.Muscle.BICEPS;
            case 5:
                return Exercise.Muscle.TRICEPS;
            case 6:
                return Exercise.Muscle.ABS;
            case 7:
                return Exercise.Muscle.LEGS;
        }
        return Exercise.Muscle.UNKOWN;
    }


    public void showEditWorkoutMenu(GymMember gymMember) {

        int menuSelection = 0;
        String userInput = "";

        do {
            gymMember.showWorkouts();

            if (gymMember.getWorkoutList().size() != 0) {
                System.out.println("\nOptions:\n1. Add exercise\n2. Edit\n3. Delete\n0. Go Back");
                userInput = input.nextLine();

                menuSelection = isNumber(userInput);

                switch (menuSelection) {
                    case 0:
                        return;
                    case 1:
                        System.out.println("Enter the number of the workout you wish to add exercise to");
                        addExercise(gymMember,isNumber(input.nextLine()) - 1);
                        break;
                    case 2:
                        editWorkout(); // Change name of workout/exercise, amount of reps, etc.
                        break;
                    case 3:
                        deleteWorkout(currentUser); // Delete workout or exercise
                        break;
                }
            }
        } while (!gymMember.getWorkoutList().isEmpty());
    }


    public void updateWorkout(GymMember gymMember, int workoutNumber) {

        int menuSelection = 0;
        String userInput = ""; // TODO: behövs??? Kan ta bort på alla? och lägga direkt i isNumber(input.Netxtline)

        do {
            System.out.println("" +
                    "Workout options:\n" +
                    "1. Change workout name\n" +
                    "0. Go Back");

            userInput = input.nextLine();
            menuSelection = isNumber(userInput);

            switch (menuSelection) {
                case 0:
                    return;
                case 1:
                    // TODO: GÖr till funktion (eller lägg addNEwExercise i switch case med)
                    System.out.println("Enter new workout name:");
                    input.nextLine();
                    String newWorkoutName = input.nextLine();
                    gymMember.getWorkoutList().get(workoutNumber - 1).setWorkoutName(newWorkoutName); // Change workout name
                    break;
            }
            System.out.println("Workout successfully updated!");
        } while (true);
    }


    public void updateExercise(GymMember gymMember, int workoutNumber, int exerciseNumber) {

        int menuSelection = 0;
        String userInput = "";

        do {
            System.out.println("" +
                    "Exercise options:\n" +
                    "1. Change exercise name\n" +
                    "2. Change amount of reps\n" +
                    "3. Change amount of sets\n" +
                    "4. Change targeted muscle\n" +
                    "0. Go Back\n");


            userInput = input.nextLine();
            menuSelection = isNumber(userInput);

            switch (menuSelection) {
                case 0:
                    return;
                case 1:
                    System.out.println("Enter new exercise name:");
                    input.nextLine(); // TODO: ta bort senare
                    gymMember.getWorkoutList().get(workoutNumber - 1).exerciseList.get(exerciseNumber - 1).setExerciseName(input.nextLine()); // Change exercise name
                    break;
                case 2:
                    System.out.println("Enter new amount of reps:");
                    gymMember.getWorkoutList().get(workoutNumber - 1).exerciseList.get(exerciseNumber - 1).setNumberOfReps(isNumber(input.nextLine())); // Change amount of reps
                    break;
                case 3:
                    System.out.println("Enter new amount of sets:");
                    gymMember.getWorkoutList().get(workoutNumber - 1).exerciseList.get(exerciseNumber - 1).setNumberOfSets(isNumber(input.nextLine())); // Change amount of sets
                    break;
                case 4:
                    gymMember.getWorkoutList().get(workoutNumber - 1).exerciseList.get(exerciseNumber - 1).setTargetedMuscle(selectMuscleGroup()); // Change targeted muscle group
                    break;
            }
            System.out.println("Exercise successfully updated!");
        } while (true);
    }


    // TODO: förbättra (lägg i workout)
    // TODO: Combine with returnWorkoutPrefix??
    public void editWorkout() {

        int[] indexArray = returnWorkoutPrefix("change");
        int workoutNumber = indexArray[0];
        int exerciseNumber = indexArray[1];

        if (exerciseNumber == 0) {
            updateWorkout(currentUser, workoutNumber);
        } else {
            updateExercise(currentUser, workoutNumber, exerciseNumber);
        }
    }


    public int[] returnWorkoutPrefix(String editMode) {

        boolean validNumbers = false;
        int workoutIndex = 0;
        int exerciseIndex = 0;

        do {
            System.out.println("Enter the prefix-number of the workout or exercise you want to " + editMode);
            String userInput = input.nextLine().replace(".", "").trim();
            try {
                //workoutIndex = Character.getNumericValue(userInput.charAt(0));
                workoutIndex = Integer.parseInt(userInput.substring(0, 1));
                if (userInput.length() >= 2) {
                    exerciseIndex = Integer.parseInt(userInput.substring(1));
                }
                validNumbers = true;
            } catch (Exception e) {
                System.out.println("Must enter a valid number prefix.");
            }
        } while (!validNumbers);

        int[] prefixArray = {workoutIndex, exerciseIndex };
        return prefixArray;
    }

    // OLD VERSION
   /* public int[] returnWorkoutOrExercise(String mode) {
        System.out.printf("Enter the prefix-number of the workout or exercise you want to %s:\n", mode);
        input.nextLine();
        String selectedWorkout = input.nextLine();
        int[] indexArray = new int[2];

        // User entered an exercise in a workout
        if (selectedWorkout.contains(".") && selectedWorkout.length() >= 3 && selectedWorkout.charAt(1) == '.') {

            String[] numbers = selectedWorkout.split("\\."); // Split String at "."
            int workoutIndex = Integer.parseInt(numbers[0]); // Store first half of the String in workoutIndex as a number
            int exerciseIndex = Integer.parseInt(numbers[1].replace(".", "")); // Store the second half as a number and remove the dot

            indexArray[0] = workoutIndex;
            indexArray[1] = exerciseIndex;
        } else {
            char number = selectedWorkout.charAt(0);
            int workoutIndex = Character.getNumericValue(number);
            indexArray[0] = workoutIndex;
        }
        return indexArray;
    }*/



    // Delete workouts or exercises
    public void deleteWorkout(GymMember gymMember) {

        int[] indexArray = returnWorkoutPrefix("delete");
        int workoutNumber = indexArray[0];
        int exerciseNumber = indexArray[1];

        if (exerciseNumber == 0) {
            System.out.printf("Workout '%s' was deleted!\n", gymMember.getWorkoutList().get(workoutNumber - 1).getWorkoutName());
            gymMember.getWorkoutList().remove(workoutNumber - 1); // Remove workout

        } else {
            // If exercise is the last in workout, then delete the whole workout
            if (gymMember.getWorkoutList().get(workoutNumber - 1).exerciseList.size() <= 1) {
                System.out.printf("Workout '%s' was deleted!\n", gymMember.getWorkoutList().get(workoutNumber - 1).getWorkoutName());
                gymMember.getWorkoutList().remove(workoutNumber - 1);

            } else {
                System.out.printf("Exercise '%s' was deleted!\n", gymMember.getWorkoutList().get(workoutNumber - 1).exerciseList.get(exerciseNumber -1).getExerciseName());
                gymMember.getWorkoutList().get(workoutNumber - 1).removeExercise(exerciseNumber - 1); // Delete exercise
            }
        }
        return;
    }


    // TODO: använd den i GymMember klass istället
    public void searchWorkout(GymMember gymMember) {

        System.out.println("Enter name of the workout you are looking for:");
        input.nextLine();
        String searchedWorkout = input.nextLine();

        ArrayList<Workout> matchingWorkouts = gymMember.getSearchedWorkout(searchedWorkout);
        ArrayList<Workout> relatedWorkouts = gymMember.getRelatedSearchedWorkout(searchedWorkout);

        // TODO: bygg ihop funktion för att printa ut wokruts (kanske samma som i klass gmMEmber)
        if (!matchingWorkouts.isEmpty()) {
            System.out.println("Workout(s) Found:");
            for (int i = 0; i < matchingWorkouts.size(); i++) {
                System.out.println(matchingWorkouts.get(i).getWorkoutName());
                matchingWorkouts.get(i).showExercises(i + 1);
            }
        } else if (!relatedWorkouts.isEmpty()) {
            System.out.printf("Workout that contains '%s' found:\n", searchedWorkout);
            for (int i = 0; i < relatedWorkouts.size(); i++) {
                System.out.println(relatedWorkouts.get(i).getWorkoutName());
                relatedWorkouts.get(i).showExercises(i + 1);
            }
        } else {
            System.out.printf("No workouts matching %s found\n", searchedWorkout);
        }
    }











    public void sortMenu(GymMember gymMember) {

        do {
            System.out.println("Sort:\n1. Workouts\n2. Exercises\n0. Go Back");
            int sortSelection = isNumber(input.nextLine());

            switch (sortSelection) {
                case 0:
                    return;
                case 1:
                    sortWorkout(gymMember);
                    break;
                case 2:
                    gymMember.showWorkouts();
                    System.out.println("Enter workout prefix for sorting exercises");
                    sortExercises(gymMember, isNumber(input.nextLine()));
                    gymMember.showWorkouts();
                    break;
            }
        } while (true);
    }

    // TODO: enum för olika sortering??
    // TODO: lägg sort i edit??
    public void sortWorkout(GymMember gymMember) {

        System.out.println("Sort by:\n1. Name\n0. Exit");
        int menuSelection = isNumber(input.nextLine());

        switch (menuSelection) {

            case 0:
                return;
            case 1:
                SortWorkoutName sortWorkoutName = new SortWorkoutName();
                Collections.sort(gymMember.getWorkoutList(), sortWorkoutName);
                gymMember.showWorkouts();
                break;
        }
    }

    public void sortExercises(GymMember gymMember, int workoutIndex) {
        System.out.println("Sort by:\n1. Name\n2. Reps\n3. Sets\n4. Muscle\n0. Go Back");
        int menuSelection = isNumber(input.nextLine());

        switch (menuSelection) {
            case 0:
                return;
            case 1:
                SortExerciseName sortExerciseName = new SortExerciseName();
                Collections.sort(gymMember.getWorkoutList().get(workoutIndex - 1).exerciseList, sortExerciseName);
                break;
            case 2:
                SortExerciseReps sortExerciseReps = new SortExerciseReps();
                Collections.sort(gymMember.getWorkoutList().get(workoutIndex - 1).exerciseList, sortExerciseReps);
                break;
            case 3:
                SortExerciseSets sortExerciseSets = new SortExerciseSets();
                Collections.sort(gymMember.getWorkoutList().get(workoutIndex - 1).exerciseList, sortExerciseSets);
                break;
            case 4:
                SortExercisesMuscle sortExercisesMuscle = new SortExercisesMuscle();
                Collections.sort(gymMember.getWorkoutList().get(workoutIndex - 1).exerciseList, sortExercisesMuscle);
                break;
        }

    }



    // TODO: egen klass?
    // TODO: loop
    public void help() {
        System.out.println("What do you need help with?");
        System.out.println("1. How to create a workout\n2. How to edit a workout\n3. How to search for a workout\n0. Go Back\n");

        int menuSelector = isNumber(input.nextLine());

        switch (menuSelector) {
            case 0:
                return;
            case 1:
                helpCreateWorkout();
                break;
            case 2:
                helpEditWorkout();
                break;
            case 3:
                System.out.println("How to search");
                break;
        }
    }


    public void helpCreateWorkout() {
        System.out.println("Step 1: Give your workout a name (ex: 'Chest day' or 'Monday Workout'.\n" +
                "\t It's okey to name to workouts the same.\n\n" +
                "Step 2: Enter the name of the exercise you want to add (all workouts need at least one exercise).\n" +
                "\t It's also okey for exercises to have the same name.\n\n" +
                "Step 3: Specify the amount of reps of the given exercise.\n" +
                "\tReps or repitions are the number of times you perform an exercise (ex: doing 15 pushup, 15 will be your amount of reps)).\n\n" +
                "Step 4: Specify the amount of sets of the given exercise.\n" +
                "\tSets are the number of times you're gonna repeat an exercise (if you do 15 pushups then rest and then do 15 more, you have done two sets).\n");
    }

    public void helpEditWorkout() {
        System.out.println("Step 1: Choose 'Edit Workouts' in the main menu.\n\n" +
                "For adding a new exercise to a already created workout, .....  ");


        // To edit an exercise enter the number infront (1.2)
    }



























    // TODO: Show if possible friends before
    public void showFriendsMenu() {

        do {
            System.out.println("1. Add Gym Buddy\n2. Edit Gym Buddy\n3. Show Friends\n4. Search\n5. Sort\n0. Go Back");
            int menuSelection = isNumber(input.nextLine());

            switch (menuSelection) {
                case 0:
                    return;
                case 1:
                    addGymBuddy();
                    break;
                case 2:
                    editGymBuddy();
                    break;
                case 3:
                    showGymBuddies();
                case 4:
                    searchGymBuddy();
                    break;
                case 5:
                    sortGymBuddies(currentUser);
                    break;
            }
        } while (true);
    }






    public void sortGymBuddies(GymMember gymMember) {

        SortFriend sortFriend = new SortFriend();
        Collections.sort(gymMember.getFriendList(), sortFriend);
        gymMember.showFriends();
    }


    // TODO: if no staff. skriv ingen oersonal. Samt tid när deet är bamanat? alt. när nästashift börjar?
    public void checkAvailableStaff() {

        Gym currentGym = new Gym();

        // TODO: add workdays (5 days of the week)
        //Date todaysDate = new Date();
        //todaysDate.getTime();

        //System.out.println(java.time.LocalTime.now().getHour()); // GETS Current time

        int currentTime = java.time.LocalTime.now().getHour();

        System.out.println("Available Staff at your local gym:");

        for (int i = 0; i < currentGym.staffMembers.size(); i++) {

            if (currentTime > currentGym.staffMembers.get(i).getShiftStartHour() && currentTime < currentGym.staffMembers.get(i).getShiftEndHour()) {
                System.out.printf("\t-%s %s\n\n", currentGym.staffMembers.get(i).getFullName(), currentGym.staffMembers.get(i).getFullWorkShift()); // TODO: print staff
            }
        }
    }


























    public void showObjects(ArrayList<Object> objectList, String objectName) {

        System.out.printf("Current %s\n", objectName);

        if (objectList.isEmpty()) {
            System.out.println("\t-Empty");
            return;
        }

        // TODO SKriva ut objekten
        for (int i = 0; i < objectList.size(); i++) {
            //System.out.println("\n%s. %s\n", i + 1, objectList.get(i));
        }


    }




    // TODO: Ändra curretnUsers till gymMembers?? (nej: bara kunna en själv ska kunna söka. ja: programmet bör vara utformat för alla..)
    // TODO: COmbine with search workout?
    public void searchGymBuddy() {

        int numberOfMatchingNames = 0;
        int numberOfRelatedNames = 0;

        System.out.println("Enter name of the friend you are looking for:");
        input.nextLine();
        String searchedFriend = input.nextLine();


        for (int i = 0; i < currentUser.getFriendList().size(); i++) {

            if (currentUser.getFriendList().get(i).getFullName().equalsIgnoreCase(searchedFriend)) {
                if (numberOfMatchingNames == 0) {
                    System.out.println("Friend(s) Found:");
                }
                System.out.println(currentUser.getFriendList().get(i).getFullName());
                numberOfMatchingNames++;

                // TODO: FIxa för många upperCase?
                //} else if (workout.contains(searchedWorkout) &&  !workout.equals(searchedWorkout)) {
            } else if (currentUser.getFriendList().get(i).getFullName().toUpperCase().contains(searchedFriend.toUpperCase()) && numberOfMatchingNames == 0) {

                // Only get related workouts in no exact match are found
                if (numberOfRelatedNames == 0) {
                    System.out.printf("Friend that contains '%s' found:\n", searchedFriend);
                }
                System.out.println(currentUser.getFriendList().get(i).getFullName());
                numberOfRelatedNames++;
            }
        }

        // If no matching or related workout found
        if (numberOfMatchingNames == 0 && numberOfRelatedNames == 0) {
            System.out.println("No Friends found with that name");
        }
    }




    public void addGymBuddy() {

        System.out.println("Here you can add friends to your friendslist!");

        do {
            System.out.println("Enter your friends first name");
            String firstName = input.nextLine().trim();

            System.out.println("Enter friends last name");
            String lastName = input.nextLine().trim();

            currentUser.addFriend(firstName, lastName);
            System.out.println(currentUser.getFriendList().get(currentUser.getFriendList().size() - 1).getFullName() + " was added to your friendlist");

            System.out.println("Do you want to add another friend?\n1. Add friend\n2. Go Back");
            int menuSelection = isNumber(input.nextLine());

            switch (menuSelection) {

                case 2:
                    return;
            }
        } while (true);
    }




    public void showGymBuddies() {

        System.out.println("Friends in friend list:");

        // If no friends in gymBuddies
        if (currentUser.getFriendList().isEmpty()) {
            System.out.println("\t-Empty");
            return;
        }


        currentUser.showFriends();
        /*for (int i = 0; i < accountHolder.gymBuddies.size(); i++) {
            System.out.printf("\n%s. %s\n", i + 1, accountHolder.gymBuddies.get(i).getWorkoutName());

            accountHolder.workoutList.get(i).showExercises(i + 1);
        }*/

    }

    public void editGymBuddy() {

        // Show all gym buddies
        showGymBuddies();

        if (currentUser.getFriendList().size() == 0) {
            return;
        }

        // Choose buddy
        System.out.println("Enter the number of the friend you wish to change:");
        int friendIndex = isNumber(input.nextLine());

        do {
            System.out.println("Options:\n1. Edit First Name\n2. Edit Last Name\n3. Remove Friend\n0. Go Back");
            int menuSelection = isNumber(input.nextLine());

            switch (menuSelection) {
                case 0:
                    return;
                case 1:
                    System.out.println("Enter new first name:");
                    currentUser.getFriendList().get(friendIndex - 1).setFirstName(input.nextLine().trim());
                    break;
                case 2:
                    System.out.println("Enter new last name");
                    currentUser.getFriendList().get(friendIndex - 1).setLastName(input.nextLine().trim());
                    break;
                case 3:
                    currentUser.deleteFriend(friendIndex - 1);
                    return;
            }
        } while (true);
    }











    public Number checkIfValidNumber(String str) {

        do {
            try {
                int number = Integer.parseInt(str);
                System.out.println("Is a int number " + number);
            } catch (Exception e) {
                System.out.println("Is not an int number");
                try {
                    double number = Double.parseDouble(str);
                    System.out.println("is a double");
                } catch (Exception d) {
                    System.out.println("neither double or int");
                }
            }
        } while (true);
    }


    // TODO: add message (Enter menu selectiom: or Enter amount of Sets:)
    public int checkIfInteger() {

        int numb = 0;

        do {
            String userInput = input.nextLine();
            try {
                numb = Integer.parseInt(userInput);
            } catch (Exception e) {
                System.out.println("Must enter a number:");
            }
        } while (numb == 0);
        return numb;
    }


    // TODO: rename convert to number? / inputToNumber
    // TODO: lägg do while när funktionen kallas på isätället?
    public int isNumber(String str) {

        //do {
            try {
                return Integer.parseInt(str);
            } catch (Exception e) {
                System.out.println("Must enter a number:");
                return 0;
            }
        //} while (true);
    }

    // FUNkar inte overloada med anna return typ
    /*public double isNumber(String str) {
        do {
            try {
                return Double.parseDouble(str);
            } catch (Exception e) {
                System.out.println("Must enter a decimal number:");
                return 0;
            }
        } while (true);
    }*/


  /*public <T extends Number> T checkInput() {

        String userInput = input.nextLine();
        T number;

        try {
            number = Integer.parseInt(userInput);
        }
    }*/

/*
    // TODO: BYGG OM SÅ ATT DEN RETURNERAR DOUBLE ELLER INT

    // TODO: Fixa med overloading metod istället
    // Generic function
    public <T extends NumberFormat<T>> T isNumber(T a, T b, T c) {

    }


    public <T> T isIntOrDouble(String str) {
        do {
            try {
                return Integer.parseInt(str);
            } catch (Exception e) {

            }
        } while (true);
    } */


















}


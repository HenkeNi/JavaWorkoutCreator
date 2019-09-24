package com.HenrikJangefelt;

import java.util.*;

// TODO: egen class för StaffSchedule

// TODO: FIxa try and catch for inputs!!
// TODO: ta in userInout som String och omvandla sedan (Fixar bugg med nextLine inte läses in?) TODO: FIx isNumber

// TODO: kolla att enum Muscle fungerar
// TODO: use enums more!!!

// TODO: add functionallity for changing your username, hämta/se sitt password/email

// TODO: ange veckodagar för staff

// TODO: Are you sure you want to delete friend/workout/exercise - check first?
// TODO: clean - rensa alla

// TODO: (TAnkar) menyalternativ som en array av enums eller liknande ???

// TODO: Bygg efter singelton (kolla upp!!)

// TODO: sort

public class TrainingProgram {

    Scanner input = new Scanner(System.in);

    //GymMember accountHolder = new GymMember("MrOrMrs.", "Somebody"); // TODO: Sätt default värde i Person istället?

    static GymMember currentUser = new GymMember("", ""); // TEST (Static) TODO: (ta bort från konstruktorn i Person???)

    public TrainingProgram() {
        test();
        showMainMenu();
    }


    public void showMainMenu() {

        int menuSelection = 0;
        String userInput = "";


        do {
            System.out.println("Main Menu:\n1. Workouts\n2. Friends\n3. Check Available Staff\n4. Help(TODO)\n5. Exit");
            userInput = input.nextLine();

            menuSelection = isNumber(userInput);
            //int numberInt = checkIfValidNumber(userInput);

            switch (menuSelection) {
                case 1:
                    showWorkoutMenu();
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
                case 5:
                    return;
            }
        } while (menuSelection != 5);
    }



    private void showWorkoutMenu() {

        do {
            System.out.println("Workout menu:\n1. Add Workout\n2. Edit Workout\n3. Show Workouts \n4. Search Workout\n5. Sort Workout \n6. Go Back");
            int menuSelection = isNumber(input.nextLine());

            switch (menuSelection) {
                case 1:
                    createWorkout();
                    break;
                case 2:
                    showEditWorkoutMenu();
                    break;
                case 3:
                    currentUser.showWorkouts();
                    break;
                case 4:
                    searchWorkout();
                    break;
                case 5:
                    sortWorkout();
                    break;
                case 6:
                    return;
            }
        } while (true);
    }


    // Creates a new workout
    public void createWorkout() {
        System.out.println("Enter workout's name:");
        input.nextLine(); // TODO: Kan ta bort?
        currentUser.addWorkout(input.nextLine());
        addExercise(currentUser.workoutList.size() - 1); // Add new exercise to the last workout in the workoutList
    }


    // Add exercises to workouts
    public void addExercise(int workoutIndex) {

        do {
            System.out.println("Exercise name:");
            String exerciseName = input.nextLine();

            System.out.println("Number of reps:");
            int numberOfReps = isNumber(input.nextLine());

            System.out.println("Number of sets:");
            int numberOfSets = isNumber(input.nextLine());

            currentUser.workoutList.get(workoutIndex).addExercise(exerciseName, numberOfReps, numberOfSets, selectMuscleGroup());

            System.out.printf("Workout: \'%s\' currently consist of %s exercises\n", currentUser.workoutList.get(workoutIndex).getWorkoutName(), currentUser.workoutList.get(workoutIndex).exerciseList.size());
            System.out.println("1. Add another exercise\n2. Go Back");

            int menuSelection = isNumber(input.nextLine());

            switch (menuSelection) {
                case 2:
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


    public void showEditWorkoutMenu() {

        int menuSelection = 0;
        String userInput = "";

        do {
            currentUser.showWorkouts();

            if (currentUser.workoutList.size() != 0) {
                System.out.println("\nOptions:\n1. Add exercise\n2. Edit\n3. Delete\n4. Go Back");
                userInput = input.nextLine();

                menuSelection = isNumber(userInput);

                switch (menuSelection) {
                    case 1:
                        System.out.println("Enter the number of the workout you wish to add exercise to");
                        addExercise(isNumber(input.nextLine()) - 1);
                        break;
                    case 2:
                        editWorkout(); // Change name of workout/exercise, amount of reps, etc.
                        break;
                    case 3:
                        deleteWorkout(); // Delete workout or exercise
                        break;
                    case 4:
                        return; // Go Back
                }
            }
        } while (!currentUser.workoutList.isEmpty());
    }









    // TODO: förbättra
    public void updateWorkout(int workoutNumber) {

        int menuSelection = 0;
        String userInput = ""; // TODO: behövs??? Kan ta bort på alla? och lägga direkt i isNumber(input.Netxtline)

        do {
            System.out.println("" +
                    "Workout options:\n" +
                    "1. Change workout name\n" +
                    "2. Go Back");

            userInput = input.nextLine();
            menuSelection = isNumber(userInput);


            switch (menuSelection) {
                case 1:
                    // TODO: GÖr till funktion (eller lägg addNEwExercise i switch case med)
                    System.out.println("Enter new workout name:");
                    input.nextLine();
                    String newWorkoutName = input.nextLine();
                    currentUser.workoutList.get(workoutNumber - 1).setWorkoutName(newWorkoutName); // Change workout name
                    break;
                case 2:
                    return;
            }
        } while (true);
    }




    // TODO: förbättra
    public void updateExercise(int workoutNumber, int exerciseNumber) {

        int menuSelection = 0;
        String userInput = "";

        do {
            System.out.println("" +
                    "Exercise options:\n" +
                    "1. Change exercise name\n" +
                    "2. Change amount of reps\n" +
                    "3. Change amount of sets\n" +
                    "4. Change targeted muscle\n" +
                    "5. Go Back\n");


            userInput = input.nextLine();
            menuSelection = isNumber(userInput);

            switch (menuSelection) {
                case 1:
                    System.out.println("Enter new exercise name:");
                    input.nextLine(); // TODO: ta bort senare
                    currentUser.workoutList.get(workoutNumber - 1).exerciseList.get(exerciseNumber - 1).setExerciseName(input.nextLine()); // Change exercise name
                    break;
                case 2:
                    System.out.println("Enter new amount of reps:");
                    currentUser.workoutList.get(workoutNumber - 1).exerciseList.get(exerciseNumber - 1).setNumberOfReps(isNumber(input.nextLine())); // Change amount of reps
                    break;
                case 3:
                    System.out.println("Enter new amount of sets:");
                    currentUser.workoutList.get(workoutNumber - 1).exerciseList.get(exerciseNumber - 1).setNumberOfSets(isNumber(input.nextLine())); // Change amount of sets
                    break;
                case 4:
                    currentUser.workoutList.get(workoutNumber - 1).exerciseList.get(exerciseNumber - 1).setTargetedMuscle(selectMuscleGroup()); // Change targeted muscle group
                    break;
                case 5:
                    return;
            }
            System.out.println("Exercise successfully updated!");
        } while (true);
    }




    // TODO: kolla över check.., alter, updateExer



    // TODO: fix userInput krasch
    // TODO: loop, om inte angivit giltligt tal, ange ett nytt...
    // TODO: kolla om string kan kovertas till tal
    // TODO: Ändra till att kolla om det är en dubbel
    // Checks if user input is a number (workout) or a number.number (exercise)
    public int[] returnWorkoutOrExercise(String mode) {
        //public int[] checkIfWorkoutOrExercise(String userInput, String mode) {
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
    }



    // TODO: förbättra
    public void editWorkout() {

        int[] indexArray = returnWorkoutOrExercise("change");
        int workoutNumber = indexArray[0];
        int exerciseNumber = indexArray[1];

        if (exerciseNumber == 0) {
            updateWorkout(workoutNumber);
        } else {
            updateExercise(workoutNumber, exerciseNumber);
        }
    }


    // TODO: försök konvertera usreInout till double annars returnera int (optional return values???)
    // TODO: Försök tilll att göra om wokroutOrexercise med double
    public void test() {


        // ta numret, gör om till int (ta bort punkt) första siffra blir
        System.out.printf("Enter the prefix-number of the workout or exercise you want to change");
        String userInput = input.nextLine().replace(".", "").trim();
        System.out.println(userInput);


        /*
        double decimalNumb = 0.0;
        System.out.printf("Enter the prefix-number of the workout or exercise you want to change");
        String userInput = input.nextLine();

        try {
            decimalNumb = Double.parseDouble(userInput);
        } catch (Exception e) {
            System.out.println("Didnt wokr"); // Loop, bool etc.
        }

        int workoutNumb = (int)decimalNumb;
        System.out.println(workoutNumb);*/

    }




    // Delete workouts or exercises
    public void deleteWorkout() {

        int[] indexArray = returnWorkoutOrExercise("delete");
        int workoutNumber = indexArray[0];
        int exerciseNumber = indexArray[1];

        if (exerciseNumber == 0) {
            System.out.printf("Workout '%s' was deleted!\n", currentUser.workoutList.get(workoutNumber - 1).getWorkoutName());
            currentUser.workoutList.remove(workoutNumber - 1); // Remove workout

        } else {
            // If exercise is the last in workout, then delete the whole workout
            if (currentUser.workoutList.get(workoutNumber - 1).exerciseList.size() <= 1) {
                System.out.printf("Workout '%s' was deleted!\n", currentUser.workoutList.get(workoutNumber - 1).getWorkoutName());
                currentUser.workoutList.remove(workoutNumber - 1);

            } else {
                System.out.printf("Exercise '%s' was deleted!\n", currentUser.workoutList.get(workoutNumber - 1).exerciseList.get(exerciseNumber -1).getExerciseName());
                currentUser.workoutList.get(workoutNumber - 1).removeExercise(exerciseNumber - 1); // Delete exercise
            }
        }
        return;
    }


    // TODO: Go back function!!!
    public void searchWorkout() {

        // TODO: find better way
        int matchingWorkouts = 0;
        int relatedWorkouts = 0;

        System.out.println("Enter name of the workout you are looking for:");
        input.nextLine();
        String searchedWorkout = input.nextLine();


        for (int i = 0; i < currentUser.workoutList.size(); i++) {

            if (currentUser.workoutList.get(i).getWorkoutName().equalsIgnoreCase(searchedWorkout)) {
                if (matchingWorkouts == 0) {
                    System.out.println("Workout(s) Found:");
                }
                System.out.println(currentUser.workoutList.get(i).getWorkoutName());
                currentUser.workoutList.get(i).showExercises(i + 1);
                matchingWorkouts++;

                // TODO: FIxa för många upperCase?
                //} else if (workout.contains(searchedWorkout) &&  !workout.equals(searchedWorkout)) {
            } else if (currentUser.workoutList.get(i).getWorkoutName().toUpperCase().contains(searchedWorkout.toUpperCase()) && matchingWorkouts == 0) {

                // Only get related workouts in no exact match are found
                if (relatedWorkouts == 0) {
                    System.out.printf("Workout that contains '%s' found:\n", searchedWorkout);
                }
                System.out.println(currentUser.workoutList.get(i).getWorkoutName());
                currentUser.workoutList.get(i).showExercises(i + 1);
                relatedWorkouts++;
            }
        }

        // If no matching or related workout found
        if (matchingWorkouts == 0 && relatedWorkouts == 0) {
            System.out.println("No workouts found with that name");
        }
    }




    // TODO: egen klass?
    // TODO: loop
    public void help() {
        System.out.println("What do you need help with?");
        System.out.println("1. How to create a workout\n2. How to edit a workout\n3. How to search for a workout\n4. Go Back\n");

        int menuSelector = isNumber(input.nextLine());

        switch (menuSelector) {
            case 1:
                helpCreateWorkout();
                break;
            case 2:
                helpEditWorkout();
                break;
            case 3:
                System.out.println("How to search");
                break;
            case 4:
                return;
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
            System.out.println("1. Add Gym Buddy\n2. Edit Gym Buddy\n3. Show Friends\n4. Search\n5. Sort\n6. Go Back");
            int menuSelection = isNumber(input.nextLine());

            switch (menuSelection) {
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
                    return;
            }
        } while (true);
    }










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





    // TODO: COmbine with search workout?
    public void searchGymBuddy() {

        int numberOfMatchingNames = 0;
        int numberOfRelatedNames = 0;

        System.out.println("Enter name of the friend you are looking for:");
        input.nextLine();
        String searchedFriend = input.nextLine();


        for (int i = 0; i < currentUser.gymBuddies.size(); i++) {

            if (currentUser.gymBuddies.get(i).getFullName().equalsIgnoreCase(searchedFriend)) {
                if (numberOfMatchingNames == 0) {
                    System.out.println("Friend(s) Found:");
                }
                System.out.println(currentUser.gymBuddies.get(i).getFullName());
                numberOfMatchingNames++;

                // TODO: FIxa för många upperCase?
                //} else if (workout.contains(searchedWorkout) &&  !workout.equals(searchedWorkout)) {
            } else if (currentUser.gymBuddies.get(i).getFullName().toUpperCase().contains(searchedFriend.toUpperCase()) && numberOfMatchingNames == 0) {

                // Only get related workouts in no exact match are found
                if (numberOfRelatedNames == 0) {
                    System.out.printf("Friend that contains '%s' found:\n", searchedFriend);
                }
                System.out.println(currentUser.gymBuddies.get(i).getFullName());
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

            currentUser.addGymBuddy(firstName, lastName);
            System.out.println(currentUser.gymBuddies.get(currentUser.gymBuddies.size() - 1).getFullName() + " was added to your friendlist");

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
        if (currentUser.gymBuddies.isEmpty()) {
            System.out.println("\t-Empty");
            return;
        }


        currentUser.showGymBuddies();
        /*for (int i = 0; i < accountHolder.gymBuddies.size(); i++) {
            System.out.printf("\n%s. %s\n", i + 1, accountHolder.gymBuddies.get(i).getWorkoutName());

            accountHolder.workoutList.get(i).showExercises(i + 1);
        }*/

    }

    public void editGymBuddy() {

        // Show all gym buddies
        showGymBuddies();

        if (currentUser.gymBuddies.size() == 0) {
            return;
        }

        // Choose buddy
        System.out.println("Enter the number of the friend you wish to change:");
        int friendIndex = isNumber(input.nextLine());

        do {
            System.out.println("Options:\n1. Edit First Name\n2. Edit Last Name\n3. Remove Friend\n4. Go Back");
            int menuSelection = isNumber(input.nextLine());

            switch (menuSelection) {
                case 1:
                    System.out.println("Enter new first name:");
                    currentUser.gymBuddies.get(friendIndex - 1).setFirstName(input.nextLine().trim());
                    break;
                case 2:
                    System.out.println("Enter new last name");
                    currentUser.gymBuddies.get(friendIndex - 1).setLastName(input.nextLine().trim());
                    break;
                case 3:
                    currentUser.removeGymBuddy(friendIndex - 1);
                    return;
                case 4:
                    return;
            }
        } while (true);
    }



    // TODO: enum för olika sortering
    // TODO: lägg sort i edit
    public void sortWorkout() {

        /*Comparator<Workout> compareByName = new Comparator<Workout>() {
            @Override
            public int compare(Workout o1, Workout o2) {
                //return 0;
                return o1.getWorkoutName().compareTo(o2.getWorkoutName());
            }
        };*/

        //Collections.sort();

        ArrayList<Workout> workouts = currentUser.workoutList;
        //Comparator<Workout> compareByName = (Workout o1, Workout o2) ->



        //Comparator<Workout> compareByName = (Workout o1, Workout o2) -> o1.getWorkoutName().compareTo(o2.getWorkoutName());
        //Comparator<Workout> compareBySometthing



        //showWorkouts();
        //Collections.sort(accountHolder.workoutList);
        // Return if empty
        /*if (!workoutList.isEmpty()) {

        }*/
        //Collections.sort(workoutList);


    }

    // RÄtt eller använd index??
    public void sortWorkoutsManually(Workout firstWorkout, Workout secondWorkout) {

        Workout tempWorkout = firstWorkout;
        firstWorkout = secondWorkout;
        secondWorkout = tempWorkout;

    }


    public void sortByName() {

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
    public int isNumber(String str) {


        do {
            try {
                return Integer.parseInt(str);
            } catch (Exception e) {
                System.out.println("Must enter a number:");
                return 0;
            }
        } while (true);
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


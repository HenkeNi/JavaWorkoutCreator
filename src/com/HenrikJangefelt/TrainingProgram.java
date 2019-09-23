package com.HenrikJangefelt;

import java.util.*;

// TODO: FIxa try and catch for inputs!!
// TODO: use enums more!!!
// TODO: randomera/slumpa fram en övning?
// TODO: ta in userInout som String och omvandla sedan (Fixar bugg med nextLine inte läses in?) TODO: FIx isNumber
// TODO: add functionallity for changing your username
// TODO: ADD staffMembers (Friends is mixture of GymMembers and staffmembers?)
// TODO: KOLLA FÖRST OM DET REDAN FINNS: Men vid add exercise i edit kunna gå tillbaka istället för att ange vilken workout att lägga till i
// TODO: kolla att enum Muscle fungerar
// TODO: clean - rensa alla

// TODO: Are you sure you want to delete friend/workout/exercise

// TODO:ADD friends
// TODO: sök på antgingen workout eller friend

public class TrainingProgram {

    Scanner input = new Scanner(System.in);
    GymMember accountHolder = new GymMember("MrOrMrs.", "Somebody"); // TODO: Sätt default värde i Person istället?


    public TrainingProgram() {

        createNewAccount();
        mainMenu();
    }


    // TODO: Rename
    public void createNewAccount() {

        System.out.println("\nWelcome to the workout creator!\nHere you can create your own custom workouts\n" +
                "\nBut first you must enter some personal details:");

        System.out.println("\nFirstname:");
        String firstName = input.nextLine().trim();
        System.out.println("Lastname:");
        String lastName = input.nextLine().trim();
        accountHolder.setFirstName(firstName);
        accountHolder.setLastName(lastName);

        System.out.printf("Welcome %s!\n\n", accountHolder.getFullName());
    }


    // TODO: combine addWorkout with add Gym buddies -> submenu
    public void mainMenu() {

        int menuSelection = 0;
        String userInput = "";

        //System.out.println("\nThis is the workout creator! Here you can create new workouts");

        do {
            System.out.println("This is the Main Menu.\n1. Add or Edit Workouts\n2. Add or Edit Friend List\n3. Search\n4. Show\n5. Help(TODO)\n6. Check Available Staff\n7. Exit\n8. TEST SORT");
            //System.out.println("This is the Main Menu.\n1. Add Workouts\n2. Edit Workouts\n3. Add Gym Buddies\n4. Edit Gym Buddies \n5. Search\n6. Show all\n7. Help(TODO)\n8. Exit");
            userInput = input.nextLine();

            menuSelection = isNumber(userInput);
            //int numberInt = checkIfValidNumber(userInput);

            switch (menuSelection) {
                case 1:
                    addOrEditWorkout();
                    //createNewWorkout();
                    break;
                case 2:
                    addOrEditFriendList();
                    //editWorkouts();
                    break;
                case 3:
                    searchWorkoutOrFriend();
                    // searchWorkout();
                    break;
                case 4:
                    showFriendsOrWorkouts();
                    break;
                case 5:
                    help();
                    break;
                case 6:
                    checkAvailableStaff();
                    break;
                case 7:
                    return;
                case 8:
                    sortWorkout();
                    break;
            }
        } while (menuSelection != 7);
    }


    // TODO: Fixa flexible minimenu!?
    // public void miniMenu(String option1, String option2) {
    /*public void miniMenu(String[] menuOptions) {

        for (int i = 0; i < menuOptions.length; i++) {
            System.out.printf("%s. %s\n", i + 1, menuOptions[i]);
        }

        int menuSelection = isNumber(input.nextLine());

        switch (menuSelection) {

        }
    }*/

    public void checkAvailableStaff() {

        Gym currentGym = new Gym();

        // TODO: add workdays (5 days of the week)
        //Date todaysDate = new Date();
        //todaysDate.getTime();

        //System.out.println(java.time.LocalTime.now().getHour()); // GETS Current time

        int currentTime = java.time.LocalTime.now().getHour();

        for (int i = 0; i < currentGym.staffMembers.size(); i++) {

            if (currentTime > currentGym.staffMembers.get(i).getShiftStartHour() && currentTime < currentGym.staffMembers.get(i).getShiftEndHour()) {
                System.out.printf("Available staff: %s %s\n\n", currentGym.staffMembers.get(i).getFullName(), currentGym.staffMembers.get(i).getFullWorkShift()); // TODO: print staff
            }
        }
    }

    // TODO: Show (if there is, workouts before adding or editing)
    public void addOrEditWorkout() {

        // TODO: Show gymBuddies??
        do {
            System.out.println("1. Add Workout\n2. Edit Workout\n3. Go Back");
            int menuSelection = isNumber(input.nextLine());

            switch (menuSelection) {
                case 1:
                    createNewWorkout();
                    break;
                case 2:
                    editWorkouts();
                    break;
                case 3:
                    return;
            }
        } while (true);
    }

    // TODO: Show if possible friends before
    public void addOrEditFriendList() {

        do {
            System.out.println("1. Add Gym Buddy\n2. Edit Gym Buddy\n3. Go Back");
            int menuSelection = isNumber(input.nextLine());

            switch (menuSelection) {
                case 1:
                    addGymBuddy();
                    break;
                case 2:
                    editGymBuddy();
                    break;
                case 3:
                    return;
            }
        } while (true);
    }

    public void showFriendsOrWorkouts() {

        do {
            System.out.println("Choose which to show:\n1. Workouts\n2. Friends\n3. Go Back");
            int menuSelection = isNumber(input.nextLine());

            switch (menuSelection) {
                case 1:
                    showWorkouts();
                    break;
                case 2:
                    showGymBuddies();
                    break;
                case 3:
                    return;
            }
        } while (true);
    }


    public void searchWorkoutOrFriend() {
        System.out.println("Search for workout or friend\n1. Workout\n2. Friend");
        int menuSelection = isNumber(input.nextLine());

        switch (menuSelection) {
            case 1:
                searchWorkout();
            case 2:
                searchGymBuddy();
        }
    }


    public void createNewWorkout() {
        System.out.println("Enter new workout's name:");
        //input.nextLine(); // TODO: Kan ta bort?
        accountHolder.addWorkout(input.nextLine());

        addExercises(accountHolder.workoutList.size() - 1); // Access last element


    }


    // TODO: Fixa inout number från string (går att ange bokstav)
    public void addExercises(int workoutIndex) {

        do {
            System.out.println("Enter exercise name:");
            String exerciseName = input.nextLine();

            System.out.println("Enter amount of reps:");
            int amountOfReps = isNumber(input.nextLine());

            System.out.println("Enter amount of sets:");
            int amountOfSets = isNumber(input.nextLine());

            Exercise.Muscle targetedMuslce = enterTargetedMuscle();

            //System.out.println(targetedMuslce);
            accountHolder.workoutList.get(workoutIndex).addExercise(exerciseName, amountOfReps, amountOfSets, targetedMuslce);

            System.out.printf("Workout: \'%s\' currently consist of %s exercises\n", accountHolder.workoutList.get(workoutIndex).getWorkoutName(), accountHolder.workoutList.get(workoutIndex).exerciseList.size());
            System.out.println("1. Add another exercise\n2. Go Back");

            int menuSelection = isNumber(input.nextLine());

            switch (menuSelection) {
                case 1:
                    break;
                case 2:
                    return;
            }

        } while (true);
    }


    public Exercise.Muscle enterTargetedMuscle() {
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


    /*public void exerciseTargetedMuscle(int workoutNumber, int exerciseNumber) {
        System.out.println("Choose targeted muscle for exercise:\n" +
                "1. Chest\n" +
                "2. Back\n" +
                "3. Shoulders\n" +
                "4. Biceps\n" +
                "5. Triceps\n" +
                "6. Abs\n" +
                "7. Legs\n");

        int muscleSelection = isNumber(input.nextLine());

        switch (muscleSelection) {
            case 1:
                accountHolder.workoutList.get(workoutNumber -1).exerciseList.get(exerciseNumber - 1).setTargetedMuscle(Exercise.Muscle.CHEST);
                break;
            case 2:
                accountHolder.workoutList.get(workoutNumber -1).exerciseList.get(exerciseNumber - 1).setTargetedMuscle(Exercise.Muscle.BACK);
                break;
            case 3:
                accountHolder.workoutList.get(workoutNumber -1).exerciseList.get(exerciseNumber - 1).setTargetedMuscle(Exercise.Muscle.SHOULDERS);
                break;
            case 4:
                accountHolder.workoutList.get(workoutNumber -1).exerciseList.get(exerciseNumber - 1).setTargetedMuscle(Exercise.Muscle.BICEPS);
                break;
            case 5:
                accountHolder.workoutList.get(workoutNumber -1).exerciseList.get(exerciseNumber - 1).setTargetedMuscle(Exercise.Muscle.TRICEPS);
                break;
            case 6:
                accountHolder.workoutList.get(workoutNumber -1).exerciseList.get(exerciseNumber - 1).setTargetedMuscle(Exercise.Muscle.ABS);
                break;
            case 7:
                accountHolder.workoutList.get(workoutNumber -1).exerciseList.get(exerciseNumber - 1).setTargetedMuscle(Exercise.Muscle.LEGS);
                break;
        }
    }*/




    // Edit workouts menu
    public void editWorkouts() {

        int menuSelection = 0;
        String userInput = "";

        do {
            showWorkouts();

            if (accountHolder.workoutList.size() != 0) {
                System.out.println("\nOptions:\n1. Add exercise\n2. Edit\n3. Delete\n4. Go Back");
                userInput = input.nextLine();

                 menuSelection = isNumber(userInput);

                switch (menuSelection) {
                    case 1:
                        addMoreExercises(); // Add new exercise to existing workout
                        break;
                    case 2:
                        alterWorkout(); // Change name of workout/exercise, amount of reps, etc.
                        break;
                    case 3:
                        deleteWorkout(); // Delete workout or exercise
                        break;
                    case 4:
                        return; // Go Back
                }
            }
        } while (!accountHolder.workoutList.isEmpty());
    }



    // TODO: GÖr om till generics (tar in antingen workoutList eller clientList)? // ELLER TAR IN ARRAY AV OBJECT
    // TODO: INte metdo??? lägg kod i switch?
    public void showWorkouts() {

        //System.out.println(accountHolder.getFullName() + "'s current Workouts:"); // TODO: ange namn?
        System.out.println("Current Workouts:");

        // If no workouts in workoutList
        if (accountHolder.workoutList.isEmpty()) {
            System.out.println("\t-Empty");
            return;
        }

        accountHolder.showWorkouts();
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



    // TODO: fix userInput krasch
    // Checks if user input is a number (workout) or a number.number (exercise)

    public int[] checkIfWorkoutOrExercise(String mode) {
        //public int[] checkIfWorkoutOrExercise(String userInput, String mode) {
        System.out.printf("Enter the prefix-number of the workout or exercise you want to %s:\n", mode);
        input.nextLine();
        String selectedWorkout = input.nextLine();
        int[] indexArray = new int[2];

        // TODO: loop, om inte angivit giltligt tal, ange ett nytt...

        // TODO: kolla om string kan kovertas till tal


        // TODO: Ändra till att kolla om det är en dubbel
        // TODO: övning blir t.ex: 1.12 för workout 1 och övning 12
        // TODO: kolla om det double är i en funktion....
        // User entered an exercise in a workout
        if (selectedWorkout.contains(".") && selectedWorkout.length() == 3 && selectedWorkout.charAt(1) == '.') {

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


    // Delete workouts or exercises
    public void deleteWorkout() {

        int[] indexArray = checkIfWorkoutOrExercise("delete");
        int workoutNumber = indexArray[0];
        int exerciseNumber = indexArray[1];

        if (exerciseNumber == 0) {
            System.out.printf("Workout '%s' was deleted!\n", accountHolder.workoutList.get(workoutNumber - 1).getWorkoutName());
            accountHolder.workoutList.remove(workoutNumber - 1); // Remove workout

        } else {
            // If exercise is the last in workout, then delete the whole workout
            if (accountHolder.workoutList.get(workoutNumber - 1).exerciseList.size() <= 1) {
                System.out.printf("Workout '%s' was deleted!\n", accountHolder.workoutList.get(workoutNumber - 1).getWorkoutName());
                accountHolder.workoutList.remove(workoutNumber - 1);

            } else {
                System.out.printf("Exercise '%s' was deleted!\n", accountHolder.workoutList.get(workoutNumber - 1).exerciseList.get(exerciseNumber -1).getExerciseName());
                accountHolder.workoutList.get(workoutNumber - 1).removeExercise(exerciseNumber - 1); // Delete exercise
            }
        }
        return;
    }


    // Add additional exercises to a workout
    public void addMoreExercises() {

        System.out.println("Enter the number of the workout you wish to add exercise to");
        //int workoutIndex = input.nextInt();
        int workoutIndex = isNumber(input.nextLine());
        addExercises(workoutIndex - 1);
    }


    // TODO: bryt upp! För stor funktion
      public void alterWorkout() {

        int[] indexArray = checkIfWorkoutOrExercise("change");
        int workoutNumber = indexArray[0];
        int exerciseNumber = indexArray[1];

          if (exerciseNumber == 0) {
            updateWorkout(workoutNumber);
        } else {
            updateExercise(workoutNumber, exerciseNumber);
        }
    }


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
                    "5. GO Back\n");


            userInput = input.nextLine();
            menuSelection = isNumber(userInput);

            switch (menuSelection) {
                case 1:
                    System.out.println("Enter new exercise name:");
                    input.nextLine();
                    String newName = input.nextLine();
                    System.out.println("Name was changed to " + newName);
                    accountHolder.workoutList.get(workoutNumber - 1).exerciseList.get(exerciseNumber - 1).setExerciseName(newName); // Change exercise name
                    break;
                case 2:
                    System.out.println("Enter new amount of reps:");
                    int newReps = isNumber(input.nextLine());
                    accountHolder.workoutList.get(workoutNumber - 1).exerciseList.get(exerciseNumber - 1).setNumberOfReps(newReps); // Change amount of reps
                    break;
                case 3:
                    System.out.println("Enter new amount of sets:");
                    int newSets = isNumber(input.nextLine());
                    accountHolder.workoutList.get(workoutNumber - 1).exerciseList.get(exerciseNumber - 1).setNumberOfSets(newSets); // Change amount of sets
                    break;
                case 4:
                    accountHolder.workoutList.get(workoutNumber - 1).exerciseList.get(exerciseNumber - 1).setTargetedMuscle(enterTargetedMuscle());
                    //exerciseTargetedMuscle(workoutNumber, exerciseNumber);
                    //accountHolder.workoutList.get(workoutNumber -1).exerciseList.get(exerciseNumber - 1).setTargetedMuscle(Exercise.Muscle.BICEPS);
                    break;
                case 5:
                    return;
            }
        } while (true);
    }


    public void updateWorkout(int workoutNumber) {

        int menuSelection = 0;
        String userInput = "";

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
                    accountHolder.workoutList.get(workoutNumber - 1).setWorkoutName(newWorkoutName); // Change workout name
                    break;
                case 2:
                    return;
            }
        } while (true);
    }




    // TODO: Go back function!!!
    public void searchWorkout() {

        // TODO: find better way
        int matchingWorkouts = 0;
        int relatedWorkouts = 0;

        System.out.println("Enter name of the workout you are looking for:");
        input.nextLine();
        String searchedWorkout = input.nextLine();


        for (int i = 0; i < accountHolder.workoutList.size(); i++) {

            if (accountHolder.workoutList.get(i).getWorkoutName().equalsIgnoreCase(searchedWorkout)) {
                if (matchingWorkouts == 0) {
                    System.out.println("Workout(s) Found:");
                }
                System.out.println(accountHolder.workoutList.get(i).getWorkoutName());
                accountHolder.workoutList.get(i).showExercises(i + 1);
                matchingWorkouts++;

                // TODO: FIxa för många upperCase?
            //} else if (workout.contains(searchedWorkout) &&  !workout.equals(searchedWorkout)) {
            } else if (accountHolder.workoutList.get(i).getWorkoutName().toUpperCase().contains(searchedWorkout.toUpperCase()) && matchingWorkouts == 0) {

                // Only get related workouts in no exact match are found
                if (relatedWorkouts == 0) {
                    System.out.printf("Workout that contains '%s' found:\n", searchedWorkout);
                }
                System.out.println(accountHolder.workoutList.get(i).getWorkoutName());
                accountHolder.workoutList.get(i).showExercises(i + 1);
                relatedWorkouts++;
            }
        }

        // If no matching or related workout found
        if (matchingWorkouts == 0 && relatedWorkouts == 0) {
            System.out.println("No workouts found with that name");
        }
    }


    // TODO: COmbine with search workout?
    public void searchGymBuddy() {

        int numberOfMatchingNames = 0;
        int numberOfRelatedNames = 0;

        System.out.println("Enter name of the friend you are looking for:");
        input.nextLine();
        String searchedFriend = input.nextLine();


        for (int i = 0; i < accountHolder.gymBuddies.size(); i++) {

            if (accountHolder.gymBuddies.get(i).getFullName().equalsIgnoreCase(searchedFriend)) {
                if (numberOfMatchingNames == 0) {
                    System.out.println("Friend(s) Found:");
                }
                System.out.println(accountHolder.gymBuddies.get(i).getFullName());
                numberOfMatchingNames++;

                // TODO: FIxa för många upperCase?
                //} else if (workout.contains(searchedWorkout) &&  !workout.equals(searchedWorkout)) {
            } else if (accountHolder.gymBuddies.get(i).getFullName().toUpperCase().contains(searchedFriend.toUpperCase()) && numberOfMatchingNames == 0) {

                // Only get related workouts in no exact match are found
                if (numberOfRelatedNames == 0) {
                    System.out.printf("Friend that contains '%s' found:\n", searchedFriend);
                }
                System.out.println(accountHolder.gymBuddies.get(i).getFullName());
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

            accountHolder.addGymBuddy(firstName, lastName);
            System.out.println(accountHolder.gymBuddies.get(accountHolder.gymBuddies.size() - 1).getFullName() + " was added to your friendlist");

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
        if (accountHolder.gymBuddies.isEmpty()) {
            System.out.println("\t-Empty");
            return;
        }


        accountHolder.showGymBuddies();
        /*for (int i = 0; i < accountHolder.gymBuddies.size(); i++) {
            System.out.printf("\n%s. %s\n", i + 1, accountHolder.gymBuddies.get(i).getWorkoutName());

            accountHolder.workoutList.get(i).showExercises(i + 1);
        }*/

    }

    public void editGymBuddy() {

        // Show all gym buddies
        showGymBuddies();

        if (accountHolder.gymBuddies.size() == 0) {
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
                    accountHolder.gymBuddies.get(friendIndex - 1).setFirstName(input.nextLine().trim());
                    break;
                case 2:
                    System.out.println("Enter new last name");
                    accountHolder.gymBuddies.get(friendIndex - 1).setLastName(input.nextLine().trim());
                    break;
                case 3:
                    accountHolder.removeGymBuddy(friendIndex - 1);
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

        ArrayList<Workout> workouts = accountHolder.workoutList;
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







    public void checkForValidEmail() {

    }


    public void saveSocialSecurityNumber() {
        System.out.println("Input your social security number:");

        String socialSecurityNumb = "";

        do {
            socialSecurityNumb = input.nextLine();

            if (socialSecurityNumb.length() == 11 && socialSecurityNumb.charAt(6) == '-') {
                // Save or return number
                return;
            }
        } while (true);
    }
}






  /*public void createNewWorkout() {
        System.out.println("Enter new workout's name:");
        input.nextLine(); // TODO: Kan ta bort?
        Workout newWorkout = new Workout(input.nextLine().trim());
        //workoutList.add(newWorkout);
        accountHolder.workoutList.add(newWorkout);

        addExercises(newWorkout);
    }*/


        /*public void addExercises(Workout newWorkout) {

        boolean addingExercise = true;
        String userInput = "";

        do {

            // Must add at least one exercise to a workout
            if (newWorkout.exerciseList.size() == 0) {
                newWorkout = createExercise(newWorkout);
                //addNewExercise(newWorkout);
            } else {
                System.out.printf("Workout: \'%s\' currently consist of %s exercises\n", newWorkout.getWorkoutName(), newWorkout.exerciseList.size());
                System.out.println("1. Add another exercise\n2. Go back");
                userInput = input.nextLine();


                int menuSelection = isNumber(userInput);

                switch (menuSelection) {
                    case 1:
                        newWorkout = createExercise(newWorkout); // Add exercise to current workout
                        //addNewExercise(newWorkout);
                        break;
                    case 2:
                        addingExercise = false;
                        break;
                }
            }
        } while (addingExercise);

        //workoutList.add(newWorkout); // Add workout to workoutList
    }*/


    /*public Workout createExercise(Workout newWorkout) {

        System.out.println("Name of exercise to add:");

        // Fixes bug(?) where user input isn't registered
        // TODO: ta bort?
        if (newWorkout.exerciseList.size() > 0) {
            input.nextLine();
        }
        String exerciseName = input.nextLine();

        System.out.println("Amount of Reps for exercise:");
        int amountOfReps = isNumber(input.nextLine());

        System.out.println("Amount of Sets for exercise:");
        int amountOfSets = isNumber(input.nextLine());
        //int amountOfSets = checkForValidInput();

        newWorkout.addExercise(exerciseName, amountOfReps, amountOfSets); // Add exercise to workout
        System.out.printf("Exercise '%s' was added to workout '%s'!\n\n", exerciseName, newWorkout.getWorkoutName());
        return newWorkout;
    }*/


    /*
      // Delete workouts or exercises
    public void deleteUserWorkout() {

        int[] indexArray = checkIfWorkoutOrExercise("delete");
        int workoutNumber = indexArray[0];
        int exerciseNumber = indexArray[1];

        if (exerciseNumber == 0) {
            System.out.printf("Workout '%s' was deleted!\n", workoutList.get(workoutNumber - 1).getWorkoutName());
            workoutList.remove(workoutNumber - 1); // Remove workout

        } else {
            // If exercise is the last in workout, then delete the whole workout
            if (workoutList.get(workoutNumber - 1).exerciseList.size() <= 1) {
                System.out.printf("Workout '%s' was deleted!\n", workoutList.get(workoutNumber - 1).getWorkoutName());
                workoutList.remove(workoutNumber - 1);

            } else {
                System.out.printf("Exercise '%s' was deleted!\n", workoutList.get(workoutNumber - 1).exerciseList.get(exerciseNumber -1).getExerciseName());
                workoutList.get(workoutNumber - 1).removeExercise(exerciseNumber - 1); // Delete exercise
            }
        }
        return;
    }
     */


    /*
      public void showWorkouts() {

        System.out.println("Current Workouts:");

        // If no workouts in workoutList
        if (accountHolder.workoutList.isEmpty()) {
            System.out.println("\t-Empty");
            return;
        }


        // Prints out all workouts in workoutList, including their exercises
        for (int i = 0; i < accountHolder.workoutList.size(); i++) {
            System.out.printf("\n%s. %s\n", i + 1, accountHolder.workoutList.get(i).getWorkoutName());

            accountHolder.workoutList.get(i).showExercises(i + 1);
        }
    }
     */
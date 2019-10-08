package com.HenrikJangefelt;

import java.util.*;

// TODO: dum idé att alla metoder tar in GymMember. Ja: onödigt? .Nej: Programmet funkar för att lägga till workouts till andra???

// TODO: egen class för StaffSchedule

// TODO: FIxa try and catch for inputs!!
// TODO: ta in userInout som String och omvandla sedan (Fixar bugg med nextLine inte läses in?) TODO: FIx isNumber

// TODO: Försök implementera: Interface, mer enums(?), abstrakta metoder, Singleton (design mönster)
// TODO: Förbättra: Jobba mer med att lägga logik i klasser
// TODO: Lägga till: Settings -> chang your username, ändra/se sitt password/email. Rensa alla arrays. Staff -> Ange veckodagar dem arbetar

// TODO: sätt så mycket som möjligt till private?

// TODO: Final check- kolla att man bara kan ange siffror, samt bara rätt intervall för tex arrayer...
// TODO: Final check Rensa klasser som inte används längre, compareTo/comparator kika närmare på

public class TrainingProgram {

    //Scanner input = new Scanner(System.in); // TODO Ta bort ich ha bara i view klassen!
    View view = View.getInstance(); // Get instance to View class

    static GymMember currentUser = new GymMember("", ""); // TEST (Static) TODO: (ta bort från konstruktorn i Person???) EJ STATIC!!

    public TrainingProgram() {
        showMainMenu(currentUser);
    }






    public void showMainMenu(GymMember gymMember) {  // TODO: Renmae gymMember

        do {
            view.showMenu(View.MainMenuItem.class, "Main Menu:"); // Displays main menu by sending correct enumType to the showMenu method in 'View' class.

            // Switch on the returned enum case from the View class.
            switch (view.getMenuChoice(View.MainMenuItem.class)) {

                case WORKOUTS:
                    showWorkoutMenu(gymMember);
                    break;
                case FRIENDS:
                    showFriendsMenu();
                    break;
                case AVAILABLE_STAFF:
                    checkAvailableStaff();
                    break;
                case HELP:
                    help();
                    break;
                case EXIT:
                    return;
            }
        } while (true);
    }


    // TODO: lägg till funktion för att köra passet?
    private void showWorkoutMenu(GymMember gymMember) {

        do {
            view.showMenu(View.SubMenuItem.class, "Workout Menu:"); // Displays main menu by sending correct enumType to the showMenu method in 'View' class.

            // TODO: lägg till funktion för att köra passet?
            switch (view.getMenuChoice(View.SubMenuItem.class)) {

                case ADD:
                    createWorkout(gymMember);
                    break;
                case EDIT:
                    showEditWorkoutMenu(gymMember);
                    break;
                case SHOW:
                    showWorkouts(gymMember, gymMember.getWorkoutList());
                    break;
                case SEARCH:
                    searchWorkout(gymMember);
                    break;
                case SORT:
                    sortMenu(gymMember);
                    break;
                case BACK:
                    return;
            }
        } while (true);
    }


    // TODO: make a workoutFactory
    public void createWorkout(GymMember gymMember) {

        UserInput userInput = view.getUserInput(UserInput.InputType.STRING, "Enter workout's name:");
        gymMember.addWorkout(userInput.message);

        //String workoutName = view.getUserInput("Enter workout's name");
        //gymMember.addWorkout(workoutName);

        //System.out.println("Enter workout's name:");
        //gymMember.addWorkout(input.nextLine());


        addExercise(gymMember, gymMember.getWorkoutList().size() - 1); // Add new exercise to the last workout in the workoutList







        /*for (int i = 0; i < gymMember.getWorkoutList().size(); i++) {
            gymMember.getWorkoutList().get(i).showExercises(i);
        }*/

        ArrayList<Workout> workouts = gymMember.getWorkoutList();

        // TODO: hämta workouts från textfil (om det finns någon), ladda upp igen (plus ny workout)
        //FileUtils.writeGenericObjects("workouts.ser", gymMember.getWorkoutList());
        FileUtils.writeGenericObjects("workouts.ser", workouts);

        //System.out.println(gymMember.getWorkoutList());
        List<Workout> savedWorkouts = FileUtils.readGenericObjects("workouts.ser");
        savedWorkouts.get(0).showExercises(0);
    }







    public void addExercise(GymMember gymMember, int workoutIndex) {

        do {
            UserInput userInput = view.getUserInput(UserInput.InputType.STRING, "Exercise name:");
            String exerciseName = userInput.message;

            userInput = view.getUserInput(UserInput.InputType.INT, "Number of reps:");
            int numberOfReps = userInput.number;

            userInput = view.getUserInput(UserInput.InputType.INT, "Number of sets:");
            int numberOfSets = userInput.number;

            gymMember.getWorkoutList().get(workoutIndex).addExercise(exerciseName, numberOfReps, numberOfSets, selectMuscleGroup());

            // TODO: put in view?
            System.out.printf("Workout: \'%s\' currently consist of %s exercises\n", gymMember.getWorkoutList().get(workoutIndex).getWorkoutName(), gymMember.getWorkoutList().get(workoutIndex).getExerciseList().size());
            System.out.println("1. Add another exercise\n0. Go Back");

            int menuSelection = getNumberFromUserInput();

            switch (menuSelection) {
                case 0:
                    return;
            }
        } while (true);
    }


    // TODO: Bara kunna ange tal mellan 1-7
    // Choose worked muscle group for exercise
    public Exercise.Muscle selectMuscleGroup() {

        view.showMenu(Exercise.Muscle.class, "Choose targeted muscle for exercise:");
        
        int muscleSelection = view.getNumberFromUserInput();

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

        do {
            showWorkouts(gymMember, gymMember.getWorkoutList());

            if (gymMember.getWorkoutList().size() != 0) {
                System.out.println("\nOptions:\n1. Add exercise\n2. Edit\n3. Delete\n0. Go Back");

                int menuSelection = getNumberFromUserInput();

                switch (menuSelection) {
                    case 0:
                        return;
                    case 1:
                        System.out.println("Enter the number of the workout you wish to add exercise to");
                        addExercise(gymMember, getNumberFromUserInput() - 1);
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

        do {
            System.out.println("" +
                    "Workout options:\n" +
                    "1. Change workout name\n" +
                    "0. Go Back");

            int menuSelection = getNumberFromUserInput();

            switch (menuSelection) {
                case 0:
                    return;
                case 1:
                    // TODO: GÖr till funktion (eller lägg addNEwExercise i switch case med)
                    System.out.println("Enter new workout name:");
                    String newWorkoutName = input.nextLine();
                    gymMember.getWorkoutList().get(workoutNumber - 1).setWorkoutName(newWorkoutName); // Change workout name
                    break;
            }
            System.out.println("Workout successfully updated!");
        } while (true);
    }


    public void updateExercise(GymMember gymMember, int workoutNumber, int exerciseNumber) {

        do {
            System.out.println("" +
                    "Exercise options:\n" +
                    "1. Change exercise name\n" +
                    "2. Change amount of reps\n" +
                    "3. Change amount of sets\n" +
                    "4. Change targeted muscle\n" +
                    "0. Go Back\n");

            int menuSelection = getNumberFromUserInput();

            switch (menuSelection) {
                case 0:
                    return;
                case 1:
                    System.out.println("Enter new exercise name:");
                    gymMember.getWorkoutList().get(workoutNumber - 1).getExerciseList().get(exerciseNumber - 1).setExerciseName(input.nextLine()); // Change exercise name
                    break;
                case 2:
                    System.out.println("Enter new amount of reps:");
                    gymMember.getWorkoutList().get(workoutNumber - 1).getExerciseList().get(exerciseNumber - 1).setNumberOfReps(getNumberFromUserInput()); // Change amount of reps
                    break;
                case 3:
                    System.out.println("Enter new amount of sets:");
                    gymMember.getWorkoutList().get(workoutNumber - 1).getExerciseList().get(exerciseNumber - 1).setNumberOfSets(getNumberFromUserInput()); // Change amount of sets
                    break;
                case 4:
                    gymMember.getWorkoutList().get(workoutNumber - 1).getExerciseList().get(exerciseNumber - 1).setTargetedMuscle(selectMuscleGroup()); // Change targeted muscle group
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

        int[] prefixArray = {workoutIndex, exerciseIndex};
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
        System.out.println(gymMember.deleteWorkout(indexArray[0], indexArray[1]));
    }






    public void searchWorkout(GymMember gymMember) {


        System.out.println("Enter name of the workout you are looking for:");
        String searchedWorkout = input.nextLine();

        ArrayList<Workout> matches = gymMember.getSearchedObject(gymMember.getWorkoutList(), searchedWorkout);
        ArrayList<Workout> related = gymMember.getRelatedSearchedObject(gymMember.getWorkoutList(), searchedWorkout);

        if (!matches.isEmpty()) {
            System.out.println("Workout(s) Found:");
            gymMember.showWorkouts(matches);
        } else if (!related.isEmpty()) {
            System.out.printf("Workout that contains '%s' found:\n", searchedWorkout);
            gymMember.showWorkouts(related);
        } else {
            System.out.printf("No workouts matching %s found\n", searchedWorkout);
        }


        /*
        ArrayList<Workout> matchingWorkouts = gymMember.getSearchedWorkout(searchedWorkout);
        ArrayList<Workout> relatedWorkouts = gymMember.getRelatedSearchedWorkout(searchedWorkout);

        if (!matchingWorkouts.isEmpty()) {
            System.out.println("Workout(s) Found:");
            gymMember.showWorkouts(matchingWorkouts);

        } else if (!relatedWorkouts.isEmpty()) {
            System.out.printf("Workout that contains '%s' found:\n", searchedWorkout);
            gymMember.showWorkouts(relatedWorkouts);

        } else {
            System.out.printf("No workouts matching %s found\n", searchedWorkout);
        }*/
    }


    // TODO: sätt Workouts som optional?
    public void showWorkouts(GymMember gymMember, ArrayList<Workout> workouts) {

        System.out.println("Current Workouts:");

        if (gymMember.getWorkoutList().isEmpty()) {
            System.out.println("\t -Empty");
            return;
        }
        gymMember.showWorkouts(workouts);
    }


    public void sortMenu(GymMember gymMember) {

        do {
            System.out.println("Sort:\n1. Workouts\n2. Exercises\n0. Go Back");
            int sortSelection = getNumberFromUserInput();

            switch (sortSelection) {
                case 0:
                    return;
                case 1:
                    sortWorkout(gymMember);
                    break;
                case 2:
                    showWorkouts(gymMember, gymMember.getWorkoutList());
                    System.out.println("Enter workout prefix for sorting exercises");
                    sortExercises(gymMember, getNumberFromUserInput());
                    showWorkouts(gymMember, gymMember.getWorkoutList());
                    break;
            }
        } while (true);
    }

    // TODO: enum för olika sortering??
    // TODO: lägg sort i edit??
    public void sortWorkout(GymMember gymMember) {

        System.out.println("Sort by:\n1. Name\n0. Exit");
        int menuSelection = getNumberFromUserInput();

        switch (menuSelection) {

            case 0:
                return;
            case 1:
                SortWorkoutName sortWorkoutName = new SortWorkoutName();
                Collections.sort(gymMember.getWorkoutList(), sortWorkoutName);
                showWorkouts(gymMember, gymMember.getWorkoutList());
                break;
        }
    }

    public void sortExercises(GymMember gymMember, int workoutIndex) {
        System.out.println("Sort by:\n1. Name\n2. Reps\n3. Sets\n4. Muscle\n0. Go Back");
        int menuSelection = getNumberFromUserInput();

        switch (menuSelection) {
            case 0:
                return;
            case 1:
                SortExerciseName sortExerciseName = new SortExerciseName();
                Collections.sort(gymMember.getWorkoutList().get(workoutIndex - 1).getExerciseList(), sortExerciseName);
                break;
            case 2:
                SortExerciseReps sortExerciseReps = new SortExerciseReps();
                Collections.sort(gymMember.getWorkoutList().get(workoutIndex - 1).getExerciseList(), sortExerciseReps);
                break;
            case 3:
                SortExerciseSets sortExerciseSets = new SortExerciseSets();
                Collections.sort(gymMember.getWorkoutList().get(workoutIndex - 1).getExerciseList(), sortExerciseSets);
                break;
            case 4:
                SortExercisesMuscle sortExercisesMuscle = new SortExercisesMuscle();
                Collections.sort(gymMember.getWorkoutList().get(workoutIndex - 1).getExerciseList(), sortExercisesMuscle);
                break;
        }

    }


    // TODO: egen klass?
    // TODO: loop
    public void help() {
        System.out.println("What do you need help with?");
        System.out.println("1. How to create a workout\n2. How to edit a workout\n3. How to search for a workout\n0. Go Back\n");

        int menuSelector = getNumberFromUserInput();

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

    // Läs in från fil?
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
            int menuSelection = getNumberFromUserInput();

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
                    break;
                case 4:
                    searchGymBuddy();
                    break;
                case 5:
                    sortFriends(currentUser);
                    break;
            }
        } while (true);
    }


    public void sortFriends(GymMember gymMember) {

        SortFriend sortFriend = new SortFriend();
        Collections.sort(gymMember.getFriendList(), sortFriend);
        gymMember.showFriends(gymMember.getFriendList());
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


    // TODO: Ändra curretnUsers till gymMembers?? (nej: bara kunna en själv ska kunna söka. ja: programmet bör vara utformat för alla..)
    // TODO: COmbine with search workout?
    public void searchGymBuddy() {


        System.out.println("Enter name of the friend you are looking for:");
        String searchedFriend = input.nextLine();

        ArrayList<GymMember> matchingFriends = currentUser.getSearchedFriend(searchedFriend);
        ArrayList<GymMember> relatedFriends = currentUser.getRelatedSearchedFriend(searchedFriend);

        if (!matchingFriends.isEmpty()) {
            System.out.println("Friend(s) Found:");
            currentUser.showFriends(matchingFriends);

        } else if (!relatedFriends.isEmpty()) {
            System.out.printf("Friend(s) that contains '%s' found:\n", searchedFriend);
            currentUser.showFriends(relatedFriends);
        } else {
            System.out.printf("No friends matching %s found\n", searchedFriend);
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
            int menuSelection = getNumberFromUserInput();

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
        currentUser.showFriends(currentUser.getFriendList());
    }

    public void editGymBuddy() {

        // Show all gym buddies
        showGymBuddies();

        if (currentUser.getFriendList().size() == 0) {
            return;
        }

        // Choose buddy
        System.out.println("Enter the number of the friend you wish to change:");
        int friendIndex = getNumberFromUserInput();

        do {
            System.out.println("Options:\n1. Edit First Name\n2. Edit Last Name\n3. Remove Friend\n0. Go Back");
            int menuSelection = getNumberFromUserInput();

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


    // TODO: put in view class
    public int getNumberFromUserInput() {

        int numb = -999;

        do {
            String userInput = input.nextLine();
            try {
                numb = Integer.parseInt(userInput);
            } catch (Exception e) {
                System.out.println("Must enter a number:");
            }
        } while (numb == -999);
        return numb;
    }


    // TESTS!!


    // TODO: ta bort?
    /*public Number checkIfValidNumber(String str) {

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
    }*/


   /* public void showObjects(ArrayList<Object> objectList, String objectName) {

        System.out.printf("Current %s\n", objectName);

        if (objectList.isEmpty()) {
            System.out.println("\t-Empty");
            return;
        }

        // TODO SKriva ut objekten
        for (int i = 0; i < objectList.size(); i++) {
            //System.out.println("\n%s. %s\n", i + 1, objectList.get(i));
        }
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
























/*
 public void addExercise(GymMember gymMember, int workoutIndex) {

        do {
            String exerciseName = view.getUserInput(UserInput.InputType.STRING,"Exercise name:");

            /*System.out.println("Exercise name:");
            String exerciseName = input.nextLine();

            System.out.println("Number of reps:");
                    int numberOfReps = getNumberFromUserInput();

                    System.out.println("Number of sets:");
                    int numberOfSets = getNumberFromUserInput();

                    gymMember.getWorkoutList().get(workoutIndex).addExercise(exerciseName, numberOfReps, numberOfSets, selectMuscleGroup());


                    System.out.printf("Workout: \'%s\' currently consist of %s exercises\n", gymMember.getWorkoutList().get(workoutIndex).getWorkoutName(), gymMember.getWorkoutList().get(workoutIndex).getExerciseList().size());
                    System.out.println("1. Add another exercise\n0. Go Back");

                    int menuSelection = getNumberFromUserInput();

                    switch (menuSelection) {
                    case 0:
                    return;
                    }
                    } while (true);
                    }
 */


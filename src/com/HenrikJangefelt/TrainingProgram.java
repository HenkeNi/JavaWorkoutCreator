package com.HenrikJangefelt;

import java.util.*;

// TODO: dum idé att alla metoder tar in GymMember. Ja: onödigt? .Nej: Programmet funkar för att lägga till workouts till andra???

// TODO: egen class för StaffSchedule

// TODO: lägg till funktion för att köra passet?

// TODO: Försök implementera: Interface, mer enums(?), abstrakta metoder, Singleton (design mönster)
// TODO: Förbättra: Jobba mer med att lägga logik i klasser? MVC Och design mönster

// TODO: Lägga till: Settings -> chang your username, ändra/se sitt password/email. Rensa alla arrays. Staff -> Ange veckodagar dem arbetar

// TODO: sätt så mycket som möjligt till private?
// TODO: Final check Rensa klasser som inte används längre, compareTo/comparator kika närmare på

// TODO: Kolla om filen är skrivskyddad??

// TODO: SUPERVIKTIGT KOLLA ATT VÄRDET INTE ÄR NULL FRÅN VIEW!!!!!!
// TODO: Istället för att ta in en gymMember tar den in ett objekt i metoderna!!!!!!!!

// FIX: add exercise i edit, delete

public class TrainingProgram {

    Scanner input = new Scanner(System.in); // TODO Ta bort ich ha bara i view klassen!
    View view = View.getInstance(); // Get instance to View class
    static GymMember currentUser = new GymMember("", ""); // TEST (Static) TODO: (ta bort från konstruktorn i Person???) EJ STATIC!!


    public TrainingProgram() {
        showMainMenu(currentUser);
    }

    // Main menu
    private void showMainMenu(GymMember gymMember) {

        do {
            // Displays main menu by sending correct enumType to the showMenu method in 'View' class.
            view.showMenu(View.MainMenuItem.class, "Main Menu:");

            // Switch on the returned enum case from the View class.
            switch (view.getMenuChoice(View.MainMenuItem.class)) {

                case WORKOUTS:
                    showSubMenu(gymMember, true);
                    break;
                case FRIENDS:
                    showSubMenu(gymMember, false);
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


    // Submenu (menu for workouts or friends)
    public void showSubMenu(GymMember gymMember, boolean isWorkoutSubMenu) {

        String menuType = isWorkoutSubMenu ? "Workout Menu:" : "Friend Menu";

        do {
            view.showMenu(View.SubMenuItem.class, menuType);

            switch (view.getMenuChoice(View.SubMenuItem.class)) {
                case ADD:
                    if (isWorkoutSubMenu) createWorkout(gymMember); else addFriend();
                    //isWorkoutSubMenu ? createWorkout(gymMember) : addFriend();
                    break;
                case EDIT:
                    if (isWorkoutSubMenu) showEditWorkoutMenu(gymMember); else editFriendList(gymMember);
                    break;
                case SHOW:
                    // TODO: fixa
                    if (isWorkoutSubMenu) show(gymMember, gymMember.getWorkoutList(), "Current Workout(s):"); else show(gymMember, gymMember.getFriendList(), "Friends in friend list:");
                    break;
                case SEARCH:
                    //if (isWorkoutSubMenu) search(gymMember.getWorkoutList(), ); else search(); // TODO: Fixa
                    break;
                case SORT:
                    if (isWorkoutSubMenu) sortWorkout(gymMember); else sortFriends(gymMember);
                    break;
                case BACK:
                    return;
            }
        } while (true);
    }





    // TODO: Fixa
    public void showEditWorkoutMenu(GymMember gymMember) {

        do {

            // Show current workouts
            show(gymMember, gymMember.getWorkoutList(), "Current Workout(s):");

            UserInput userInput = new UserInput();

            //gymMember.show(gymMember.getWorkoutList());
            //showWorkouts(gymMember, gymMember.getWorkoutList());

            if (gymMember.getWorkoutList().size() != 0) {
                userInput = view.getUserInput(UserInput.InputType.INT, "\nOptions:\n1. Add exercise\n2. Edit\n3. Delete\n0. Go Back");

                int menuSelection = userInput.intValue;

                switch (menuSelection) {
                    case 0:
                        return;
                    case 1:
                        userInput = view.getUserInput(UserInput.InputType.INT, "Enter the number of the workout you wish to add exercise to");
                        addExercise(gymMember.getWorkoutList().get(userInput.intValue - 1));
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


    public void showEditMenu() {

        do {

        } while (true);
    }



    public void editFriendList(GymMember gymMember) {

        //showFriends(); // Show all friends
        show(gymMember, gymMember.getFriendList(), "Friends in friend list:");

        // If no friends in friendList => return
        if (currentUser.getFriendList().size() == 0) { return; }

        // TODO: Implement friend ID istället???
        UserInput userInput = view.getUserInput(UserInput.InputType.STRING, "Enter the number of the friend you wish to change:");

        int friendIndex = userInput.intValue;

        do {
            // TODO: make enum cases???
            userInput = view.getUserInput(UserInput.InputType.INT, "Options:\n1. Edit First Name\n2. Edit Last Name\n3. Remove Friend\n0. Go Back");
            int menuSelection = userInput.intValue;

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



    // TODO: förbättra (lägg i workout)
    // TODO: Combine with returnWorkoutPrefix??
    public void editWorkout() {

        int[] indexArray = returnWorkoutPrefix("change");
        int workoutNumber = indexArray[0];
        int exerciseNumber = indexArray[1];

        if (exerciseNumber == 0) {
            updateWorkout(currentUser, workoutNumber);
        } else {
            updateExercise(currentUser.getWorkoutList().get(workoutNumber - 1), exerciseNumber);
        }
    }













    









    public void createWorkout(GymMember gymMember) {

        UserInput userInput = view.getUserInput(UserInput.InputType.STRING, "Enter workout's name:");
        gymMember.getWorkoutList().add(WorkoutFactory.createWorkout(userInput.stringValue));

        addExercise(gymMember.getWorkoutList().get(gymMember.getWorkoutList().size() - 1));
    }




    // TODO: behöer inte returnera en workout?!
    public void addExercise(Workout workout) {

        int menuSelection;

        do {
            UserInput userInput = view.getUserInput(UserInput.InputType.STRING, "Exercise name:");
            String exerciseName = userInput.stringValue;

            userInput = view.getUserInput(UserInput.InputType.INT, "Number of reps:");
            int numberOfReps = userInput.intValue;

            userInput = view.getUserInput(UserInput.InputType.INT, "Number of sets:");
            int numberOfSets = userInput.intValue;

            workout.getExerciseList().add(WorkoutFactory.addExercise(exerciseName, numberOfReps, numberOfSets, selectMuscle()));

            userInput = view.getUserInput(UserInput.InputType.INT, "" +
                    "Workout: " +
                    workout.getWorkoutName() +
                    " currently consist of " +
                    workout.getExerciseList().size() +
                    " exercises\n" +
                    "1. Add another exercise\n" +
                    "0. Go Back");

            menuSelection = userInput.intValue;

        } while (menuSelection != 0);

        // TODO: Spara objekt i readMe-File (hämta redan sparade först och sen spara upp igen med den nya?
    }




    public void addFriend() {

        UserInput userInput;

        do {
            userInput = view.getUserInput(UserInput.InputType.STRING, "Add new friend:\nFirst name:");
            String firstName = userInput.stringValue;

            userInput = view.getUserInput(UserInput.InputType.STRING, "Last name:");
            String lastName = userInput.stringValue;

            currentUser.addFriend(firstName, lastName);

            // TODO: Change??
            System.out.println(currentUser.getFriendList().get(currentUser.getFriendList().size() - 1).getFullName() + " was added to your friendlist");

            userInput = view.getUserInput(UserInput.InputType.INT, "Do you want to add another friend?\n1. Add friend\n2. Go Back");

            switch (userInput.intValue) {
                case 2:
                    return;
            }
        } while (true);
    }









    // TODO: Bara kunna ange tal mellan 1-7
    // Choose worked muscle group for exercise
    public Exercise.Muscle selectMuscle() {

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


















    // TODO: ANvänd bara currentUSer???
    // For showing objects in an arrayList
    public <T extends Object> void show(GymMember gymMember, ArrayList<T> arrayList, String listType) {

        UserInput userInput = view.getUserInput(UserInput.InputType.NONE, listType);

        if (arrayList.isEmpty()) { return; }

        int index = 0;
        int subIndex = 0;
        for (T listItem : arrayList) {
            view.showMessage((index + 1) + ". " + listItem.toString());

            if (arrayList.equals(gymMember.getWorkoutList())) {
                for (Exercise exercise : gymMember.getWorkoutList().get(index).getExerciseList()) {
                    view.showMessage("\t" + (index + 1) + "." + (subIndex + 1) + ". " + gymMember.getWorkoutList().get(index).getExerciseList().get(subIndex++));
                }
            }
            index++;
        }
    }



    // Delete workouts or exercises
    public void deleteWorkout(GymMember gymMember) {

        int[] indexArray = returnWorkoutPrefix("delete");
        System.out.println(gymMember.deleteWorkout(indexArray[0], indexArray[1]));
    }



    // TODO: tar in arrayList??? ... generic for deleting object
    public <T extends Object> void delete(ArrayList<T> list, int atPosition) {
        UserInput userInput = new UserInput();
        //userInput = view.getUserInput(UserInput.InputType.INT, "")
        list.remove(atPosition);
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










    public void updateWorkout(GymMember gymMember, int workoutNumber) {

        UserInput userInput = new UserInput();

        do {
            // TODO ENUM?
            userInput = view.getUserInput(UserInput.InputType.INT, "Workout options:\n1. Change workout name\n0. Go Back");

            int menuSelection = userInput.intValue;

            switch (menuSelection) {
                case 0:
                    return;
                case 1:
                    // TODO: GÖr till funktion (eller lägg addNEwExercise i switch case med)
                    userInput = view.getUserInput(UserInput.InputType.STRING, "Enter new workout name:");
                    gymMember.getWorkoutList().get(workoutNumber - 1).setWorkoutName(userInput.stringValue); // Change workout name
                    break;
            }
            view.getUserInput(UserInput.InputType.NONE, "Workout successfully updated!");
        } while (true);
    }


    public void updateExercise(Workout workout, int exerciseIndex) {
        //public void updateExercise(GymMember gymMember, int workoutNumber, int exerciseIndex) {

        UserInput userInput = new UserInput();

        do {
            // TODO: ENUM
            userInput = view.getUserInput(UserInput.InputType.INT, "Exercise options:\n1. Change exercise name\n2. Change amount of reps\n3. Change amount of sets\n4. Change targeted muscle\n0. Go Back\n");
            int menuSelection = userInput.intValue;

            switch (menuSelection) {
                case 0:
                    return;
                case 1:
                    userInput = view.getUserInput(UserInput.InputType.STRING, "Enter new exercise name:");
                    workout.getExerciseList().get(exerciseIndex - 1).setExerciseName(userInput.stringValue);
                    break;
                case 2:
                    userInput = view.getUserInput(UserInput.InputType.INT, "Enter new amount of reps:");
                    workout.getExerciseList().get(exerciseIndex - 1).setNumberOfReps(userInput.intValue);
                    break;
                case 3:
                    userInput = view.getUserInput(UserInput.InputType.INT, "Enter new amount of sets:");
                    workout.getExerciseList().get(exerciseIndex - 1).setNumberOfSets(userInput.intValue);
                    break;
                case 4:
                    view.getUserInput(UserInput.InputType.NONE, "Chose muscle:"); // TODO: fixa
                    workout.getExerciseList().get(exerciseIndex - 1).setTargetedMuscle(selectMuscle());
                    break;
            }
            System.out.println("Exercise successfully updated!");
        } while (true);
    }









    // TODO: Ta också in om sökes efter workout eller friend
    // TODO: Ta in workoutList eller friendList??!!!!
    // TA IN generic lista
        public <T> void search(ArrayList<T> list, String searchedWord) {
        //public void search(GymMember gymMember, String searchedWord) {

        //ArrayList<Workout> matches = gymMember.getSearchedObject(gymMember.getWorkoutList(), userInput.stringValue);
        //ArrayList<Workout> related = gymMember.getRelatedSearchedObject(gymMember.getWorkoutList(), userInput.stringValue);

    }




    public void searchWorkout(GymMember gymMember) {

        UserInput userInput = view.getUserInput(UserInput.InputType.STRING, "Enter the name of the workout you are looking for:");

        ArrayList<Workout> matches = gymMember.getSearchedObject(gymMember.getWorkoutList(), userInput.stringValue);
        ArrayList<Workout> related = gymMember.getRelatedSearchedObject(gymMember.getWorkoutList(), userInput.stringValue);

        /*if (!matches.isEmpty()) {
            System.out.println("Workout(s) Found:");
            gymMember.showWorkouts(matches);
        } else if (!related.isEmpty()) {
            System.out.printf("Workout that contains '%s' found:\n", userInput.stringValue);
            gymMember.showWorkouts(related);
        } else {
            System.out.printf("No workouts matching %s found\n", userInput.stringValue);
        }*/

        String message = "No workouts matching " + userInput.stringValue + " found\n";

        if (!matches.isEmpty()) {
            message = "Workout(s) found";
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





    public void sortMenu(GymMember gymMember) {

        UserInput userInput = new UserInput();

        do {

            userInput = view.getUserInput(UserInput.InputType.INT, "Sort:\n1. Workouts\n2. Exercises\n0. Go Back");

            int sortSelection = userInput.intValue;

            switch (sortSelection) {
                case 0:
                    return;
                case 1:
                    sortWorkout(gymMember);
                    break;
                case 2:
                    show(gymMember, gymMember.getWorkoutList(), "Current Workout(s):");
                    //showWorkouts(gymMember, gymMember.getWorkoutList());

                    userInput = view.getUserInput(UserInput.InputType.INT, "Enter workout prefix for sorting exercises");
                    sortExercises(gymMember, userInput.intValue);
                    show(gymMember, gymMember.getWorkoutList(), "Current Workout(s):");
                    show(gymMember, gymMember.getWorkoutList(), "Current Workout(s):");
                    //showWorkouts(gymMember, gymMember.getWorkoutList());
                    break;
            }
        } while (true);
    }

    // TODO: enum för olika sortering??
    // TODO: lägg sort i edit??
    public void sortWorkout(GymMember gymMember) {

        UserInput userInput = new UserInput();

        userInput = view.getUserInput(UserInput.InputType.INT, "Sort by:\n1. Name\n0. Exit");

        int menuSelection = userInput.intValue;

        switch (menuSelection) {

            case 0:
                return;
            case 1:
                SortWorkoutName sortWorkoutName = new SortWorkoutName();
                Collections.sort(gymMember.getWorkoutList(), sortWorkoutName);
                show(gymMember, gymMember.getWorkoutList(), "Current Workout(s):");
                //showWorkouts(gymMember, gymMember.getWorkoutList());
                break;
        }
    }




    public void sortExercises(GymMember gymMember, int workoutIndex) {

        view.showMenu(View.ExerciseOptions.class, "Sort By:");

        switch (view.getMenuChoice(View.ExerciseOptions.class)) {
            case NAME:
                SortExerciseName sortExerciseName = new SortExerciseName();
                Collections.sort(gymMember.getWorkoutList().get(workoutIndex - 1).getExerciseList(), sortExerciseName);
                break;
            case REPS:
                SortExerciseReps sortExerciseReps = new SortExerciseReps();
                Collections.sort(gymMember.getWorkoutList().get(workoutIndex - 1).getExerciseList(), sortExerciseReps);
                break;
            case SETS:
                SortExerciseSets sortExerciseSets = new SortExerciseSets();
                Collections.sort(gymMember.getWorkoutList().get(workoutIndex - 1).getExerciseList(), sortExerciseSets);
                break;
            case MUSCLE:
                SortExercisesMuscle sortExercisesMuscle = new SortExercisesMuscle();
                Collections.sort(gymMember.getWorkoutList().get(workoutIndex - 1).getExerciseList(), sortExercisesMuscle);
                break;
            case BACK:
                return;
        }
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
    public void searchFriend() {


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

























    // TODO: egen klass?
    // TODO: loop
    public void help() {
        UserInput userInput = view.getUserInput(UserInput.InputType.INT, "Help Menu:\n1. How to create a workout\n2. How to edit a workout\n3. How to search for a workout\n0. Go Back\n");

        int menuSelector = userInput.intValue;

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



// TODO: make a workoutFactory
    /*public void createWorkout(GymMember gymMember) {

        WorkoutFactory workoutFactory = new WorkoutFactory();

        UserInput userInput = view.getUserInput(UserInput.InputType.STRING, "Enter workout's name:");
        gymMember.getWorkoutList().add(workoutFactory.createWorkout(userInput.stringValue));



        //gymMember.addWorkout(userInput.stringValue);

        //String workoutName = view.getUserInput("Enter workout's name");
        //gymMember.addWorkout(workoutName);

        //System.out.println("Enter workout's name:");
        //gymMember.addWorkout(input.nextLine());


        addExercise(gymMember, gymMember.getWorkoutList().size() - 1); // Add new exercise to the last workout in the workoutList


        for (int i = 0; i < gymMember.getWorkoutList().size(); i++) {
            gymMember.getWorkoutList().get(i).showExercises(i);
        }

        ArrayList<Workout> workouts = gymMember.getWorkoutList();

        // TODO: hämta workouts från textfil (om det finns någon), ladda upp igen (plus ny workout)
        //FileUtils.writeGenericObjects("workouts.ser", gymMember.getWorkoutList());
        FileUtils.writeGenericObjects("workouts.ser", workouts);

        //System.out.println(gymMember.getWorkoutList());
        List<Workout> savedWorkouts = FileUtils.readGenericObjects("workouts.ser");
        savedWorkouts.get(0).showExercises(0);
    }*/







    /*public void addExercise(GymMember gymMember, int workoutIndex) {

        do {
            UserInput userInput = view.getUserInput(UserInput.InputType.STRING, "Exercise name:");
            String exerciseName = userInput.stringValue;

            userInput = view.getUserInput(UserInput.InputType.INT, "Number of reps:");
            int numberOfReps = userInput.intValue;

            userInput = view.getUserInput(UserInput.InputType.INT, "Number of sets:");
            int numberOfSets = userInput.intValue;

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
    }*/





       /*public void showWorkouts(GymMember gymMember, ArrayList<Workout> workouts) {

        System.out.println("Current Workouts:");

        if (gymMember.getWorkoutList().isEmpty()) {
            System.out.println("\t -Empty");
            return;
        }
        gymMember.showWorkouts(workouts);
    }*/

    /*public void showFriends() {

        System.out.println("Friends in friend list:");

        // If no friends in gymBuddies
        if (currentUser.getFriendList().isEmpty()) {
            System.out.println("\t-Empty");
            return;
        }
        currentUser.showFriends(currentUser.getFriendList());
    }*/






// Workout menu
    /*private void showWorkoutMenu(GymMember gymMember) {

        do {
            view.showMenu(View.SubMenuItem.class, "Workout Menu:");

            switch (view.getMenuChoice(View.SubMenuItem.class)) {

                case ADD:
                    createWorkout(gymMember);
                    break;
                case EDIT:
                    showEditWorkoutMenu(gymMember);
                    break;
                case SHOW:
                    show(gymMember, gymMember.getWorkoutList(), "Current Workout(s):");
                    //showWorkouts(gymMember, gymMember.getWorkoutList());
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
    }*/


    /*public void showFriendsMenu(GymMember gymMember) {

        do {
            view.showMenu(View.SubMenuItem.class, "Friend Menu:");

            switch (view.getMenuChoice(View.SubMenuItem.class)) {
                case ADD:
                    addFriend();
                    break;
                case EDIT:
                    editFriendList(gymMember);
                    break;
                case SHOW:
                    show(gymMember, gymMember.getFriendList(), "Friends in friend list:");
                    break;
                case SEARCH:
                    searchFriend();
                    break;
                case SORT:
                    sortFriends(currentUser);
                    break;
                case BACK:
                    return;
            }
        } while (true);
    }*/

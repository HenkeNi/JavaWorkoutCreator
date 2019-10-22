package com.HenrikJangefelt;

import com.HenrikJangefelt.person.GymMember;

import java.util.*;


// När man lägger till friends - om man skriver hela namnet samtidigt så behöver man inte skriva efternamn efter det?!


// SORT!!!
// TODO: enum för dem olika sorterings alternativen??
// TODO: lägg sort i edit?? ändra om i menyerna!

// TODO: Skapa gemensam fil för olika enums?


// TODO: lägg till _____ för att skilja av tex: main menu \n ------

// TODO: använd helloPharases.set(0, "Hello") för att ändra workoutList

// TODO: lägg till parantse i meny altenrativ tex: Add (workout)


// TODO: skriv empty om tom vid sökning

// Förändringar:
// TODO: dum idé att alla metoder tar in GymMember. Ja: onödigt? .Nej: Programmet funkar för att lägga till workouts till andra???



// ATT lägga till:


// TODO: egen class för StaffSchedule
// TODO: enum för login??
// TODO: implementera Hashtable
// TODO: Skapa enum class



// TODO: Försök implementera: Interface, mer enums(?), abstrakta metoder, Singleton (design mönster)
// TODO: Förbättra: Jobba mer med att lägga logik i klasser? MVC Och design mönster

// TODO: Lägga till: Settings -> chang your username, ändra/se sitt password/email. Rensa alla arrays. Staff -> Ange veckodagar dem arbetar

// TODO: sätt så mycket som möjligt till private?
// TODO: Final check Rensa klasser som inte används längre, compareTo/comparator kika närmare på

// TODO: Kolla om filen är skrivskyddad??

// TODO: SUPERVIKTIGT KOLLA ATT VÄRDET INTE ÄR NULL FRÅN VIEW!!!!!!
// TODO: Istället för att ta in en gymMember tar den in ett objekt i metoderna!!!!!!!!

// TODO: create new user deletes all worouts in file....

/*
Filhantering:
Det ska gå att spara en lista av objekt.
Efter att programmet startats om ska man kunna läsa in denna lista av objekt igen.
Det ska finnas try-catch felhantering på detta, dvs programmet ska inte krascha om filen inte finns när man försöker läsa den, eller om den är skrivskyddad när man försöker spara.
 */

// TODO: readin help från fil

// TODO: enum meny för edit (kolla om man kan sätta fler värden på enum casen och sen sortera ut vissa av dem till dem olika menyerna )

// TODO: spara både vid skapande vid ny workout && updating av wokrut/eercsie && add exer till existernade workout gör till en fuktion? Att skicka
// // TODO: upp och hämta ner objekt?? Eller räcker med den i file utils?

// TODO: friendlist en array av Person, kan lägga till både friends och gymStaff?


// TODO: änra gymMember parametrarna till tex. ArrayList<GymMember> istället

// TODO: SAKER SOM INTE LÄNGRE FUNGERAR!!!! SHOW, SORT OCH EDIT

// Saker att implementera (bonus )!
// lägg till funktion för att köra passet?



// FIX: add exercise i edit, delete
// FIX: input två ggr vid ange muscle worked
// FIX: edit crash


// TODO: WORKOUT Ska sparas varje gång man lägger till en övning samt varje gång man ändrar en övning/wokrout

public class TrainingProgram {

    View view = View.getInstance(); // Get instance to View class
    static GymMember currentUser = new GymMember("", ""); // TEST (Static) TODO: (ta bort från konstruktorn i Person???) EJ STATIC!!
    // TODO: Ta bort static och skicka gymMEmber till login (först TrainningProgrm sen öppnas login)



    public TrainingProgram() {
        showMainMenu(currentUser);
    }


    // Main menu
    private void showMainMenu(GymMember gymMember) {

        do {
            //fetchWorkouts(); // TODO: Lägga den här??+

            // Displays main menu by sending correct enumType to the showMenu method in 'View' class.
            view.showMenu(View.MainMenuItem.class, "Main Menu");

            // Switch on the returned enum case from the View class.
            switch (view.getMenuItem(View.MainMenuItem.class)) {

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
                    showHelpMenu();
                    break;
                case EXIT:
                    return;
            }
        } while (true);
    }


    // Submenu (menu for workouts or friends)
    private void showSubMenu(GymMember gymMember, boolean isWorkoutSubMenu) {

        String menuType = isWorkoutSubMenu ? "Workout Menu" : "Friend Menu";

        do {
            view.showMenu(View.SubMenuItem.class, menuType);

            switch (view.getMenuItem(View.SubMenuItem.class)) {
                case ADD:
                    if (isWorkoutSubMenu) addWorkout(gymMember);
                    else addFriend(gymMember);
                    //isWorkoutSubMenu ? createWorkout(gymMember) : addFriend();
                    break;
                case EDIT:
                    if (isWorkoutSubMenu) showEditWorkoutMenu(gymMember);
                    else editFriendList(gymMember, gymMember.getFriendList());
                    break;
                case SHOW:
                    // TODO: fixa
                    if (isWorkoutSubMenu) show(gymMember, gymMember.getWorkoutList(), "Current Workout(s):");
                    else show(gymMember, gymMember.getFriendList(), "Friends in friend list:");
                    break;
                case SEARCH:
                    if (isWorkoutSubMenu) search(gymMember, gymMember.getWorkoutList());
                    else search(gymMember, gymMember.getFriendList());
                    break;
                case SORT:
                    if (isWorkoutSubMenu) sortMenu(gymMember);
                    else sortFriends(gymMember);
                    break;
                case BACK:
                    return;
            }
        } while (true);
    }





    // Lägg allt i workout factory class
    private void addWorkout(GymMember gymMember) {

        String workoutName = view.getUserInput(UserInput.InputType.STRING, "Enter workout's name:").stringValue; // Get workout name
        gymMember.getWorkoutList().add(WorkoutFactory.createWorkout(workoutName)); // Create new workout (and add to workoutList)

        addExercise(gymMember.getWorkoutList().get(gymMember.getWorkoutList().size() - 1)); // Add exercises to workout

        // TODO: Bättre med mer readabiliy??
        //Workout newWorkout = WorkoutFactory.createWorkout(workoutName);
        //gymMember.getWorkoutList().add(newWorkout);

    }


    private void addExercise(Workout workout) {

        int menuChoice;

        do {
            String exerciseName = view.getUserInput(UserInput.InputType.STRING, "Exercise name").stringValue;
            int numberOfReps = view.getUserInput(UserInput.InputType.INT, "Number of reps:").intValue;
            int numberOfSets = view.getUserInput(UserInput.InputType.INT, "Number of sets:").intValue;

            workout.getExerciseList().add(WorkoutFactory.createExercise(exerciseName, numberOfReps, numberOfSets, getMuscleGroup()));

            menuChoice = view.getUserInput(UserInput.InputType.INT, "" +
                    "Workout: " + workout.getWorkoutName() +
                    " currently consist of " + workout.getExerciseList().size() + " exercises\n" +
                    "1. Add another exercise\n" +
                    "0. Go Back").intValue;

        } while (menuChoice != 0);

        saveWorkouts();
    }




    private void addFriend(GymMember gymMember) {

        int menuChoice;

        do {
            String firstName = view.getUserInput(UserInput.InputType.STRING, "Add new friend.\nFirst name:").stringValue;
            String lastName = view.getUserInput(UserInput.InputType.STRING, "Last name:").stringValue;

            gymMember.addFriend(firstName, lastName);

            // TODO: Add in view displayMessage
            view.getUserInput(UserInput.InputType.NONE, gymMember.getFriendList().get(gymMember.getFriendList().size() - 1).getFullName() + " was added to your friend list\n");

            menuChoice = view.getUserInput(UserInput.InputType.INT, "Do you want to add another friend?\n1. Add friend\n2. Go Back").intValue;

        } while (menuChoice != 2);
    }





    // TODO: lägg i factory: Lägg i workout???? kanske dum ide
    // TODO: Bara kunna ange tal mellan 1-7
    // TODO: Inte en funktion
    // Choose worked muscle group for exercise
    private Exercise.Muscle getMuscleGroup() {

        view.showMenu(Exercise.Muscle.class, "Choose targeted muscle for exercise:");
        return view.getMenuItem(Exercise.Muscle.class);
    }







    private void saveWorkouts() {
        // TODO: Spara objekt i readMe-File (hämta redan sparade först och sen spara upp igen med den nya?
        // TODO: hämta workouts när programmet laddas (räcker det??)

        FileUtils.writeGenericObjects("workouts.ser", currentUser.getWorkoutList()); // Save current workouts to file

    }

    private void fetchWorkouts() {

        // TODO: gör så att workoutlist rensas innan det laddas ner från fil (ej dubbleter)
        ArrayList<Workout> fetchedWorkouts = FileUtils.readGenericObjects("workouts.ser");

        if (fetchedWorkouts == null) { return; }

        currentUser.getWorkoutList().clear(); // Remove all data from arrayList (prevent duplicates)

        for (Workout workout : fetchedWorkouts) {
            currentUser.getWorkoutList().add(workout);
        }




        // Append the downloaded data
        /*for (Workout workout : fetchedWorkouts) {
            if (workout != null) {
            }
        }*/

        if (currentUser.getWorkoutList() == null) {
            System.out.println("TOM");
        } else {
            for (Workout workout : currentUser.getWorkoutList()) {
                System.out.println(workout);
            }
        }

    }








    private void updateWorkout(Workout workout) {

        int menuChoice;

        //UserInput userInput;

        do {
            // TODO ENUM?
            menuChoice = view.getUserInput(UserInput.InputType.INT, "Workout options:\n1. Change workout name\n0. Go Back").intValue;

            switch (menuChoice) {
                case 0:
                    return;
                case 1:
                    workout.setWorkoutName(view.getUserInput(UserInput.InputType.STRING, "Enter new workout name:").stringValue); // Change workout name
                    break;
            }
            view.getUserInput(UserInput.InputType.NONE, "Workout successfully updated!");
        } while (true);
    }








    private void updateExercise(Exercise exercise) {

        do {
            view.showMenu(View.ExerciseOptions.class, "Edit Exercise Options");

            switch (view.getMenuItem(View.ExerciseOptions.class)) {
                case BACK:
                    return;
                case NAME:
                    exercise.setExerciseName(view.getUserInput(UserInput.InputType.STRING, "Enter new exercise name:").stringValue);
                    break;
                case REPS:
                    exercise.setNumberOfReps(view.getUserInput(UserInput.InputType.INT, "Enter new amount of reps:").intValue);
                    break;
                case SETS:
                    exercise.setNumberOfSets(view.getUserInput(UserInput.InputType.INT, "Enter new amount of sets:").intValue);
                    break;
                case MUSCLE:
                    exercise.setTargetedMuscle(getMuscleGroup());
                    break;
            }
            view.getUserInput(UserInput.InputType.NONE, "Exercise successfully updated!");
        } while (true);
    }
























    // TODO: Fixa
    // Did press edit workout
    private void showEditWorkoutMenu(GymMember gymMember) {

        UserInput userInput = new UserInput();

        do {
            show(gymMember, gymMember.getWorkoutList(), "Current Workout(s):"); // Show current workouts

            if (gymMember.getWorkoutList().isEmpty()) { return; } // TODO: BEHÖVS??

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
                    editWorkout(gymMember); // Change name of workout/exercise, amount of reps, etc.
                    break;
                case 3:
                    deleteWorkout(currentUser); // Delete workout or exercise
                    break;
            }
        } while (true);
    }


    private void showEditMenu() {

        do {

        } while (true);
    }


    private void editFriendList(GymMember gymMember, ArrayList<GymMember> friendList) {

        show(gymMember, friendList, "Friends in friend list:");

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
                case 1:
                    userInput = view.getUserInput(UserInput.InputType.STRING, "Enter new first name:");
                    gymMember.getFriendList().get(friendIndex - 1).setFirstName(userInput.stringValue.trim());
                    break;
                case 2:
                    userInput = view.getUserInput(UserInput.InputType.STRING, "Enter new last name:");
                    gymMember.getFriendList().get(friendIndex - 1).setLastName(userInput.stringValue.trim());
                    break;
                case 3:
                    gymMember.deleteFriend(friendIndex - 1);
                    return;
            }
        } while (userInput.intValue != 0);
    }



























    // TODO: COmbine med showWorkout i View????
    // TODO: Fixa!!!
    // TODO: ANvänd bara currentUSer???
    // For showing objects in an arrayList
    private  <T extends Object> void show(GymMember gymMember, ArrayList<T> arrayList, String listType) {

        view.getUserInput(UserInput.InputType.NONE, listType); // Print out menu type

        if (arrayList.isEmpty()) {
            view.getUserInput(UserInput.InputType.NONE,  "\t -Empty");
            return;
        }

        T classType = arrayList.get(0); // Get the Class type from the first element in the arrayList
        int index = 0;


        for (T listItem : arrayList) {
            view.showMessage((index + 1) + ". " + listItem.toString()); // Shows number before workout and the workout itself

            int subIndex = 0;


            /*if (arrayList.get(0) instanceof Workout) {
                fetchWorkouts();
            }*/

            if (classType instanceof Workout) {


                for (Exercise exercise : gymMember.getWorkoutList().get(index).getExerciseList()) {
                    view.showMessage("\t" + (index + 1) + "." + (subIndex + 1) + ". " + gymMember.getWorkoutList().get(index).getExerciseList().get(subIndex));
                    subIndex++;
                }
            }

            // TOOD: FUnkar? Eller gör på annat sätt (skriva ut email under varje friend
            if (classType instanceof GymMember) {
                //for (GymMember gymMember1 : gymMember.getFriendList().get())
            }


            index++;
        }
    }














    // Delete workouts or exercises
    private void deleteWorkout(GymMember gymMember) {

        int[] indexArray = view.getWorkoutNumberPrefix("delete");
        System.out.println(gymMember.deleteWorkout(indexArray[0], indexArray[1]));
    }


    // TODO: tar in arrayList??? ... generic for deleting object
    private  <T extends Object> void delete(ArrayList<T> list, int atPosition) {
        UserInput userInput = new UserInput();
        //userInput = view.getUserInput(UserInput.InputType.INT, "")
        list.remove(atPosition);
    }





























    // TODO: Kolla istället vilket objekt med instanceOf
    // TODO: Ta också in om sökes efter workout eller friend
    // TODO: Ta in workoutList eller friendList??!!!!

    //private  <T extends Object> void search(ArrayList<T> list, boolean isWorkout, String searchedWord) {
    private  <T extends Object> void search (GymMember gymMember, ArrayList<T> list) {

        // Checks if the first element in ArrayList is of type Workout. If true; assign first string value to variable objectSort
        String objectSort = list.get(0) instanceof Workout ? "Workout" : "Friend";

        String searchedWord = view.getUserInput(UserInput.InputType.STRING, "Enter the name of the " + objectSort + " you're looking for:").stringValue;


        ArrayList<T> matches = gymMember.getSearchedObject(list, searchedWord);
        ArrayList<T> related = gymMember.getRelatedSearchedObject(list, searchedWord);

        if (!matches.isEmpty()) {
            show(gymMember, matches, objectSort + "(s) found:");
        } else if (!related.isEmpty()) {
            show(gymMember, related, objectSort + "(s) that contains '" + searchedWord + "' found:");
        } else {
            view.getUserInput(UserInput.InputType.NONE, "No " + objectSort + "(s) matching '" + searchedWord + "' found:");
        }
    }









    // TODO: förbättra (lägg i workout)
    // TODO: Combine with returnWorkoutPrefix??

    // TODO: ta in två nummer istället?
    private void editWorkout(GymMember gymMember) {

        int[] indexArray = view.getWorkoutNumberPrefix("change");
        int workoutNumber = indexArray[0];
        int exerciseNumber = indexArray[1];

        if (exerciseNumber == 0) {
            updateWorkout(gymMember.getWorkoutList().get(workoutNumber - 1));
        } else {
            updateExercise(gymMember.getWorkoutList().get(workoutNumber - 1).getExerciseList().get(exerciseNumber - 1));
        }
    }









    // TODO: Lägg till sort Firends här???
    private void sortMenu(GymMember gymMember) {

        int menuChoice;

        do {
            menuChoice = view.getUserInput(UserInput.InputType.INT, "Sort:\n1. Workouts\n2. Exercises\n0. Go Back").intValue;

            show(gymMember, gymMember.getWorkoutList(), "Current Workout(s):");

            switch (menuChoice) {
                case 0:
                    return;
                case 1:
                    sortWorkout(gymMember);
                    break;
                case 2:
                    sortExercises(gymMember.getWorkoutList());
                    break;
            }
            show(gymMember, gymMember.getWorkoutList(), "Current Workout(s):");
        } while (true);
    }

    private void sortFriends(GymMember gymMember) {

        int menuChoice = view.getUserInput(UserInput.InputType.INT, "Sort by:\n1. First name\n2. Last name\n0. Go Back").intValue;

        switch (menuChoice) {
            case 0:
                return;
            case 1:
                Collections.sort(gymMember.getFriendList(), new SortFriend.SortFriendFirstName());
                break;
            case 2:
                Collections.sort(gymMember.getFriendList(), new SortFriend.SortFriendLastName());
                break;
        }
        show(gymMember, gymMember.getFriendList(), "Current Friends in list:");
        //gymMember.showFriends(gymMember.getFriendList());
    }


    private void sortWorkout(GymMember gymMember) {

        int menuChoice = view.getUserInput(UserInput.InputType.INT, "Sort by:\n1. Workout name\n2. Number of exercises\n0. Exit").intValue;

        switch (menuChoice) {

            case 0:
                return;
            case 1:
                Collections.sort(gymMember.getWorkoutList(), new SortWorkout.SortWorkoutName());
                break;
            case 2:
                Collections.sort(gymMember.getWorkoutList(), new SortWorkout.SortWorkoutExercises());
                break;
        }
    }


    private void sortExercises(ArrayList<Workout> workouts) {

        view.showMenu(View.ExerciseOptions.class, "Sort By");

        for (Workout workout : workouts) {

            switch (view.getMenuItem(View.ExerciseOptions.class)) {
                case NAME:
                    Collections.sort(workout.getExerciseList(), new SortExercise.SortExerciseName());
                    break;
                case REPS:
                    Collections.sort(workout.getExerciseList(), new SortExercise.SortExerciseReps());
                    break;
                case SETS:
                    Collections.sort(workout.getExerciseList(), new SortExercise.SortExerciseSets());
                    break;
                case MUSCLE:
                    Collections.sort(workout.getExerciseList(), new SortExercise.SortExercisesMuscle());
                    break;
                case BACK:
                    return;
            }
        }
    }



































    // TODO: fixa search i helpMe.txt samt lägg till annat...
    private void showHelpMenu() {

        do {
            int menuChoice = view.getUserInput(UserInput.InputType.INT, "Help Menu:\n1. How to create a workout\n2. How to edit a workout\n3. How to search for a workout\n0. Go Back\n").intValue;

            switch (menuChoice) {
                case 0:
                    return;
                case 1:
                    getHelpMessage("Section1", "Section2");
                    break;
                case 2:
                    getHelpMessage("Section2", "Section3");
                    break;
                case 3:
                    getHelpMessage("Section3", "Section4");
                    break;
            }
        } while (true);
    }


    private void getHelpMessage(String readFrom, String readTo) {

        boolean atReadStartPosition = false;
        ArrayList<String> lines = FileUtils.readAllLines("helpMe.txt"); // Fetch arrayList of Strings from FileUtils

        for (String line : lines) {

            if (line.contains(readFrom)) {
                atReadStartPosition = true;
                continue;
            }

            if (atReadStartPosition) {
                if (line.contains(readTo)) { return; }
                view.getUserInput(UserInput.InputType.NONE, line);
            }
        }
    }


    // TODO: if no staff. skriv ingen oersonal. Samt tid när deet är bamanat? alt. när nästashift börjar?
    private void checkAvailableStaff() {

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


}

































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


// TODO: Lägg i view
    /*private int[] returnWorkoutPrefix(String editOrDelete) {

        boolean validNumbers = false;
        int workoutIndex = 0;
        int exerciseIndex = 0;

        do {
            UserInput userInput = view.getUserInput(UserInput.InputType.STRING, "Enter the prefix-number of the workout or exercise you want to " + editOrDelete);
            String input = userInput.stringValue.replace(".", "").trim();
            try {
                //workoutIndex = Character.getNumericValue(userInput.charAt(0));
                workoutIndex = Integer.parseInt(input.substring(0, 1));
                if (input.length() >= 2) {
                    exerciseIndex = Integer.parseInt(input.substring(1));
                }
                validNumbers = true;
            } catch (Exception e) {
                System.out.println("Must enter a valid number prefix.");
            }
        } while (!validNumbers);

        int[] prefixArray = {workoutIndex, exerciseIndex};
        return prefixArray;
    }*/




     /*private void searchWorkout(GymMember gymMember) {

        UserInput userInput = view.getUserInput(UserInput.InputType.STRING, "Enter the name of the workout you are looking for:");

        ArrayList<Workout> matches = gymMember.getSearchedObject(gymMember.getWorkoutList(), userInput.stringValue);
        ArrayList<Workout> related = gymMember.getRelatedSearchedObject(gymMember.getWorkoutList(), userInput.stringValue);

        if (!matches.isEmpty()) {
            System.out.println("Workout(s) Found:");
            gymMember.showWorkouts(matches);
        } else if (!related.isEmpty()) {
            System.out.printf("Workout that contains '%s' found:\n", userInput.stringValue);
            gymMember.showWorkouts(related);
        } else {
            System.out.printf("No workouts matching %s found\n", userInput.stringValue);
        }

        String message = "No workouts matching " + userInput.stringValue + " found\n";

        if (!matches.isEmpty()) {
            message = "Workout(s) found";
        }
    }*/

    /*private void searchFriend(GymMember gymMember) {


        UserInput userInput = view.getUserInput(UserInput.InputType.STRING, "Enter name of the friend you are looking for:");

        ArrayList<GymMember> matchingFriends = currentUser.getSearchedFriend(userInput.stringValue);
        ArrayList<GymMember> relatedFriends = currentUser.getRelatedSearchedFriend(userInput.stringValue);

        if (!matchingFriends.isEmpty()) {
            //view.getUserInput(UserInput.InputType.NONE, "Friend(s) Found:");
            show(gymMember, gymMember.getFriendList(), "Friend(s) Found:");
            //currentUser.showFriends(matchingFriends);

        } else if (!relatedFriends.isEmpty()) {
            //view.getUserInput(UserInput.InputType.NONE, "Friend(s) that contains " + userInput.stringValue + " found:");
            show(gymMember, gymMember.getFriendList(), "Friend(s) that contains " + userInput.stringValue + " found:");
            //currentUser.showFriends(relatedFriends);
        } else {
            view.getUserInput(UserInput.InputType.NONE, "Friend(s) that contains " + userInput.stringValue + " found:");
        }
    }*/



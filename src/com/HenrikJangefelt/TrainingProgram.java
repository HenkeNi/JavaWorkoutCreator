package com.HenrikJangefelt;

import com.HenrikJangefelt.person.GymMember;

import java.io.File;
import java.util.*;


// ATT GÖRA FÖRST!!!:


    // TODO: BUGG!! SORT EXERCISES. måste ange Sort by: 1 1 1 1 många ggr för den ska registrera det!



    // Inläsning från fil:
        // TODO: fixa inläsning m.m från filer... Spara när det behövs och ladda in (bara vid start?)

        // TODO: Kolla om filen är skrivskyddad??
        // TODO: Gör helpMe filen skrivskyddad?

    // TEST FIL...

    // Try and Catch
        // TODO: lägg till mer try and catch (kolla efter null).. gör metoderna säkrare!! istället för return vid null?!
        // TODO: SUPERVIKTIGT KOLLA ATT VÄRDET INTE ÄR NULL FRÅN VIEW!!!!!! Samt att det är giltig range för numbers


    // Settings
        // TODO: Lägga till: Settings -> chang your username, ändra/se sitt password/email. Rensa alla arrays. Staff -> Ange veckodagar dem arbetar


    // Clean up
        // TODO: testa efter krascher (ange inte nummer, eller nummer utanför range osv...)
        // TODO: Final check Rensa klasser som inte används längre, compareTo/comparator kika närmare på


    // TODO: kolla Christaians show (generic) metod!!!

// TODO: dum idé att alla metoder tar in GymMember. Ja: onödigt? .Nej: Programmet funkar för att lägga till workouts till andra???




// TODO: friendlist en array av Person, kan lägga till både friends och gymStaff?


// FIX: add exercise i edit, delete
// FIX: input två ggr vid ange muscle worked
// FIX: edit crash
// TODO: WORKOUT Ska sparas varje gång man lägger till en övning samt varje gång man ändrar en övning/wokrout



// TODO: Istället för att ta in en gymMember tar den in ett objekt i metoderna!!!!!!!!
// TODO: create new user deletes all worouts in file....



/*
Filhantering:
Det ska gå att spara en lista av objekt.
Efter att programmet startats om ska man kunna läsa in denna lista av objekt igen.
Det ska finnas try-catch felhantering på detta, dvs programmet ska inte krascha om filen inte finns när man försöker läsa den, eller om den är skrivskyddad när man försöker spara.
 */


// BONUS:

// TODO: enum för dem olika sorterings alternativen??
// TODO: Skapa gemensam fil för olika enums?
// TODO: lägg till _____ / **** för att skilja av olika menyer inout osv. tex: main menu \n ------
// TODO: implementera Hashtable
// TODO: Skapa enum class

// TODO: använd cast och instanceOf i generic funktionerna!!!
// TODO: WOrkout duration
// TODO: egen class för StaffSchedule
// TODO: enum för login??

// KANSKE:
// TODO: lägg till parantse i meny altenrativ tex: Add (workout)
// TODO: enum meny för edit (kolla om man kan sätta fler värden på enum casen och sen sortera ut vissa av dem till dem olika menyerna )
// TODO: sätt så mycket som möjligt till private?



// TODO: använd helloPharases.set(0, "Hello") för att ändra workoutList



// TODO: Försök implementera: Interface, mer enums(?), abstrakta metoder, Singleton (design mönster)
// TODO: Förbättra: Jobba mer med att lägga logik i klasser? MVC Och design mönster


// TODO: änra gymMember parametrarna till tex. ArrayList<GymMember> istället





public class TrainingProgram {

    View view = View.getInstance(); // Get instance to View class
    static GymMember currentUser = new GymMember("", ""); // TEST (Static) TODO: (ta bort från konstruktorn i Person???) EJ STATIC!!
    // TODO: Ta bort static och skicka gymMEmber till login (först TrainningProgrm sen öppnas login)


    public TrainingProgram() {

        fetchWorkouts();
        showMainMenu(currentUser);
    }


    // Main menu
    public void showMainMenu(GymMember gymMember) {

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
    public void showSubMenu(GymMember gymMember, boolean isWorkoutSubMenu) {

        String menuType = isWorkoutSubMenu ? "Workout Menu" : "Friend Menu";

        do {
            view.showMenu(View.SubMenuItem.class, menuType);

            switch (view.getMenuItem(View.SubMenuItem.class)) {
                case ADD:
                    if (isWorkoutSubMenu) addWorkout(gymMember);
                    else addFriend(gymMember);
                    break;
                case EDIT:
                    if (isWorkoutSubMenu) editWorkouts(gymMember);
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
                case REMOVE:
                    if (isWorkoutSubMenu) delete(currentUser, currentUser.getWorkoutList(), "delete");
                    else delete(currentUser, currentUser.getFriendList(), "remove friend");
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
    public void addWorkout(GymMember gymMember) {

        String workoutName = view.getUserInput(UserInput.InputType.STRING, "Enter workout's name:").stringValue; // Get workout name
        gymMember.getWorkoutList().add(WorkoutFactory.createWorkout(workoutName)); // Create new workout (and add to workoutList)

        addExercise(gymMember.getWorkoutList().get(gymMember.getWorkoutList().size() - 1)); // Add exercises to workout

        // TODO: Bättre med mer readabiliy??
        //Workout newWorkout = WorkoutFactory.createWorkout(workoutName);
        //gymMember.getWorkoutList().add(newWorkout);

    }


    public void addExercise(Workout workout) {

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


    public void addFriend(GymMember gymMember) {

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
    public Exercise.Muscle getMuscleGroup() {

        view.showMenu(Exercise.Muscle.class, "Choose targeted muscle for exercise:");
        return view.getMenuItem(Exercise.Muscle.class);
    }


    public void saveWorkouts() {
        // TODO: Spara objekt i readMe-File (hämta redan sparade först och sen spara upp igen med den nya?
        // TODO: hämta workouts när programmet laddas (räcker det??)

        FileUtils.writeGenericObjects("workouts.ser", currentUser.getWorkoutList()); // Save current workouts to file

    }

    public void fetchWorkouts() {


        //ArrayList<Workout> downloadedWorkouts;

            /*if (FileUtils.readGenericObjects("workouts.ser") == null &&
                FileUtils.readGenericObjects("workouts.ser").get(0) instanceof Workout) {

            System.out.println("Inga workouts att hämta!");
            return;
        }*/

        //currentUser.getWorkoutList().add(FileUtils.readGenericObjects("workouts.ser"));

        //downloadedWorkouts = FileUtils.readGenericObjects("workouts.ser");

        //System.out.println(downloadedWorkouts.size());

        /*for (Workout workout : downloadedWorkouts) {
            currentUser.getWorkoutList().add(workout);
            System.out.println(workout);
        }*/



        // Append the downloaded data
        /*for (Workout workout : fetchedWorkouts) {
            if (workout != null) {
            }
        }*/

        /*if (currentUser.getWorkoutList() == null) {
            System.out.println("TOM");
        } else {
            for (Workout workout : currentUser.getWorkoutList()) {
                System.out.println(workout);
            }
        }*/





        ArrayList<Workout> downloadedWorkouts = FileUtils.readGenericObjects("workouts.ser");

        if (downloadedWorkouts != null) {

            for (Workout workout : downloadedWorkouts) {

                // TODO:  Beövs instance of WOrkout ?????
                //if (workout instanceof Workout) {
                    currentUser.getWorkoutList().add(workout);
                    System.out.println(workout);

                //}
            }
        }
    }






























    /*
    private void showEditWorkoutMenu(GymMember gymMember) {

        do {
            show(gymMember, gymMember.getWorkoutList(), "Current Workout(s):"); // Show current workouts

            //int[] workoutPrefix

            if (gymMember.getWorkoutList().isEmpty()) { return; } // TODO: BEHÖVS??

            int menuChoice = view.getUserInput(UserInput.InputType.INT, "\nOptions:\n1. Add exercise\n2. Edit\n3. Delete\n0. Go Back").intValue;

            switch (menuChoice) {
                case 0:
                    return;
                case 1:
                    int workoutIndex = view.getUserInput(UserInput.InputType.INT, "Enter the number of the workout you wish to add exercise to").intValue;
                    addExercise(gymMember.getWorkoutList().get(workoutIndex - 1));
                    break;
                case 2:
                    editWorkout(gymMember); // Change name of workout/exercise, amount of reps, etc.
                    break;
                case 3:
                    //deleteWorkout(currentUser); // Delete workout or exercise
                    delete(currentUser, currentUser.getWorkoutList(), "Workout");
                    break;
            }
        } while (true);
    }
     */


    // TODO: förbättra (lägg i workout)
    // TODO: Combine with returnWorkoutPrefix??

    // TODO: ta in två nummer istället?
    /*private void editWorkout(GymMember gymMember) {

        int[] indexArray = view.getListNumberPrefix("change");
        int workoutNumber = indexArray[0];
        int exerciseNumber = indexArray[1];

        if (exerciseNumber == 0) {
            updateWorkout(gymMember.getWorkoutList().get(workoutNumber - 1));
        } else {
            updateExercise(gymMember.getWorkoutList().get(workoutNumber - 1).getExerciseList().get(exerciseNumber - 1));
        }
    }*/

    /*
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
     */


    public void showEditMenu() {

        do {

        } while (true);
    }





    // TODO: Fixa
    // Did press edit workout
    public void editWorkouts(GymMember gymMember) {

        show(gymMember, gymMember.getWorkoutList(), "Current Workout(s):"); // Show current workouts
        if (gymMember.getWorkoutList().isEmpty()) {
            return;
        }

        int[] workoutPrefix = view.getListNumberPrefix("edit");

        if (workoutPrefix[1] == 0) {
            updateWorkout(gymMember.getWorkoutList().get(workoutPrefix[0] - 1));
        } else {
            updateExercise(workoutPrefix[0] - 1,gymMember.getWorkoutList().get(workoutPrefix[0] - 1).getExerciseList().get(workoutPrefix[1] - 1));
        }
    }


    public void updateWorkout(Workout workout) {

        int menuChoice;

        do {
            // TODO ENUM?
            menuChoice = view.getUserInput(UserInput.InputType.INT, "Workout options:\n1. Add exercise\n2. Change workout name\n0. Go Back").intValue;

            switch (menuChoice) {
                case 0:
                    saveWorkouts();
                    return;
                case 1:
                    addExercise(workout);
                    break;
                case 2:
                    workout.setWorkoutName(view.getUserInput(UserInput.InputType.STRING, "Enter new workout name:").stringValue); // Change workout name
                    break;
            }
            view.getUserInput(UserInput.InputType.NONE, "Workout successfully updated!");
        } while (true);
    }

    public void updateExercise(int workoutIndex, Exercise exercise) {

        do {
            view.showMenu(View.EditExercise.class, "Exercise Options");

            switch (view.getMenuItem(View.EditExercise.class)) {
                case EDIT_NAME:
                    exercise.setExerciseName(view.getUserInput(UserInput.InputType.STRING, "Enter new exercise name:").stringValue);
                    break;
                case EDIT_REPS:
                    exercise.setNumberOfReps(view.getUserInput(UserInput.InputType.INT, "Enter new amount of reps:").intValue);
                    break;
                case EDIT_SETS:
                    exercise.setNumberOfSets(view.getUserInput(UserInput.InputType.INT, "Enter new amount of sets:").intValue);
                    break;
                case EDIT_MUSCLE:
                    exercise.setTargetedMuscle(getMuscleGroup());
                    break;
                case GO_BACK:
                    saveWorkouts();
                    return;
            }
            view.getUserInput(UserInput.InputType.NONE, "Exercise successfully updated!");
            saveWorkouts();
        } while (true);
    }




    public void editFriendList(GymMember gymMember, ArrayList<GymMember> friendList) {

        show(gymMember, friendList, "Friends in friend list:");

        // If no friends in friendList => return
        if (currentUser.getFriendList().size() == 0) {
            return;
        }

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
                    //delete(currentUser, currentUser.getFriendList(), "Friend");
                    //gymMember.deleteFriend(friendIndex - 1);
                    gymMember.getFriendList().remove(friendIndex - 1);
                    return;
            }
        } while (userInput.intValue != 0);
    }


    // TODO: gör mer generic
    public  <T extends Object> void delete(GymMember gymMember, ArrayList<T> list, String listType) {

        show(gymMember, list, listType);

        int[] indexArray = view.getListNumberPrefix("delete");

        /*boolean isWorkout = list.get(0) instanceof Workout ? true : false;

        if (isWorkout && indexArray[1] == 0) {

        } else {
            if (isWorkout) {
                gymMember.getWorkoutList().remove(indexArray[0] - 1);
            } else {

            }

        }*/

        // Remove friend from list
        if (list.get(0) instanceof GymMember) {
            gymMember.getFriendList().remove(indexArray[0] - 1);
        }

        // Remove whole workout
        if (list.get(0) instanceof Workout) {

            if (indexArray[1] == 0) {
                Workout deletedWorkout = gymMember.getWorkoutList().remove(indexArray[0] - 1);
                view.getUserInput(UserInput.InputType.NONE, "Workout " + deletedWorkout + " was deleted"); // TODO: ta bort?
            } else {

                // If exercise is the last in workout, then delete the whole workout
                if (gymMember.getWorkoutList().get(indexArray[0] - 1).getExerciseList().size() <= 1) {
                    Workout deletedWorkout = gymMember.getWorkoutList().remove(indexArray[0] - 1);

                } else {
                    Exercise deletedExercise = gymMember.getWorkoutList().get(indexArray[0] - 1).getExerciseList().remove(indexArray[1] - 1);
                    view.getUserInput(UserInput.InputType.NONE, "Exercise " + deletedExercise + " was deleted!");
                }
            }
            saveWorkouts();
        }
    }


    // TODO: COmbine med showWorkout i View????
    // TODO: KOlla upp om man kan kolla hela arrayLista (arrayList instanceOf ArrayList<Workout>
    // TODO: ANvänd bara currentUSer???
    // Java doc , beskrivning utanför, kommentera vanlig inuti
    /**
     * Takes in any ArrayList of object and show each element
     * @param gymMember   User
     * @param arrayList   ArrayList of any object
     * @param listType    String
     * @param <T>
     */
    public  <T extends Object> void show(GymMember gymMember, ArrayList<T> arrayList, String listType) {

        view.getUserInput(UserInput.InputType.NONE, listType); // Print out menu type

        if (arrayList.isEmpty()) {
            view.getUserInput(UserInput.InputType.NONE, "\t -Empty");
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

            // TODO: Lägg till ---------- eler nåt för att skilja olikw workouts/ friends åt
            index++;
        }
    }


    public  <T extends Object> void search(GymMember gymMember, ArrayList<T> list) {

        if (list.isEmpty()) {
            view.getUserInput(UserInput.InputType.NONE, "No objects in list!");
            return;
        }

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





    // TODO: fixa search i helpMe.txt samt lägg till annat...
    public void showHelpMenu() {

        do {
            int menuChoice = view.getUserInput(UserInput.InputType.INT, "Help Menu:\n1. How to create a workout\n2. How to edit a workout\n3. How to search for a workout\n0. Go Back\n").intValue;

            switch (menuChoice) {
                case 0:
                    return;
                case 1:
                    getHelpText("Section1", "Section2");
                    break;
                case 2:
                    getHelpText("Section2", "Section3");
                    break;
                case 3:
                    getHelpText("Section3", "Section4");
                    break;
            }
        } while (true);
    }


    public void getHelpText(String readFrom, String readTo) {

        boolean atReadStartPosition = false;
        ArrayList<String> lines = FileUtils.readAllLines("helpMe.txt"); // Fetch arrayList of Strings from FileUtils

        for (String line : lines) {

            if (line.contains(readFrom)) {
                atReadStartPosition = true;
                continue;
            }

            if (atReadStartPosition) {
                if (line.contains(readTo)) {
                    return;
                }
                view.getUserInput(UserInput.InputType.NONE, line);
            }
        }
    }



    // TODO: !!!! Inte välja mellan exercise och Workout, utan det tas in!!!!!
    // TODO: Lägg till sort Firends här???
    public void sortMenu(GymMember gymMember) {

        int menuChoice;

        do {
            menuChoice = view.getUserInput(UserInput.InputType.INT, "Sort:\n1. Workouts\n2. Exercises\n0. Go Back").intValue;

            show(gymMember, gymMember.getWorkoutList(), "Current Workout(s):");

            switch (menuChoice) {
                case 0:
                    saveWorkouts();
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

    public void sortFriends(GymMember gymMember) {

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


    public void sortWorkout(GymMember gymMember) {

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


    public void sortExercises(ArrayList<Workout> workouts) {

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





package com.HenrikJangefelt;

import java.util.Scanner;

// Singleton
public class View {

    // TODO: FIxa try and catch for inputs!!
    // TODO: Final check- kolla att man bara kan ange siffror, samt bara rätt intervall för tex arrayer...
    // TODO: ta in userInout som String och omvandla sedan (Fixar bugg med nextLine inte läses in?) TODO: FIx isNumber
    // TODO: lägg till eftet t.ex: Add (Workout) eller Add (Friend)

    Scanner input = new Scanner(System.in);
    private static View instance = null;


    public enum MainMenuItem {

        WORKOUTS("Workouts"),
        FRIENDS("Friends"),
        AVAILABLE_STAFF("Check Available Staff"),
        HELP("Help"),
        EXIT("Exit");

        private String description;

        MainMenuItem(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }


    public enum SubMenuItem {

        ADD("Add"),
        EDIT("Edit"),
        //REMOVE("Remove"),
        SHOW("Show"),
        SORT("Sort"),
        SEARCH("Search"),
        BACK("Go Back");

        private String description;

        SubMenuItem(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }


    public enum EditMenuItem {
        ADD("Add"),
        EDIT("Edit"),
        REMOVE("Remove"),
        BACK("Go Back");

        private String description;

        EditMenuItem(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }

    public enum ExerciseOptions {
        NAME("Name"),
        REPS("Reps"),
        SETS("Sets"),
        MUSCLE("Muscle"),
        BACK("Go Back");

        private String description;

        ExerciseOptions(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }


    private View() {
        // Hidden Constructor
    }

    public static View getInstance() {
        if (instance == null) {
            instance = new View();
        }
        return instance;
    }







    // TODO: combine show (för att vissa objekt) och showMenu
    // TODO: Show alla listor eller enums
    // TODO: Combine show And Get for menu
    public <T extends Enum<T>> void showMenu(Class<T> enumType, String menuType) {

        System.out.printf("%s:\n", menuType);
        int i = 0;

        for (Enum<T> item : enumType.getEnumConstants()) {
            System.out.println((++i) + ". " + item.toString());
        }

          /*for (T item : EnumSet.allOf(enumType)) {
            System.out.println((++i) + " " + item.toString());
        }*/
    }


    public <T extends Enum<T>> T getMenuItem(Class<T> enumType) {

        int choiceIndex = getNumberFromUserInput();

        return enumType.getEnumConstants()[choiceIndex - 1]; // TODO: felhantering för index out of bounds??!!
    }





    // Displays message from user and expects an input
    public void inOutUser() {
        // TODO: Returnera ett objekt (String, int (vad som behövs))
    }


    // TODO; kanske splita upp i en del som vissar meddelande och en som tar input
    // TODO: KAnkse bättre med två olika funktioner ändå???
    public UserInput getUserInput(UserInput.InputType inputType, String message) {
        System.out.println(message);

        UserInput userInput = new UserInput();

        switch (inputType) {
            case STRING:
                userInput.stringValue = input.nextLine().trim();
                break;
            case INT:
                userInput.intValue = getNumberFromUserInput();
                break;
            case NONE:
                return null; // TODO Correct?
        }
        return userInput;
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showErrorMessage(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }


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
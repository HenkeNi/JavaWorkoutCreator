package com.HenrikJangefelt.view;

import com.HenrikJangefelt.models.UserInput;

import java.util.Scanner;

// Singleton
public class View {

    // TODO: FIxa try and catch for inputs!!
    // TODO: Final check- kolla att man bara kan ange siffror, samt bara rätt intervall för tex arrayer...
    // TODO: ta in userInout som String och omvandla sedan (Fixar bugg med nextLine inte läses in?) TODO: FIx isNumber
    // TODO: lägg till eftet t.ex: Add (Workout) eller Add (Friend)

    // TODO: ta bort showMessage???

    Scanner input = new Scanner(System.in);
    private static View instance = null;


    public enum EditExercise {

        EDIT_NAME("Change exercise name"),
        EDIT_REPS("Change number of reps"),
        EDIT_SETS("Change number of sets"),
        EDIT_MUSCLE("Change worked muscle"),
        GO_BACK("Go back");

        private String description;

        EditExercise(String description) {
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




    // Hidden Constructor (Singleton Pattern)
    private View() {}

    public static View getInstance() {
        if (instance == null) {
            instance = new View();
        }
        return instance;
    }










    // TODO: combine show (för att vissa objekt) och showMenu ... En metod som vissar allt
    // TODO: Show alla listor eller enums
    // TODO: Combine show And Get for menu???
    public <T extends Enum> void showMenu(Class<T> enumType, String menuType) {

        System.out.printf("%s:\n", menuType);
        int i = 0;

        for (Enum item : enumType.getEnumConstants()) {
            System.out.println((++i) + ". " + item.toString());
        }

          /*for (T item : EnumSet.allOf(enumType)) {
            System.out.println((++i) + " " + item.toString());
        }*/
    }


    public <T extends Enum<T>> T getMenuItem(Class<T> enumType) {

        int choiceIndex;

        do {
            choiceIndex = getNumberFromUserInput();
        } while (choiceIndex > enumType.getEnumConstants().length);

        return enumType.getEnumConstants()[choiceIndex - 1]; // TODO: felhantering för index out of bounds??!!
    }


    /*public <T extends Enum<T>> T getMenuItem(Class<T> enumType) {

        int choiceIndex;

        do {

        } while ()

        int choiceIndex = getNumberFromUserInput();

        return enumType.getEnumConstants()[choiceIndex - 1]; // TODO: felhantering för index out of bounds??!!
    }*/





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



    // TODO: GÖr test fil till denna?
    public int getNumberFromUserInput() {

        int numb = -999;

        do {
            //System.out.println("Enter menu choice:");
            String userInput = input.nextLine();
            try {
                numb = Integer.parseInt(userInput);
            } catch (Exception e) {
                System.out.println("Must enter a number:");
            }
        } while (numb == -999);
        return numb;
    }






    // TODO: bygg om / fixa
    public int[] getListNumberPrefix(String editOrDelete) {

            boolean validNumbers = false;
            int workoutIndex = 0;
            int exerciseIndex = 0;

            do {
                UserInput userInput = getUserInput(UserInput.InputType.STRING, "Enter the prefix-number of the workout or exercise you want to " + editOrDelete);
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

    }



}








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






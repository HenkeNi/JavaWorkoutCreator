package com.HenrikJangefelt;

import java.util.EnumSet;
import java.util.Scanner;

// Singleton
public class View {

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
        BACK("Back");

        private String description;

        SubMenuItem(String description) {
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




    public <T extends Enum<T>> void showMenu(Class<T> enumType, String menuType) {

        System.out.printf("%s:\n", menuType);
        int i = 0;

        for (Enum<T> item : enumType.getEnumConstants()) {
            System.out.println((++i) + " " + item.toString());
        }

          /*for (T item : EnumSet.allOf(enumType)) {
            System.out.println((++i) + " " + item.toString());
        }*/
    }


    public <T extends Enum<T>> T getMenuChoice(Class<T> enumType) {

        int choiceIndex = getNumberFromUserInput();

        return enumType.getEnumConstants()[choiceIndex - 1]; // TODO: felhantering för index out of bounds??!!
    }


    // Displays message from user and expects an input
    public void inOutUser() {
        // TODO: Returnera ett objekt (String, int (vad som behövs))
    }


    // enum? number or string
    public UserInput getUserInput(UserInput.InputType inputType, String message) {
        System.out.println(message);

        UserInput userInput = new UserInput();

        switch (inputType) {
            case STRING:
                userInput.message = input.nextLine();
                break;
            case INT:
                userInput.number = getNumberFromUserInput();
                break;
        }
        return userInput;
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


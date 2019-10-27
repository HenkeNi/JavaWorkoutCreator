package com.HenrikJangefelt.view;

import com.HenrikJangefelt.models.UserInput;

import java.util.Scanner;


/**
 * A Singleton class that is in charge of user input and -output.
 */
public class View {

    // TODO: FIxa try and catch for inputs!!
    // TODO: Final check- kolla att man bara kan ange siffror, samt bara rätt intervall för tex arrayer...
    // TODO: ta in userInout som String och omvandla sedan (Fixar bugg med nextLine inte läses in?) TODO: FIx isNumber
    // TODO: lägg till eftet t.ex: Add (Workout) eller Add (Friend)

    Scanner input = new Scanner(System.in);
    private static View instance = null;


    private View() {}

    public static View getInstance() {
        if (instance == null) {
            instance = new View();
        }
        return instance;
    }


    /**
     * Method for showing menus consisting of enum cases (or classes).
     * @param enumType Takes in a enumType (actually a class)
     * @param menuType Takes in a String, what kind of menu
     * @param <T>
     */
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


    /**
     * Returns menu item selected in menu.
     * @param enumType
     * @param <T>
     * @return
     */
    public <T extends Enum<T>> T getMenuItem(Class<T> enumType) {

        int choiceIndex;

        do {
            choiceIndex = getNumberFromUserInput();
        } while (choiceIndex > enumType.getEnumConstants().length);

        return enumType.getEnumConstants()[choiceIndex - 1]; // TODO: felhantering för index out of bounds??!!
    }

    
    /**
     * Returns user input as an object (UserInput)
     * @param inputType
     * @return
     */
    public UserInput getUserInput(UserInput.InputType inputType) {
        //public UserInput getUserInput(UserInput.InputType inputType, String message) {
        //System.out.println(message);

        UserInput userInput = new UserInput();

        switch (inputType) {
            case STRING:
                userInput.setStringValue(input.nextLine().trim());
                break;
            case INT:
                userInput.setIntValue(getNumberFromUserInput());
                break;
        }
        return userInput;
    }

    public void showMessage(String message) {
        System.out.println(message);
    }


    /**
     * Gets a number from user.
     * @return
     */
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

    /**
     * Returns numbers selected when selecting specific workout or exercise.
     * @param editOrDelete
     * @return
     */
    public int[] getListNumberPrefix(String editOrDelete) {

            boolean validNumbers = false;
            int workoutIndex = 0;
            int exerciseIndex = 0;

            do {
                showMessage("Enter the prefix-number of the workout or exercise you want to " + editOrDelete);
                UserInput userInput = getUserInput(UserInput.InputType.STRING);
                String input = userInput.getStringValue().replace(".", "").trim();
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






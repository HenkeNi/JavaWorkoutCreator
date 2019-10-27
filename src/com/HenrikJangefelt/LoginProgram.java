package com.HenrikJangefelt;

import com.HenrikJangefelt.models.UserInput;
import com.HenrikJangefelt.view.View;

/**
 * <h1>LoginProgram</h1>
 * The LoginProgram is responsible for signing in a user or to create a new one,
 * in order to access the rest of the program.
 *
 * @author Henrik Jangefelt Nilsson
 */
public class LoginProgram {

    View view = View.getInstance(); // Get instance to View class

    public LoginProgram() {
        loginMenu();
    }


    /**
     * <h1>Login Menu</h1>
     *  Menu that displays options for signing in or creating a new user.
     */
    public void loginMenu() {

       do {
           view.showMessage("\n" +
                   "Welcome to the 'Workout Creator' a tool for creating custom workouts.\n" +
                   "1. Login\n" +
                   "2. Register new user\n" +
                   "3. Exit\n" +
                   "   ...\n" +
                   "4. Quick Login");
           int menuChoice = view.getUserInput(UserInput.InputType.INT).getIntValue();

            switch (menuChoice) {
                case 1:
                    login();
                    break;
                case 2:
                    getEmailAndPassword();
                    break;
                case 3:
                    view.showMessage("Terminating...");
                    return;
                case 4:
                    createNewUser(true, "Test@hotmail.com", "password123");
                    //quickLogin();
                    break;
            }
        } while (true);
    }


    /**
     * <h1>getEmailAndPassword</h1>
     * Creates email and password for new user.
     */
    public void getEmailAndPassword() {

        boolean validEmail = false;
        boolean validPassword = false;

        String emailAddress;
        String password;

        do {
            view.showMessage("\n" +
                    "Creating new account...\n\n" +
                    "Email address:");


            emailAddress = view.getUserInput(UserInput.InputType.STRING).getStringValue();
            validEmail = validateEmailFormat(emailAddress); // Checks for valid email input
        } while (!validEmail);

        do {
            view.showMessage("Password:");
            password = view.getUserInput(UserInput.InputType.STRING).getStringValue();

            validPassword = validatePasswordFormat(password); // Checks for valid password

        } while (!validPassword);

        createNewUser(false, emailAddress, password);
    }


    /**
     * Creates a new user.
     * @param isQuickLogin Takes in a bool as first parameter to createNewUser method
     * @param email Takes in a string as second parameter to createNewUser method
     * @param password Takes in a string as third parameter to createNewUser method
     */
    public void createNewUser(boolean isQuickLogin, String email, String password) {

        // TODO: Fixa
        //view.showMessage();
        //String firstName = isQuickLogin ? "Mr." : view.getUserInput(UserInput.InputType.STRING, "Enter first name:").stringValue;
        //String lastName = isQuickLogin ? "Default" : view.getUserInput(UserInput.InputType.STRING, "Enter last name:").stringValue;
        String firstName = isQuickLogin ? "Mr." : view.getUserInput(UserInput.InputType.STRING).getStringValue();
        String lastName = isQuickLogin ? "Default" : view.getUserInput(UserInput.InputType.STRING).getStringValue();

        TrainingProgram.currentUser.setFirstName(firstName);
        TrainingProgram.currentUser.setLastName(lastName);
        TrainingProgram.currentUser.setEmailAddress(email);
        TrainingProgram.currentUser.setPassword(password);

        new TrainingProgram();
    }


    /**
     * Method that checks for valid email input and returns a boolena.
     * @param emailAddress Takes in a string as first parameter
     * @return Returns validated email as a boolean
     */
    public boolean validateEmailFormat(String emailAddress) {

        if (emailAddress.length() < 15) {
            System.out.println("Email must be 15 characters or longer");
            return false;
        }

        if (!emailAddress.contains("@hotmail") && !emailAddress.contains("@gmail")) {
            System.out.println("Email must contain @hotmail or @gmail");
            return false;
        }

        if (!emailAddress.endsWith(".com") && !emailAddress.endsWith(".net") && !emailAddress.endsWith(".org")) {
            System.out.println("Email must end with '.com', '.net' or '.org'");
            return false;
        }
        return true;
    }


    /**
     * Checks for valid password.
     * @param password Takes in a string as first parameter
     * @return Returns validated password as a boolean
     */
    public boolean validatePasswordFormat(String password) {
        return !password.equals("") && !password.equals("password") && password.length() >= 8;
    }


    /**
     * Checks email and password input with current users eamil and password and either let the user progress to the rest
     * of the program of returns the user to the menu if failed.
     */
    public void login() {

        // TODO: Fetch user?

        view.showMessage("Email Address:");
        String userEmail = view.getUserInput(UserInput.InputType.STRING).getStringValue();

        view.showMessage("Password:");
        String userPassword = view.getUserInput(UserInput.InputType.STRING).getStringValue();

        boolean loginApproved = checkLoginInformation(userEmail, userPassword);

        String accessMessage = loginApproved ? "Granted" : "Denied";
        view.showMessage("Access " + accessMessage);

        if (loginApproved) {
            view.showMessage("Welcome Back " + TrainingProgram.currentUser.getFullName() + "!\n");

            new TrainingProgram();
        } else {
            return;
        }
    }


    /**
     * Validates the email and password
     * @param email Takes in a string as first parameter
     * @param password Takes in a string as second parameter
     * @return Returns validated login information as a boolean
     */
    public boolean checkLoginInformation(String email, String password) {

        if (!email.equals(TrainingProgram.currentUser.getEmailAddress())) {
            view.showMessage("Invalid Email Address!");
            return false;
        }
        if (!password.equals(TrainingProgram.currentUser.getPassword())) {
            view.showMessage("Invalid Password!");
            return false;
        }
        return true;
    }


}







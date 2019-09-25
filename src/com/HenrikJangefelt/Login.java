package com.HenrikJangefelt;

import java.util.Scanner;

// TODO: använd nextLine, men spara bara första delen av strängen(?)
public class Login {

    Scanner input = new Scanner(System.in);


    public Login() {
        loginMenu();
    }

    private void loginMenu() {

       do {
            System.out.println("\nWelcome to the 'Workout Creator' a tool for creating custom workouts.\n1. Login\n2. Register new user\n3. Exit\n   ...\n4. (Secret bypass option for lazy teachers)");
            int menuSelection = input.nextInt();

            switch (menuSelection) {
                case 1:
                    login();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    System.out.println("Terminating...");
                    return;
                case 4:
                    bypassOption();
                    break;
            }
        } while (true);
    }


    private void createAccount() {

        boolean validEmail = false;
        boolean validPassword = false;

        String emailAddress;
        String password;

        do {
            System.out.println("\nCreating new account...\n\nEmail address: ");
            emailAddress = input.next();
            validEmail = checkEmailFormat(emailAddress); // Checks for valid email input
        } while (!validEmail);

        do {
            System.out.println("Password:");
            password = input.next();

            if (!password.equals("") && !password.equals("password")) {
                validPassword = true;
            }
        } while (!validPassword);

        createNewUser(emailAddress, password);
    }


    private void createNewUser(String emailAddress, String password) {

        input.nextLine();
        System.out.println("Enter first name:");
        TrainingProgram.currentUser.setFirstName(input.nextLine());

        System.out.println("Enter last name:");
        TrainingProgram.currentUser.setLastName(input.nextLine());

        TrainingProgram.currentUser.setEmailAdress(emailAddress);
        TrainingProgram.currentUser.setPassword(password);

        System.out.printf("Welcome %s!\n\n", TrainingProgram.currentUser.getFullName());
        new TrainingProgram();
    }

    // TODO: use else if instead??
    private boolean checkEmailFormat(String emailAddress) {

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


    private void login() {

        System.out.println("Email address:");
        String loginEmail = input.next();

        System.out.println("Password:");
        String loginPassword = input.next();

        boolean loginCorrect = checkLoginInformation(loginEmail, loginPassword);

        String accessMessage = loginCorrect ? "granted" : "denied";
        System.out.println("Access " + accessMessage);

        if (loginCorrect) {
            System.out.printf("Welcome Back %s!\n", TrainingProgram.currentUser.getFullName());
            new TrainingProgram();
        } else {
            return;
        }
    }

    private boolean checkLoginInformation(String email, String password) {

        if (!email.equals(TrainingProgram.currentUser.getEmailAdress())) {
            System.out.println("Invalid Email Address");
            return false;
        }
        if (!password.equals(TrainingProgram.currentUser.getPassword())) {
            System.out.println("Invalid Password");
            return false;
        }

        return true;

        //return email.equals(TrainingProgram.currentUser.getEmailAdress()) && password.equals(TrainingProgram.currentUser.getPassword());
    }

    private void bypassOption() {

        TrainingProgram.currentUser.setFirstName("Mr.");
        TrainingProgram.currentUser.setLastName("Default");

        TrainingProgram.currentUser.setEmailAdress("Test@hotmail.com");
        TrainingProgram.currentUser.setPassword("password123");

        System.out.printf("Welcome %s!\n\n", TrainingProgram.currentUser.getFullName());
        new TrainingProgram();
    }
}


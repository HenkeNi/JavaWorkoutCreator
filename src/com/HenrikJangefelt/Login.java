package com.HenrikJangefelt;

import java.util.Scanner;

public class Login {

    Scanner input = new Scanner(System.in);

    boolean validEmail = false;
    boolean validPassword = false;

    public Login() {
        loginMenu();
    }

    // TODO: Secret Bypass option/button for lazy teachers
    private void loginMenu() {

       do {
            System.out.println("\nWelcome to the 'Workout Creator' a tool for creating custom workouts.\n1. Login\n2. Register new user\n3. Exit");
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
            }
        } while (true);
    }



    public void createAccount() {

        String emailAddress;
        String password;

        do {
            System.out.println("\nCreating new account...\n\nEmail Address: ");
            emailAddress = input.next();
            validEmail = checkEmailFormat(emailAddress); // Checks for valid email input
        } while (!validEmail);

        do {
            System.out.println("Enter password:");
            password = input.next();

            if (!password.equals("") && !password.equals("password")) {
                validPassword = true;
            }
        } while (!validPassword);

        createNewUser(emailAddress, password);
    }


    public void createNewUser(String emailAddress, String password) {

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

        System.out.println("Please enter your email:");
        String loginEmail = input.next();

        System.out.println("Please enter your password:");
        String loginPassword = input.next();

        boolean loginCorrect = checkLoginInformation(loginEmail, loginPassword);

        String accessMessage = loginCorrect ? "Accepted" : "Denied";
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
}

   /* public void createNewUser(String emailAddress, String password) {

        input.nextLine();
        System.out.println("Enter first name:");
        TrainingProgram.currentUser.setFirstName(input.nextLine());
        //String firstName = input.nextLine();
        System.out.println("Enter last name:");
        TrainingProgram.currentUser.setLastName(input.nextLine());
        //String lastName = input.nextLine();

        // TODO: !!!!! Använd static currentUser
        //GymMember currentUser = new GymMember(firstName, lastName);
        //currentUser.setEmailAdress(emailAddress);
        //currentUser.setPassword(password);

        TrainingProgram.currentUser.setEmailAdress(emailAddress);
        TrainingProgram.currentUser.setPassword(password);

        // TODO: GO to Traingin porg
        //new TrainingProgram(currentUser);
        new TrainingProgram();
    }*/

// Combined (hard to read)
// Email must end with .com // .net // .org and contain either @hotmail // @gmail
        /*if (emailAddress.length() > 15 && (emailAddress.contains("@hotmail") || emailAddress.contains("@gmail")) &&
                (emailAddress.endsWith(".com") || emailAddress.endsWith(".net") ||  emailAddress.endsWith(".org"))) {
            return true;
        } else {
            System.out.println("Invalid Email Address");
            return false;
        }*/

// Simple
        /*if (emailAddress.endsWith("@hotmail.com") || emailAddress.endsWith("@gmail.com")) {
            System.out.println("Yup, ends with hotmail.com");
            return true;
        } else {
            return false;
        }*/
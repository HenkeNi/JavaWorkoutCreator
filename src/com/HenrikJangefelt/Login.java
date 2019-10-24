package com.HenrikJangefelt;

// TODO: när man skapar ett konto/registrerar nytt ladda inte in data från fil (använd bool)
// TODO: Hämta hel användare som är  sparad??!!


// TODO: Använd bara welcome "user" på ett ställe
// TODO: is logged in på bara ett ställe med?
// Todo: kolla om email finns sparat...
public class Login {

    View view = View.getInstance(); // Get instance to View class

    public Login() {
        loginMenu();
    }


    private void loginMenu() {

       do {
           int menuChoice = view.getUserInput(UserInput.InputType.INT, "\n" +
                   "Welcome to the 'Workout Creator' a tool for creating custom workouts.\n" +
                   "1. Login\n" +
                   "2. Register new user\n" +
                   "3. Exit\n" +
                   "   ...\n" +
                   "4. Quick Login").intValue;

            switch (menuChoice) {
                case 1:
                    login();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    view.getUserInput(UserInput.InputType.NONE, "Terminating...");
                    return;
                case 4:
                    createNewUser(true, "Test@hotmail.com", "password123");
                    //quickLogin();
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
            UserInput userInput = view.getUserInput(UserInput.InputType.STRING, "\n" +
                    "Creating new account...\n\n" +
                    "Email address:");

            emailAddress = userInput.stringValue;
            validEmail = validateEmailFormat(emailAddress); // Checks for valid email input
        } while (!validEmail);

        do {
            UserInput userInput = view.getUserInput(UserInput.InputType.STRING, "Password:");
            password = userInput.stringValue;

            validPassword = validatePasswordFormat(password); // Checks for valid password

        } while (!validPassword);

        createNewUser(false, emailAddress, password);
    }




    private void createNewUser(boolean isQuickLogin, String email, String password) {

        String firstName = isQuickLogin ? "Mr." : view.getUserInput(UserInput.InputType.STRING, "Enter first name:").stringValue;
        String lastName = isQuickLogin ? "Default" : view.getUserInput(UserInput.InputType.STRING, "Enter last name:").stringValue;

        TrainingProgram.currentUser.setFirstName(firstName);
        TrainingProgram.currentUser.setLastName(lastName);
        TrainingProgram.currentUser.setEmailAdress(email);
        TrainingProgram.currentUser.setPassword(password);

        new TrainingProgram();
    }


    // TODO: use else if instead??
    private boolean validateEmailFormat(String emailAddress) {

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

    private boolean validatePasswordFormat(String password) {

        return !password.equals("") && !password.equals("password");
    }


    private void login() {

        // TODO: Fetch user?

        String userEmail = view.getUserInput(UserInput.InputType.STRING, "Email Address:").stringValue;
        String userPassword = view.getUserInput(UserInput.InputType.STRING, "Password:").stringValue;

        boolean loginApproved = checkLoginInformation(userEmail, userPassword);

        String accessMessage = loginApproved ? "Granted" : "Denied";
        view.getUserInput(UserInput.InputType.NONE, "Access " + accessMessage);

        if (loginApproved) {
            view.getUserInput(UserInput.InputType.NONE, "Welcome Back " + TrainingProgram.currentUser.getFullName() + "!\n");

            new TrainingProgram();
            //TrainingProgram.isLoggedIn = true;
        } else {
            return;
        }
    }

    private boolean checkLoginInformation(String email, String password) {

        if (!email.equals(TrainingProgram.currentUser.getEmailAdress())) {
            view.getUserInput(UserInput.InputType.NONE, "Invalid Email Address!");
            return false;
        }
        if (!password.equals(TrainingProgram.currentUser.getPassword())) {
            view.getUserInput(UserInput.InputType.NONE, "Invalid Password!");
            return false;
        }
        return true;
    }


}



   /*private void createNewUser(String emailAddress, String password) {

        // TODO: Kolla upp om Userinput userinput inte behövs!!!
        TrainingProgram.currentUser.setFirstName(view.getUserInput(UserInput.InputType.STRING, "Enter first name:").stringValue);
        TrainingProgram.currentUser.setLastName(view.getUserInput(UserInput.InputType.STRING, "Enter last name:").stringValue);
        TrainingProgram.currentUser.setEmailAdress(emailAddress);
        TrainingProgram.currentUser.setPassword(password);

        view.getUserInput(UserInput.InputType.NONE, "Welcome " + TrainingProgram.currentUser.getFullName() + "!\n\n");
        new TrainingProgram();
        //TrainingProgram.isLoggedIn = true;
    }

    private void quickLogin() {

        TrainingProgram.currentUser.setFirstName("Mr.");
        TrainingProgram.currentUser.setLastName("Default");

        TrainingProgram.currentUser.setEmailAdress("Test@hotmail.com");
        TrainingProgram.currentUser.setPassword("password123");

        view.getUserInput(UserInput.InputType.NONE, "Welcome " + TrainingProgram.currentUser.getFullName() + "!\n");
        new TrainingProgram();
        //TrainingProgram.isLoggedIn = true;
    }*/

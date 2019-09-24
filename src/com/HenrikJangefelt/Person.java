package com.HenrikJangefelt;

// TODO: REname??
// TODO: Create abstract metod
public abstract class Person {

    private String firstName; // TODO, sätt default värde?
    private String lastName;
    private String emailAdress;
    private String password;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // TODO: Make abstract??
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAdress() {
        return emailAdress;
    }
    public String getPassword() {
        return password;
    }

    //TODO: add an abstract metod
    //public abstract String greeting();

    // TODO: OM FLera abstracta metoder lägg i interface




}

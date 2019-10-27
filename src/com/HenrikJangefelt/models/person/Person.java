package com.HenrikJangefelt.models.person;

import java.io.Serializable;

public abstract class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getEmailAddress() { return emailAddress; }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }


}

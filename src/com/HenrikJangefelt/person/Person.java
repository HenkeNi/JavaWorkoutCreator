//package com.HenrikJangefelt;
package com.HenrikJangefelt.person;

import java.io.Serializable;

// TODO: ta bort onödiga getters osv....
// TODO: Rename to something else??
// TODO: Add an abstract metod
public abstract class Person implements Serializable {

    private String firstName;
    private String lastName;
    private String emailAdress;
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

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getEmailAdress() { return emailAdress; }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }



    //TODO: add an abstract metod
    //public abstract String greeting();
    // Login som abstakt // ??? GymMembers använder kanske nånting annat
    // CHeck in??

    // TODO: OM FLera abstracta metoder lägg i interface


    // TODO: logik för att logga in?
}
package com.HenrikJangefelt;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginProgramTest {

    LoginProgram loginProgram = new LoginProgram();

    @Test
    public void validateEmailFormatTest() {

        String emailAddress1 = "test@hotmail.com";
        assertTrue(loginProgram.validateEmailFormat(emailAddress1));

        String emailAddress2 = "test@gmail.com";
        assertTrue(loginProgram.validateEmailFormat(emailAddress2));

        String emailAddress3 = "test@hotmail";
        assertFalse(loginProgram.validateEmailFormat(emailAddress3));

        String emailAddress4 = "asla";
        assertFalse(loginProgram.validateEmailFormat(emailAddress4));

    }


    @Test
    public void validatePasswordFormatTest() {

        String password1 = "123";
        assertFalse(loginProgram.validatePasswordFormat(password1));

        String password2 = "password";
        assertFalse(loginProgram.validatePasswordFormat(password2));

        String password3 = "secretCode";
        assertTrue(loginProgram.validatePasswordFormat(password3));

        String password4 = "password123";
        assertTrue(loginProgram.validatePasswordFormat(password4));
    }

}


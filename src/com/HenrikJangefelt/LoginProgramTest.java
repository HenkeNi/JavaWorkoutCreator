package com.HenrikJangefelt;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class LoginProgramTest {


    @Test
    public void validateEmailFormat() {

        //String input = "test@hotmail.com";
        //System.in(new ByteArrayInputStream(input.getBytes())); // Behövs när user input förväntas

        String emailAddress1 = "test@hotmail.com";
        assertTrue(emailAddress1.length() > 15);
        assertTrue(emailAddress1.contains("@hotmail") || emailAddress1.contains("@gmail"));
        assertTrue(emailAddress1.endsWith(".com") || emailAddress1.endsWith(".net") || emailAddress1.endsWith(".org"));

        String emailAddress2 = "email.comt";
        assertFalse(emailAddress2.length() > 15);
        assertFalse(emailAddress2.contains("@hotmail") || emailAddress2.contains("@gmail"));
        assertFalse(emailAddress2.endsWith(".com") || emailAddress2.endsWith(".net") || emailAddress2.endsWith(".org"));
    }

    @Test
    public void validatePasswordFormat() {

        String password1 = "password123";

        assertTrue(password1 != "");
        assertTrue(password1 != "password");
        assertTrue(password1.length() >= 8);
    }
}
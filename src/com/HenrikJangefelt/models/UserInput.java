package com.HenrikJangefelt.models;

/**
 * Class UserInput works as return type for user input.
 */
public class UserInput {

    public enum InputType {
        STRING,
        INT,
    }

    private String stringValue;

    private int intValue;

    public int getIntValue() { return intValue; }

    public String getStringValue() { return stringValue; }

    public void setIntValue(int intValue) { this.intValue = intValue; }

    public void setStringValue(String stringValue) { this.stringValue = stringValue; }
}

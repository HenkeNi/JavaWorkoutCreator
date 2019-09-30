package com.HenrikJangefelt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {




    /*public static void writeObject(String fileName, Workout workout){
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = new FileOutputStream(fileName, true); // objekt oftast '.ser' true innebär lägger till/false skriver över
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(workout);
            objectOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/

    // Static, so it can be reaced without creating an object of FileUtils
    /*public static void writeObjects(String fileName, ArrayList<Workout> workouts) {
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName, true);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(workouts);
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Workout> readObjects(String fileName){
        ObjectInputStream objectinputstream = null;
        List<Workout> workouts = null;
        try {
            FileInputStream streamIn = new FileInputStream(fileName);
            objectinputstream = new ObjectInputStream(streamIn);
            workouts = (List<Workout>) objectinputstream.readObject();
            objectinputstream .close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workouts;
    }*/

   /* public static ArrayList<Workout> readObjects(String fileName){
        ObjectInputStream objectinputstream = null;
        ArrayList<Workout> workouts = null;
        try {
            FileInputStream streamIn = new FileInputStream(fileName);
            objectinputstream = new ObjectInputStream(streamIn);
            workouts = (ArrayList<Workout>) objectinputstream.readObject();
            objectinputstream .close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workouts;
    }*/



    /*public static <T extends Object> ArrayList<T> readObjects(String fileName){
        ObjectInputStream objectinputstream = null;
        ArrayList<T> objectList = null;
        try {
            FileInputStream streamIn = new FileInputStream(fileName);
            objectinputstream = new ObjectInputStream(streamIn);
            objectList = (ArrayList<T>) objectinputstream.readObject();
            objectinputstream .close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectList;
    }


    // Flexibel för generic objekt
    public static <T extends Object> void writeGenericObjects(String fileName, ArrayList<T> objectList){
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = new FileOutputStream(fileName, true); // objekt oftast '.ser' true innebär lägger till/false skriver över
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(objectList);
            objectOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/

    // TODO: Spara antingen workouts och friends för sig elle gymMember object
    public static <T extends Object> ArrayList<T> readGenericObjects(String fileName){
        ObjectInputStream objectinputstream = null;
        ArrayList<T> objectList = null;
        try {
            FileInputStream streamIn = new FileInputStream(fileName);
            objectinputstream = new ObjectInputStream(streamIn);
            objectList = (ArrayList<T>) objectinputstream.readObject();
            objectinputstream .close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectList;
    }


    // Flexibel för generic objekt
    public static <T extends Object> void writeGenericObjects(String fileName, List<T> objectList){
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = new FileOutputStream(fileName, false); // objekt oftast '.ser' true innebär lägger till/false skriver över
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(objectList);
            objectOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}

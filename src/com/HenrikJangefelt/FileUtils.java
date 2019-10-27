package com.HenrikJangefelt;

import com.HenrikJangefelt.view.View;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {



    //Read from file...
    public static  ArrayList<String> readAllLines(String fileName) {

        ArrayList<String> allLines = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (String line : lines) {
                allLines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allLines;
    }


    public static void writeAllLines(ArrayList<String> lines, String fileName) {

        if (!canWriteToFile(fileName)) { return; }

        try {
            Path path = Paths.get(fileName);
            Files.write(path, lines);
        } catch (Exception e) {
            //e.printStackTrace();
            View.getInstance().showMessage("This is a read only file!");
        }

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
            View.getInstance().showMessage("List successfully saved!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }




    // TEST
    public static <T extends Object> void saveObjects(String filename, ArrayList<T> objects, StandardOpenOption... option) {
        Path path = Paths.get(filename);
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path, option))) {
            for (T object : objects) {
                out.writeObject(object);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    // TEST
    public static <T extends Object> ArrayList<T> loadObjects(String filename) {

        Path path = Paths.get(filename);
        ArrayList<T> objectList = null;
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))) {
            return (ArrayList<T>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



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





    public static void setReadOnly(String fileName) {
        File file = new File(fileName);
        file.setReadOnly();
    }


    public static boolean canWriteToFile(String fileName) {
        File file = new File(fileName);
        return file.canWrite();
    }

}








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




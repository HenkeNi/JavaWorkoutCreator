package com.HenrikJangefelt;

import com.HenrikJangefelt.view.View;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    
    /**
     * Fetches text from textFile as an arrayList of strings
     * @param fileName Takes in a filename or filepath
     * @return Returns the arrayList with text
     */
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


    /**
     * Writes text (as an ArrayList of Strings) to text file.
     * @param lines Takes in an ArrayList of Strings
     * @param fileName Takes in a filename as a String
     */
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


    /**
     * Save objects to file.
     * @param fileName Takes in a filename as a String
     * @param objectList Takes in an ArrayList (generic) of objects
     * @param <T> Method is generic
     */
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


    /**
     * Fetches objects from file.
     * @param fileName Takes in a filename to fetch from
     * @param <T> Is generic
     * @return Returns the ArrayList of fetched objects
     */
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

    /**
     * Set file as read only
     * @param fileName
     */
    public static void setReadOnly(String fileName) {
        File file = new File(fileName);
        file.setReadOnly();
    }

    /**
     * Check if file can be written to.
     * @param fileName
     * @return
     */
    public static boolean canWriteToFile(String fileName) {
        File file = new File(fileName);
        return file.canWrite();
    }



    // TEST
    /*public static <T extends Object> void saveObjects(String filename, ArrayList<T> objects, StandardOpenOption... option) {
        Path path = Paths.get(filename);
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path, option))) {
            for (T object : objects) {
                out.writeObject(object);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/


    // TEST
    /*public static <T extends Object> ArrayList<T> loadObjects(String filename) {

        Path path = Paths.get(filename);
        ArrayList<T> objectList = null;
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))) {
            return (ArrayList<T>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

}









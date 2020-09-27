package com.sg.flooringmastery.dao;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.sg.flooringmastery.dto.Order;

public class FlooringMasteryStorage {
    private List<String> orderFiles = new ArrayList<>();
    private File fileOrders = new File("");
    int i = 1;
    Map<LocalDate, Order> storeDates = new HashMap<>();
    boolean trueOrFalse = false;

    private String formatDateToString(LocalDate currentDate) {
        String currentDateString = currentDate.toString();
        String[] tokens = currentDateString.split("-");
        String year = tokens[0];
        String month = tokens[1];
        String day = tokens[2];
        String newFormat = month + day + year;
        return newFormat;
    }

    // private LocalDate formatStringToDate(String currentDate) {

    // }

    public void createNewFile(LocalDate orderDate) throws IOException {
        String newFileName = formatDateToString(orderDate);
        File newFile = new File("Orders\\Orders_" + newFileName + ".txt");
        newFile.createNewFile();
        orderFiles.add(newFile.toString());
    }

    public void deleteFileIfEmpty(File file) {
        file.delete();
    }

    public LocalDate findOrderByDate(LocalDate userInputOrderDate) throws Exception { 
        System.out.println("findOre" + userInputOrderDate);
        if (doesFileExist(userInputOrderDate) == null) {
            System.out.println("returning null storage");
            return null;
        }
        System.out.println("returning true storage");
        System.out.println(orderFiles);
        return userInputOrderDate;
    }

    private void importFiles() throws Exception {
        // ADD FILES FROM DIRECTORY TO LIST
        System.out.println("importFiles()");
        System.out.println(orderFiles);

        String[] pathnames;
        File f = new File("C:/Users/Music Account/Documents/Mastery/Orders");
        pathnames = f.list();

        for (String pathname : pathnames) {
            System.out.println(pathname);
            String name = "Orders\\" + pathname;
            orderFiles.add(name);
        }
        System.out.println(orderFiles);
    }

    public File doesFileExist(LocalDate userInputOrderDate) throws Exception {
        fileOrders = new File("");
        String formattedDate = "";

        System.out.println("Importing files...");
        importFiles();
        System.out.println(orderFiles);

        // FORMAT USER INPUT DATE
        formattedDate = "Orders\\Orders_" + formatDateToString(userInputOrderDate) + ".txt";

        String a = "";
        // CHECK IF FILENAME CLASHES WITH ANOTHER IN THE 'ORDERS' DIRECTORY
        for (String currentFile : orderFiles) {
            // OVERWRITE AN EXISTING FILE
            System.out.println(currentFile);
            if (formattedDate.equals(currentFile)) {
                fileOrders = new File(formattedDate);
                System.out.println("File has been found.");
                a = currentFile;
            // CREATE A NEW FILE
            } else if (!formattedDate.equals(currentFile)) {
                System.out.println("File has not been found.");
                fileOrders = new File(formattedDate);
                a = currentFile;
            }
        }        

        if (!formattedDate.equals(a)) {
            return null;
        }

        System.out.println("f: " + fileOrders);

        return fileOrders;
    }

    

    // public void deleteFile() throws Exception {
    //     Scanner readFile = new Scanner(new BufferedReader(new FileReader("Orders\\test.txt")));
    //     System.out.println("running edeltingd");
    //     if (fileOrders.exists()) {
    //         System.out.println("running edeltingd");
    //         if (readFile.hasNextLine() != true) {
    //             System.out.println("running edeltingd");
    //             fileOrders.delete();
    //         }
    //     }
    // }
}

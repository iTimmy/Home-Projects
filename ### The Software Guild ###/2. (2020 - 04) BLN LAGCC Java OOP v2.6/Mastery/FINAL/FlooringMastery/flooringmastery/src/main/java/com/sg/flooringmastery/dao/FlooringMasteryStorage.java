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

    public void createNewFile(LocalDate orderDate) throws IOException {
        String newFileName = formatDateToString(orderDate);
        File newFile = new File("Orders\\Orders_" + newFileName + ".txt");
        newFile.createNewFile();
        orderFiles.add(newFile.toString());
    }

    public void deleteFileIfEmpty(File file) {
        file.delete();
    }

    private void importFiles() throws Exception {
        // ADD FILES FROM DIRECTORY TO LIST

        String[] pathnames;
        File f = new File("Orders");
        pathnames = f.list();

        for (String pathname : pathnames) {
            // System.out.println(pathname);
            String name = "Orders\\" + pathname;
            orderFiles.add(name);
        }
    }

    public File doesFileExist(LocalDate userInputOrderDate) throws Exception {
        fileOrders = new File("");
        String formattedDate = "";

        importFiles();

        // FORMAT USER INPUT DATE
        formattedDate = "Orders\\Orders_" + formatDateToString(userInputOrderDate) + ".txt";

        String a = "";
        // CHECK IF FILENAME CLASHES WITH ANOTHER IN THE 'ORDERS' DIRECTORY
        for (String currentFile : orderFiles) {
            if (formattedDate.equals(currentFile)) {
                fileOrders = new File(formattedDate);
                // System.out.println("File has been found.");
                a = currentFile;
                break;
            } else if (!formattedDate.equals(currentFile)) {
                // System.out.println("File has not been found.");
                fileOrders = new File(formattedDate);
                a = currentFile;
            }
        }        

        if (!formattedDate.equals(a)) {
            return null;
        }

        return fileOrders;
    }
}
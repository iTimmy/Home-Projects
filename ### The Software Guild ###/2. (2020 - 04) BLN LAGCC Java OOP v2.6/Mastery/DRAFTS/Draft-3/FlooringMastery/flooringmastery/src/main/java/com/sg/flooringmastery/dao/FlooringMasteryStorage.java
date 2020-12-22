package com.sg.flooringmastery.dao;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
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

    public LocalDate formatStringToDate(String date) {
        LocalDate orderOfDate = LocalDate.now();
        int i = 0;

        String yearString = "";
        String dayString = "";

        String dateSplit[] = date.split("-");
        for (String s : dateSplit) {
            if (i == 0) {
                yearString = s;
            } else if (i == 2) {
                dayString = s;
            }
            i++;
        }
        String dateMonth = dateSplit[1];
        int year = Integer.parseInt(yearString);
        int day = Integer.parseInt(dayString);

        orderOfDate = LocalDate.of(year, monthFormat(dateMonth), day);
        return orderOfDate;
    }

    public Month monthFormat(String dateMonth) {
        Month month = Month.JANUARY;
        if (dateMonth.equals("01")) {
            month = Month.JANUARY;
        } else if (dateMonth.equals("02")) {
            month = Month.FEBRUARY;
        } else if (dateMonth.equals("03")) {
            month = Month.MARCH;
        } else if (dateMonth.equals("04")) {
            month = Month.APRIL;
        } else if (dateMonth.equals("05")) {
            month = Month.MAY;
        } else if (dateMonth.equals("06")) {
            month = Month.JUNE;
        } else if (dateMonth.equals("07")) {
            month = Month.JULY;
        } else if (dateMonth.equals("08")) {
            month = Month.AUGUST;
        } else if (dateMonth.equals("09")) {
            month = Month.SEPTEMBER;
        } else if (dateMonth.equals("10")) {
            month = Month.OCTOBER;
        } else if (dateMonth.equals("11")) {
            month = Month.NOVEMBER;
        } else if (dateMonth.equals("12")) {
            month = Month.DECEMBER;
        } else {
            return null;
        }
        return month;
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
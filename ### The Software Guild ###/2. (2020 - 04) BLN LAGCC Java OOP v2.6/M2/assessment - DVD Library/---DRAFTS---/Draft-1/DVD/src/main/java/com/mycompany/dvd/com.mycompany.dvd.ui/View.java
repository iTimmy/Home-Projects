package com.mycompany.dvd.ui;

import com.mycompany.dvd.controller.*;
import com.mycompany.dvd.dto.*;
import java.util.*;
import java.io.*;

public class View {

    private UserIO io = new UserIOConsoleImpl();
    public final String DELIMITER = "   ::   ";
    
    public int display() {
        io.print("Main Menu" +
        "\n1. Add DVD" +
        "\n2. Remove DVD" +
        "\n3. Edit DVD" +
        "\n4. List DVD" +
        "\n5. Display DVD" +
        "\n6. Search DVD" +
        "\n7. Load DVD" +
        "\n8. Save DVD" +
        "\n9. Edit all DVDs" +
        "\n10. TERMINATE");

        return io.readInt("Please choose: ", 1, 10);
    }



    // ### 1: ADD DVD ### //
    public void displayAddDVD() throws Exception {
        PrintWriter out = new PrintWriter(new FileWriter("DVDdata.txt"));
        List<String> dvdInfo = new ArrayList<>();
        Map<String, List> dvdMap = new HashMap<>();


        // DVD newDVD = new DVD(dvd);
        // dvdList.add(newDVD);

        String title = io.readString("Title: ");
        String releaseDate = io.readString("Release Date: ");
        String rating = io.readString("MPAA Rating: ");
        String directorName = io.readString("Director's Name: ");
        String studio = io.readString("Studio: ");
        String notes = io.readString("Notes: ");

        dvdInfo.add(releaseDate);
        dvdInfo.add(rating);
        dvdInfo.add(directorName);
        dvdInfo.add(studio);
        dvdInfo.add(notes);
        System.out.println(dvdInfo);

        dvdMap.put(title, dvdInfo);

        Set<String> dvdKeys = dvdMap();

        for (String current: dvdKeys) {
            out.println("TITLE: " + title + DELIMITER + "RELEASE DATE: " + releaseDate + DELIMITER + "RATING: " + rating
                    + DELIMITER + "DIRECTOR NAME: " + directorName + DELIMITER + "NOTES: " + notes);

        }

        DVD initDVD = new DVD(title);
        initDVD.setTitle(title);
        initDVD.setReleaseDate(releaseDate);
        initDVD.setRating(rating);
        initDVD.setDirectorName(directorName);
        //
        initDVD.setNotes(notes);

        out.flush();
        out.close();

        io.print("________________________\n" + "Successfully added!\n\n\n");
    }

    // ### 2: REMOVE DVD ### //
    public void displayRemoveDVD() throws Exception {
        io.print("\n====== REMOVE DVD ======");

        PrintWriter out = new PrintWriter(new FileWriter("DVDdata.txt"));

        out.println("");
        io.print("________________________\n" +
        "Successfully removed!\n\n\n");
    }

    // ### 3: EDIT DVD ### //
    public void displayEditDVD() {
        io.print("\n====== EDIT DVD ======");
        io.readString("Please choose a DVD to edit by entering its title: ");
        // user enters title and 
    }

    // ### 4: LIST DVD ### //
    public void displayListDVD(List<DVD> dvdList) {
        io.print("\n====== LIST DVD ======");

        for (DVD initDVD : dvdList) {
            io.print(initDVD.getTitle() + "\n"
            + initDVD.getReleaseDate());
        }

        io.print("________________________\n" + 
        "Successfully listed!\n\n\n");
    }

    public void inputDVD() {
        String title = io.readString("Title: ");
    }

    // ### 5: DISPLAY DVD ### //
    public void displayDisplayDVD(DVD dvd) {
        io.print("\n====== DISPLAY DVD ======");

        inputDVD();

        io.print("Release Date: " + dvd.getReleaseDate());
        io.print("Director's Name: " + dvd.getDirectorName());
        io.print("Rating: " + dvd.getRating());
        io.print("Notes: " + dvd.getNotes());


        /*
        String st;
        while ((st = read.readLine()) != null) {
            System.out.println(st);
        }
        */
    }

    // ### 6: SEARCH DVD ### //
    public void displaySearchDVD() throws Exception {
        BufferedReader read = new BufferedReader(new FileReader("DVDdata.txt"));

        io.print("\n====== SEARCH DVD ======");
        io.readString("Title: ");

        String title = io.readString("Title: ");
        DVD initDVD = new DVD(title);

        io.print(initDVD.getReleaseDate());
        io.print(initDVD.getDirectorName());
        io.print(initDVD.getRating());
        io.print(initDVD.getNotes());

        String st;
        while ((st = read.readLine()) != null) {
            System.out.println(st);
        }
    }

    // ### 7: LOAD DVD ### //
    public void displayLoadDVD() throws Exception {
        BufferedReader read = new BufferedReader(new FileReader("DVDdata.txt"));

        io.print("\n====== LOAD DVD ======");

        String title = io.readString("Title: ");
        DVD initDVD = new DVD(title);

        io.print(initDVD.getReleaseDate());
        io.print(initDVD.getDirectorName());
        io.print(initDVD.getRating());
        io.print(initDVD.getNotes());

        String st;
        while ((st = read.readLine()) != null) {
            System.out.println(st);
        }
    }

    // ### 8: SAVE DVD ### //
    public void displaySaveDVD() {
        io.print("\n====== SAVE DVD ======");
        io.readString("Title: ");
    }

    // ### 9: EDIT ALL DVD(S) ### //
    public void displayEditAllDVD() {
        io.print("\n====== EDIT DVD ======");
        io.readString("Title: ");
    }

    // ### 10: TERMINATION ### //
    public void displayTermination() {
        io.print("\nTerminating...");
    }



    // ###### VALIDATION ###### //
    public void displayValidation() {

    }

}
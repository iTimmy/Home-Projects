package com.example.backend.view;

import com.example.backend.controller.*;
import com.example.backend.dto.*;
import java.util.*;
import java.io.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;






//@Component
public class DVDView {

    //@Autowired
    private UserIO io = new UserIOImpl();
    public final String DELIMITER = "   ::   ";
    
    // ------DISPLAY------
    public int display() {
        io.print("\n__________________________\n" + "Main Menu" + "\n1. Add DVD" + "\n2. Remove DVD" + "\n3. Edit DVD" + "\n4. List DVD"
                + "\n5. Display DVD" + "\n6. Search DVD" + "\n7. Load DVD" + "\n8. Save DVD" + "\n9. Edit all DVDs"
                + "\n10. TERMINATE");

        return io.readInt("\n________________\nPlease choose: ", 1, 10);
    }






// --------------------- DVD LOGIC --------------------- \\
    // >>> DVD IDENTITY <<< \\
    public DVD dvdInfo() throws Exception {
        // User inputs desired new DVD information
        String title = inputDVD();
        String releaseDate = io.readString("Release Date: ");
        String rating = io.readString("MPAA Rating: ");
        String directorName = io.readString("Director's Name: ");
        String studio = io.readString("Studio: ");
        String notes = io.readString("Notes: ");

        /*
        // Sets the newly created DVD information
        dvd.setTitle(title);
        dvd.setReleaseDate(releaseDate);
        dvd.setRating(rating);
        dvd.setDirectorName(directorName);
        dvd.setNotes(notes);
        */

        return new DVD(title, releaseDate, rating, directorName, notes);
    }






    // ### 1: ADD DVD ### \\
    public void displayAddDVD() throws Exception {
        io.print("\n________________________\n" + "Successfully added!\n\n\n");
    }






    // ### 2: REMOVE DVD ### \\
    public String displayRemoveDVD() {
        io.print("\n====== REMOVE DVD ======\n");
        // io.print("\n________________________\n" + "Successfully removed!\n\n\n");
        return io.readString("Title: ");
    }






    // ### 3: EDIT DVD ### \\
    public DVD displayEditDVD(DVD editDVD) {
        io.print("\n====== EDIT DVD ======\n");
 
        String title = io.readString("Edit title of " + editDVD.getTitle() + ": ");
        String releaseDate = io.readString("Edit release date of " + editDVD.getReleaseDate() + ": ");
        String rating = io.readString("Edit rating of " + editDVD.getRating() + ": ");
        String directorName = io.readString("Edit director's name of " + editDVD.getDirectorName() + ": ");
        String notes = io.readString("Edit notes of " + editDVD.getNotes() + ": ");

        editDVD.setTitle(title);
        editDVD.setReleaseDate(releaseDate);
        editDVD.setRating(rating);
        editDVD.setDirectorName(directorName);
        editDVD.setNotes(notes);
        
        return editDVD;
    }






    // ### 4: LIST DVD ### \\
    public void displayListDVD(List<DVD> dvdList) {
        io.print("\n====== LIST DVD ======\n");
            dvdList.stream();
        io.print("\n________________________\n" + "Successfully listed!\n\n\n");
    }






    public String inputDVD() {
        return io.readString("Title: ");
    }






    // ### 5: DISPLAY DVD ### \\
    public void displayDisplayDVD(DVD dvd) {
        io.print("\n====== DISPLAY DVD ======\n");

        String title = inputDVD();

        io.print(
        title + dvd.getTitle() + "\n" + 
        "Release Date: " + dvd.getReleaseDate() + "\n" + 
        "Rating: " + dvd.getRating() + "\n" + 
        "Director's Name: " + dvd.getDirectorName() + "\n" + 
        "Notes: " + dvd.getNotes()
        );
    }







    // ### 6: SEARCH DVD ### \\
    public void displaySearchDVD(DVD dvd) throws Exception {
        io.print("\n====== SEARCH DVD ======\n");

        String userInput = inputDVD();

        if (userInput.equals(dvd.getTitle())) {
            io.print(dvd.getTitle() + "\n" + dvd.getReleaseDate() + "\n" + dvd.getRating() + "\n"
                    + dvd.getDirectorName() + "\n" + dvd.getNotes());
        }
    }






    // ### 7: LOAD DVD ### \\
    public void displayLoadDVD(DVD dvd) throws Exception {
        BufferedReader read = new BufferedReader(new FileReader("DVDdata.txt"));

        io.print("\n====== LOAD DVD ======");

        String loadTitle = inputDVD();
        
        if (loadTitle.equals(dvd.getTitle())) {
            io.print("Release Date: " + dvd.getReleaseDate());
            io.print("Director's Name: " + dvd.getDirectorName());
            io.print("Rating: " + dvd.getRating());
            io.print("Notes: " + dvd.getNotes());
        } 

        String st;
        while ((st = read.readLine()) != null) {
            System.out.println(st);
        }
    }






    // ### 8: SAVE DVD ### \\
    public void displaySaveDVD() {
        io.print("\n====== SAVE DVD ======");
        io.readString("Title: ");
    }






    // ### 9: EDIT ALL DVD(S) ### \\
    public void displayEditAllDVD() {
        io.print("\n====== EDIT DVD ======");
        io.readString("Title: ");
    }






    // ### 10: TERMINATION ### \\
    public void displayTermination() {
        io.print("\nTerminating...");
    }






    // ###### VALIDATION ###### \\
    public void displayValidation() {
        io.print("Does not exist.");
    }

}
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
    private UserIO io;
    DVD dvd = new DVD();
    
    public DVDView (UserIO io) {
        this.io = io;
    }
    
    // ------DISPLAY------
    public int display() {
        io.print("\n__________________________\n" + 
        "Main Menu" + 
        "\n1. Add DVD" + 
        "\n2. Remove DVD" + 
        "\n3. Edit DVD" + 
        "\n4. List DVD" + 
        "\n5. Display DVD" + 
        "\n6. Search DVD" + 
        "\n7. <<< TERMINATE >>>");

        return io.readInt("\n________________\nPlease choose: ", 1, 7);
    }






// --------------------- DVD LOGIC --------------------- \\
    // >>> DVD IDENTITY <<< \\
    public DVD dvdInfo() throws Exception {
        // User inputs desired new DVD information
        String title = inputDVD();
        String releaseDate = io.readString("Release Date: ");
        String rating = io.readString("MPAA Rating: ");
        String directorName = io.readString("Director's Name: ");
        String notes = io.readString("Notes: ");

        dvd.setTitle(title);
        dvd.setReleaseDate(releaseDate);
        dvd.setRating(rating);
        dvd.setDirectorName(directorName);
        dvd.setNotes(notes);

        return new DVD(title, releaseDate, rating, directorName, notes);
    }






    // ### 1: ADD DVD ### \\
    public void displayAddDVDTitle() {
        io.print("\n====== ADD DVD ======\n");
    }
    public void displayAddDVD() throws Exception {
        io.print("\n________________________\n" + "Successfully added!\n\n\n");
    }






    // ### 2: REMOVE DVD ### \\
    public void displayRemoveDVDTitle() {
        io.print("\n====== REMOVE DVD ======\n");
    }
    public String displayRemoveDVD() {
        io.print("\n====== REMOVE DVD ======\n");
        // io.print("\n________________________\n" + "Successfully removed!\n\n\n");
        return io.readString("Title: ");
    }






    // ### 3: EDIT DVD ### \\
    public void displayEditDVDTitle() {
        io.print("\n====== EDIT DVD ======\n");
    }
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
    public void displayListDVDTitle() {
        io.print("\n====== LIST DVD ======\n");
    }
    public void displayListDVD(List<DVD> dvdList) {
        io.print(dvdList.toString());
            
        io.print("\n________________________\n" + "Successfully listed!\n\n\n");
    }






    public String inputDVD() {
        return io.readString("Title: ");
    }






    // ### 5: DISPLAY DVD ### \\
    public void displayDisplayDVDTitle() {
        io.print("\n====== DISPLAY DVD ======\n");
    }
    public void displayDisplayDVD(DVD dvd) {
        io.print(
        "Release Date: " + dvd.getReleaseDate() + "\n" + 
        "Rating: " + dvd.getRating() + "\n" + 
        "Director's Name: " + dvd.getDirectorName() + "\n" + 
        "Notes: " + dvd.getNotes()
        );
    }







    // ### 6: SEARCH DVD ### \\
    public void displaySearchDVDTitle() {
        io.print("\n====== SEARCH DVD ======\n");
    }
    public void displaySearchDVD(DVD dvd) throws Exception {
        if (inputDVD().equals(dvd.getTitle())) {
            io.print("Exists.");
        }
    }






    // ### 10: TERMINATION ### \\
    public void displayTermination() {
        io.print("\nTerminating...\n");
    }





    // ####### WARNING ####### \\
    public void displayOptionWarning() {
        io.print(" Your only options are 1-7! ");
    }

    // ###### VALIDATION ###### \\
    public void displayValidation() {
        io.print("Does not exist.");
    }

    // ###### VALIDATION ###### \\
    public void displayValidationExists() {
        io.print("This DVD already exists.");
    }
    
    public void displaySuccess() {
        io.print("\n___________\nSuccess!");
    }

}
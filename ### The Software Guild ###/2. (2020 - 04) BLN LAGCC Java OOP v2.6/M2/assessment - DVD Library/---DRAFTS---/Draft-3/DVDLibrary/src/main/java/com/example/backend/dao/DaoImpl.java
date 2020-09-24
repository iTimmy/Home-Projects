package com.example.backend.dao;

import com.example.backend.dto.*;
import com.example.backend.controller.*;
import java.util.*;
import java.io.*;

// TEMPORARY IMPORTS
import com.example.backend.view.*;
// -----------------

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class DaoImpl implements DVDdao {

    // @Autowired
    Map<String, DVD> storeDVD = new HashMap<>();
    List<String> DVDKeys = new ArrayList(storeDVD.keySet());
    List<DVD> DVDValueList = new ArrayList(storeDVD.values());

    // Set<DVD> storeDVDkeys = storeDVD.keySet();

    public static final String DELIMITER = "   ::   ";
    File file = new File("DVDdata.txt");

    // C - Create \\
    @Override
    public DVD addDVD(String dvdTitle, DVD dvd) throws Exception {
        DVD newDVD = storeDVD.put(dvdTitle, dvd);
        saveDVD();
        return newDVD;
    }

    // R - Read \\
    @Override
    public List<DVD> getAllDVDs() throws Exception {
        loadDVD();
        Set<String> dvdKeys = storeDVD.keySet();
        List<DVD> DVDKeyList = new ArrayList(storeDVD.keySet());

        for (String s : dvdKeys) {
            System.out.println("TITLE: " + s);
        }

        return DVDKeyList;
    }

    @Override
    public DVD getDVD(String dvd) throws Exception {
        Set<String> dvdKeys = storeDVD.keySet();
        loadDVD(dvd);

        for (String s : dvdKeys) {
            if (s.equals(dvd)) {
                // System.out.println("found" + storeDVD.get(dvd));
                return storeDVD.get(dvd);
            }
        }
        // System.out.println("not found");
        return null;
    }

    // U - Update \\
    @Override
    public void updateDVD(String dvd) throws Exception { // take in dvd obj
        DVD replaceDVD = new DVD();
        replaceDVD = new DVD(replaceDVD.getTitle(), replaceDVD.getReleaseDate(), replaceDVD.getDirectorName(),
                replaceDVD.getRating(), replaceDVD.getNotes());
        storeDVD.replace(dvd, replaceDVD);
    }
 // -----------------------   

    // D - Delete \\
    @Override
    public DVD deleteDVD(String dvd) throws Exception {
        loadDVD();
        DVD dvdObject = storeDVD.remove(dvd);
        saveDVD();
        return dvdObject;
    }

    private void loadDVD(String fetchTitle) throws Exception {
        Scanner fileRead = new Scanner(new BufferedReader(new FileReader(file)));

        String title = "";
        String releaseDate = "";
        String directorName = "";
        String rating = "";
        String notes = "";

        if (fileRead.hasNextLine() == true) {
            while (fileRead.hasNextLine()) {
                String currentLine = fileRead.nextLine();
                String[] dvdTokens = currentLine.split(DELIMITER);
                title = dvdTokens[0];
                releaseDate = dvdTokens[1];
                directorName = dvdTokens[2];
                rating = dvdTokens[3];
                notes = dvdTokens[4];
                DVD newDVD = new DVD(title);

                newDVD.setTitle(title);
                newDVD.setReleaseDate(releaseDate);
                newDVD.setDirectorName(directorName);
                newDVD.setRating(rating);
                newDVD.setNotes(notes);

                storeDVD.put(newDVD.getTitle(), newDVD);

                if (fetchTitle.equals(title)) {
                    break;
                }
            }
        }
    }

    private void saveDVD() throws Exception {
        PrintWriter fileWrite = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        Collection<DVD> storeDVDValues = storeDVD.values();
        String currentLine;
        for (DVD currentDVD : storeDVDValues) {
            currentLine = marshallDVD(currentDVD);
            fileWrite.println(currentLine);
            fileWrite.flush();
        }
        fileWrite.close();
    }

    private String marshallDVD(DVD dvdToString) {
        String DVDtext = dvdToString.getTitle() + DELIMITER;
        DVDtext += dvdToString.getReleaseDate() + DELIMITER;
        DVDtext += dvdToString.getDirectorName() + DELIMITER;
        DVDtext += dvdToString.getRating() + DELIMITER;
        DVDtext += dvdToString.getNotes();

        return DVDtext;
    }
    
    private void loadDVD() throws Exception {
        Scanner fileRead = new Scanner(new BufferedReader(new FileReader(file)));
        while(fileRead.hasNextLine()) {
            String currentLine = fileRead.nextLine();
            DVD getDVD = unmarshallDVD(currentLine);
            storeDVD.put(getDVD.getTitle(), getDVD);
        }
    }
    private DVD unmarshallDVD (String stringToDVD) {
        String[] dvdTokens = stringToDVD.split(DELIMITER);
        String title = dvdTokens[0];
        String releaseDate = dvdTokens[1];
        String directorName = dvdTokens[2];
        String rating = dvdTokens[3];
        String notes = dvdTokens[4];
        DVD setDVD = new DVD(title);
        setDVD.setReleaseDate(releaseDate);
        setDVD.setDirectorName(directorName);
        setDVD.setRating(rating);
        setDVD.setNotes(notes);

        return setDVD;
    }
}
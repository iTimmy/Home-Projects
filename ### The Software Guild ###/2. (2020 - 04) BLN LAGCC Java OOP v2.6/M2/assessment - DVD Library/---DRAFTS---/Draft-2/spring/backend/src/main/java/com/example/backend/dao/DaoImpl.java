package com.example.backend.dao;

import com.example.backend.dto.*;
import java.util.*;
import java.io.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class DaoImpl implements DVDdao {

    //@Autowired
    private List<DVD> storeDVD = new ArrayList<>();
    private DVD DVD = new DVD();

    public static final String DELIMITER = "   ::   ";
    public static final String DVDFile = "DVDdata.txt";





    // C - Create \\
    @Override
    public DVD addDVD(DVD dvd) throws Exception {
        storeDVD.add(dvd);
        return dvd;
    }




    // R - Read \\
    @Override
    public List<DVD> getAllDVDs() throws Exception {
        System.out.println("ok" + storeDVD);
        return storeDVD;
    }

    @Override
    public DVD getDVD(String dvd) {
        for (DVD current : storeDVD) {
            if (current.getTitle().equals(dvd)) {
                System.out.println(current);
                return current;
            }
        }
        return null;
    }




    // U - Update \\
    @Override
    public DVD updateDVD(DVD dvdInfo) {
        for (DVD current : storeDVD) {
            if (current.getTitle().equals(dvdInfo)) {
                storeDVD.remove(current);
                storeDVD.add(dvdInfo);
            }
        }
        return null;
    }




    // D - Delete \\
    @Override
    public DVD deleteDVD(String dvd) {
        for (DVD current : storeDVD) {
            if (current.equals(current)) {
                storeDVD.remove(current);
            }
        }
        return null;
    }




    public DVD loadDVD() throws Exception {
        // BufferedReader read = new BufferedReader(new FileReader("DVDdata.txt"));
        Scanner readFile = new Scanner(new BufferedReader(new FileReader(DVDFile)));

        /*
        String st;
        while ((st = read.readLine()) != null) {
        System.out.println("\n" + st);

        */
        return loadDVD();
    }
    



    public DVD saveDVD() throws Exception {
        
        PrintWriter fileWrite = new PrintWriter(new FileWriter(DVDFile));
        Set<String> DVDsave = getSet(storeDVD);
            for (String current : DVDsave) {
                fileWrite.println(current);
            }
        
        
        return null;
    }
    
}
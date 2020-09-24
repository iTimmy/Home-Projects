package com.mycompany.dvd.dao;

import com.mycompany.dvd.dto.*;
import java.util.*;

public class DaoFileImpl implements Dao {

    private Map<String, DVD> Dvd = new HashMap<>();
    private List<String> dvdList = new ArrayList<>();

    public static final String DELIMITER = "   ::   ";
    public static final String DVDFile = "DVDdata.txt";

    @Override
    public List<DVD> getAllDVDs() {
        return new ArrayList<DVD>(Dvd.values());
    }

    @Override
    public DVD getDVD(String dvd) {
        return Dvd.get(dvd);
    }

    @Override
    public DVD removeDVD(String dvd) {
        DVD removedDVD = Dvd.remove(dvd);
        return removedDVD;
    }

    @Override
    public DVD loadDVD() {
        return (DVD) Dvd;
    }

    @Override
    public DVD saveDVD() {
        return (DVD) Dvd;
    }

    @Override
    public String marshalling() {
        return (List<DVD>) Dvd;
    }

    @Override
    public String unmarshalliing() {
        return prt;
    }

}
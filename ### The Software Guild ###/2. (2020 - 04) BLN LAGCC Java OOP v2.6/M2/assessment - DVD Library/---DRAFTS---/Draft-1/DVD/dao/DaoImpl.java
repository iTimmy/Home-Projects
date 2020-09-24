package dao;

import dto.*;
import java.util.*;
import java.io.*;

@Component
public class DaoImpl implements DVDdao {

    private Map<String, DVD> Dvd = new HashMap<>();
    private List<DVD> dvdList = new ArrayList<>();
    DVD dvd = new DVD("");

    public static final String DELIMITER = "   ::   ";
    public static final String DVDFile = "DVDdata.txt";

    @Override
    public List<DVD> getAllDVDs() throws Exception {
        Scanner readFile = new Scanner(new BufferedReader(new FileReader("DVDdata.txt")));
        return new ArrayList<DVD>(Dvd.values());
    }

    @Override
    public DVD getDVD(String dvd) {
        return Dvd.get(dvd);
    }

    @Override
    public DVD deleteDVD(String dvd) {
        dvdList.remove(dvd);
        return dvd;
    }

    @Override
    public DVD updateDVD(DVD newDVD) {
        return newDVD;
    }

}
package com.example.backend.dao;

import com.example.backend.dto.*;
import java.util.*;

public interface DVDdao {

    DVD addDVD(String dvdTitle, DVD dvd) throws Exception;

    List<DVD> getAllDVDs() throws Exception;

    DVD getDVD(String title) throws Exception;

    void updateDVD(DVD newDVD, String oldTitle) throws Exception;

    DVD deleteDVD(String dvd) throws Exception;
    
}
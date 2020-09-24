package com.example.backend.dao;

import com.example.backend.dto.*;
import java.util.*;

public interface DVDdao {

    DVD addDVD(DVD dvd) throws Exception;

    List<DVD> getAllDVDs() throws Exception;

    DVD getDVD(String title);

    DVD updateDVD(DVD dvdInfo);

    DVD deleteDVD(String dvd);

    DVD loadDVD() throws Exception;

    DVD saveDVD() throws Exception;
    
}
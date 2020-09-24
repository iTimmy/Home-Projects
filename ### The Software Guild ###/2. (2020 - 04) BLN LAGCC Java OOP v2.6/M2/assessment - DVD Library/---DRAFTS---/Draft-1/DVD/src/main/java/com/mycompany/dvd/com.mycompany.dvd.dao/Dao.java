package com.mycompany.dvd.dao;

import com.mycompany.dvd.dto.*;
import java.util.*;

public interface Dao {
    List<DVD> getAllDVDs();
    DVD getDVD(String title, DVD dvd);
    DVD updateDVD();
    DVD deleteDVD(String dvd);

    DVD loadDVD();
    DVD saveDVD();

    String marshalling();
    String unmarshalling();
}
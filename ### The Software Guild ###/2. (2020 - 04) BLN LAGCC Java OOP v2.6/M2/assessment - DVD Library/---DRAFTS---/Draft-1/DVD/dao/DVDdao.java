package dao;

import dto.*;
import java.util.*;

public interface DVDdao {
    List<DVD> getAllDVDs() throws Exception;

    DVD getDVD(String title);

    DVD updateDVD(DVD newDVD);

    DVD deleteDVD(String dvd);

   //  DVD loadDVD();

   //  DVD saveDVD();

    // String marshalling();

    // String unmarshalling();
}
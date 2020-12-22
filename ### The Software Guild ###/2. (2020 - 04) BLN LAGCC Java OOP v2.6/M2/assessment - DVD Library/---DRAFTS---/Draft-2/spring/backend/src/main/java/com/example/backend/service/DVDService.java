package com.example.backend.service;

import com.example.backend.dao.*;
import com.example.backend.dto.*;
import com.example.backend.controller.*;
import java.util.*;

public interface DVDService {
    
    public DVD createDVD(DVD dvd) throws Exception;

    public DVD getDVD(String dvd) throws
        DVDDaoPersistanceException, 
        DVDDaoDuplicateIdException, 
        DVDDaoDataValidationException;

    public List<DVD> getAllDVDs() throws Exception;

    public void updateDVD(DVD dvd) throws 
        DVDDaoPersistanceException;

    public void deleteDVD(String dvd) throws 
        DVDDaoPersistanceException;

    public void validationDuplication();

    public void validationException() throws 
        DVDDaoDataValidationException;

    public void audit();

}
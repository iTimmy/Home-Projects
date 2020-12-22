package com.example.backend.service;

import com.example.backend.dao.*;
import com.example.backend.dto.*;
import com.example.backend.service.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class DVDServiceImpl implements DVDService {
    
    private DaoImpl dao = new DaoImpl();
    private AuditDao auditDao = new AuditDaoImpl();

    //@Autowired
    public DVDServiceImpl(DaoImpl dao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    public DVDServiceImpl() {
    }

    @Override
    public DVD createDVD(DVD dvd) throws Exception {
        return dao.addDVD(dvd);
    }

    @Override
    public List<DVD> getAllDVDs() throws Exception {
        return dao.getAllDVDs();
    }

    @Override
    public DVD getDVD(String dvd) throws
        DVDDaoPersistanceException,
        DVDDaoDuplicateIdException, 
        DVDDaoDataValidationException {
        return dao.getDVD(dvd);
    }

    @Override
    public void deleteDVD(String dvd) throws DVDDaoPersistanceException {
        dao.deleteDVD(dvd);
    }

    @Override
    public void updateDVD(DVD dvd) throws DVDDaoPersistanceException {
        dao.updateDVD(dvd);
    }

    @Override
    public void validationDuplication() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void validationException() throws DVDDaoDataValidationException {
        /*
        if (student.getFirstName() == null || student.getFirstName().trim().length() == 0
                || student.getLastName() == null || student.getLastName().trim().length() == 0
                || student.getCohort() == null || student.getCohort().trim().length() == 0) {

            throw new ClassRosterDataValidationException(
                    "ERROR: All fields [First Name, Last Name, Cohort] are required.");
        }
        */
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void audit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
package com.example.backend.controller;

import com.example.backend.view.*;
import com.example.backend.dao.*;
import com.example.backend.dto.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// @Component
public class DVDController {

    // @Autowired
    private DVDView view;
    private DVDdao dao;
    
    public DVDController(DVDView view, DVDdao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void run() throws Exception {
        int select = 0;

        while (select != 7) {
            loadDVD();
            select = view.display();
            try {
                switch (select) {
                    case 1:
                        addDVD();
                        break;
                    case 2:
                        removeDVD();
                        break;
                    case 3:
                        editDVD();
                        break;
                    case 4:
                        listDVD();
                        break;
                    case 5:
                        displayDVD();
                        break;
                    case 6:
                        searchDVD();
                        break;
                    case 7:
                        termination();
                        break;
                    default:
                        view.displayOptionWarning();
                }
            } catch (DVDDaoException e) {
                System.out.println("You need to put in a valid number.");
            }
        }
    }
    





    private void addDVD() throws Exception {
        view.displayAddDVDTitle();
        DVD newDVD = view.dvdInfo();
        String newTitle = newDVD.getTitle();
        if (dao.getDVD(newTitle) == null) {
            dao.addDVD(newDVD.getTitle(), newDVD);
            view.displayAddDVD();
        } else {
            view.displayValidationExists();
        }
    }

    private void removeDVD() throws Exception {
        view.displayRemoveDVDTitle();
        String title = view.inputDVD();
        if (dao.deleteDVD(title) != null) {
            view.displaySuccess();
        } else {
            view.displayValidation();;
        }
    }

    private void editDVD() throws Exception {
        view.displayEditDVDTitle();
        String editedDVD = view.inputDVD();
        DVD edit = dao.getDVD(editedDVD);
        if (edit != null) {
            // logic belongs in dao
            dao.deleteDVD(editedDVD);
            edit = view.displayEditDVD(edit);
            dao.addDVD(edit.getTitle(), edit);
            System.out.println("finished");
            // ----------------
            //dao.updateDVD(edit);
        } else {
            view.displayValidation();
        }
    }

    private void listDVD() throws Exception {
        view.displayListDVDTitle();
        List<DVD> listDVDs = dao.getAllDVDs();
        view.displayListDVD(listDVDs);
    }

    private void displayDVD() throws Exception, DVDDaoException {
        view.displayDisplayDVDTitle();
        String title = view.inputDVD();
        DVD dvdDisplay = dao.getDVD(title);
        if (dvdDisplay != null) {
            view.displayDisplayDVD(dvdDisplay);
        } else {
            view.displayValidation();
        }
    }

    private void searchDVD() throws Exception {
        view.displaySearchDVDTitle();
        String input = view.inputDVD();
        if (dao.getDVD(input) != null) {
            view.displaySuccess();
        } else {
            view.displayValidation();
        }
    }

    private void loadDVD() throws Exception {
        //dao.updateDVD();
    }

    private void termination() {
        view.displayTermination();
    }

}
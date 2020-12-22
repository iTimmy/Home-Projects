package com.example.backend.controller;

import com.example.backend.view.*;
import com.example.backend.service.*;
import com.example.backend.dto.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class DVDController {

    //@Autowired
    private DVDView view = new DVDView();
    private DVDService service = new DVDServiceImpl();

    public void run() throws Exception {
        int select = 0;

        while (select != 10) {
            select = view.display();

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
                    //loadDVD();
                    break;
                case 8:
                    saveDVD();
                    break;
                case 9:
                    editAllDVD();
                    break;
                case 10:
                    break;
                default:
                    System.out.println("what?");
            }
        }
        termination();

    }





    private void addDVD() throws Exception {
        DVD newDVD = view.dvdInfo();
        service.createDVD(newDVD);
        view.displayAddDVD();
    }

    private void removeDVD() throws Exception {
        String deletingDVD = view.displayRemoveDVD();
        DVD deletedDVD = service.getDVD(deletingDVD);
        if (deletedDVD != null) {
            service.deleteDVD(deletingDVD);
        } else {
            view.displayValidation();
        }
    }

    private void editDVD() throws Exception {
        String editedDVD = view.inputDVD();
        DVD edit = service.getDVD(editedDVD);
        if (edit != null) {
            edit = view.displayEditDVD(edit);
            service.updateDVD(edit);
        } else {
            view.displayValidation();
        }
    }

    private void listDVD() throws Exception {
        List<DVD> dvdList = service.getAllDVDs();
        view.displayListDVD(dvdList);
    }

    private void displayDVD() throws Exception {
        String title = view.inputDVD();
        DVD dvd = service.getDVD(title);
        if (dvd != null) {
            view.displayDisplayDVD(dvd);
        } else
        view.displayValidation();
    }

    private void searchDVD() throws Exception {
        String input = view.inputDVD();
        DVD searchedDVD = service.getDVD(input);
        view.displaySearchDVD(searchedDVD);
    }

    private void loadDVD(DVD dvd) throws Exception {
        //view.displayLoadDVD();
    }

    private void saveDVD() throws Exception {
        view.displaySaveDVD();
        // DVD newDVD = view.newDVD();
    }

    private void editAllDVD() {
        view.displayEditAllDVD();
    }

    private void termination() {
        view.displayTermination();
    }

}
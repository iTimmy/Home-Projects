package controller;

import java.time.LocalDate;
import java.util.*;

import service.*;
import view.*;
import dto.*;

public class FlooringMasteryController {
    FlooringMasteryView view = new FlooringMasteryView();
    FlooringMasteryService service = new FlooringMasteryServiceImpl();

    /*
    public FlooringMasteryController(FlooringMasteryView view) {
        this.view = view;
    } */

    boolean load = false;
    boolean save = true;

    public void run() throws Exception {
        int select = 0;
        
        while(select != 6) {
            view.displayMenu();
            select = view.displaySelection();

            switch (select) {
                case 1:
                    displayOrders(load);
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    exportAllData(save);
                    break;
                case 6:
                    quit();
                    break;
                default:
                    System.out.println("ok");
            }
        }
    }

    public void displayOrders(boolean load) throws Exception {
        view.displayDisplayOrdersTitle();
        view.displayLoadProgress();
        List<Order> listOrders = service.getAllOrders(load);
        view.displayDisplayOrders(listOrders);
    }

    public void addOrder() {
        Order newOrder = view.displayAddEditOrder();
        view.displaySaveProgress();
        service.createOrder(newOrder);
        view.displaySuccess();
    }

    public void editOrder() {
        view.triggerEdit();
        /*
        view.displayEditOrder();
        LocalDate userInputDate = view.displayDisplayOrders();
        if (service.getOrderByID(userInputDate) != null) {
          
        } else {

        } */
    }

    public void removeOrder() {
        view.displayRemoveOrder();
    }
    
    public void exportAllData(boolean save) throws Exception {
        view.displayExportAllData();
        if (service.getAllOrders(save) != null) {
            view.displaySaveProgress();
            view.displaySuccess();
        } else if (service.getAllOrders(save) == null) {
            view.displayExportAllDataErrorMSG();
        }
    }

    public void quit() {
        view.displayQuit();
    }
}

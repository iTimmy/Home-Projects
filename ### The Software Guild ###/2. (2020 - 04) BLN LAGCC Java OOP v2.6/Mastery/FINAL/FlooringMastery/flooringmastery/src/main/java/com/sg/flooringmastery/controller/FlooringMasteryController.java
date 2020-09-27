package com.sg.flooringmastery.controller;

import java.time.LocalDate;
import java.util.*;
import com.sg.flooringmastery.service.*;
import com.sg.flooringmastery.view.*;
import com.sg.flooringmastery.dto.*;

public class FlooringMasteryController {
    FlooringMasteryView view;
    FlooringMasteryService service;

     public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryServiceImpl service) {
         this.view = view;
         this.service = service;
     } 

    Order newOrder = new Order();
    boolean editMode = false;
    LocalDate saveOrderDate;

    public void run() throws Exception {
        int select = 0;
        
        while(select != 6) {
            view.displayMenu();
            select = view.displaySelection();

            switch (select) {
                case 1:
                    displayOrders();
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
                    exportAllData(newOrder);
                    break;
                case 6:
                    quit();
                    break;
                default:
                    view.displayInvalidSelection();
                    break;
            }
        }
    }

    public void displayOrders() throws Exception {
        LocalDate userInputOrderDate = view.displayDisplayOrdersTitle();
        searching();
            //service.getOrderByDate(userInputOrderDate);
            System.out.println("############################################################| " + userInputOrderDate + " |#############################################################");
            List<Order> listOrders = service.getOrdersByDate(userInputOrderDate);
            view.displayDisplayOrders(listOrders);
        
    }

    public void addOrder() {
        // DISPLAY PRODUCTS \\
        List<Product> listProducts = service.getAllProducts();
        List<Tax> listTaxes = service.getAllTaxes();
        // TOGGLES ADD OR EDIT MODE \\
        view.displayAddEditOrderTitle();
        if (editMode == true) {
            saveOrderDate = newOrder.getOrderDate();
        }
        newOrder = view.displayAddEditOrder(listProducts, listTaxes);
        if (editMode == true) {
            newOrder.setOrderDate(saveOrderDate);
        }
        view.displaySaveProgress();
        service.createOrder(newOrder);
        view.displaySuccess();
        editMode = false;

        view.displayCurrentOrder(newOrder);
    }

    public void editOrder() throws Exception {
        if (newOrder.getOrderDate() == null) {
            view.displayPleaseAddOrderFirst();
        } else {
            List<Order> listOrders = service.getOrdersByDate(newOrder.getOrderDate());
            view.displayDisplayOrders(listOrders);
            // displayOrders();
            selectedFileToDisplay();
            view.triggerEdit();
            editMode = true;
            addOrder();
        }
    }

    public void removeOrder() throws Exception {
        boolean valid = false;
        while(valid != true) {
            LocalDate findOrderDate = view.displayInputDate();
            if (service.getOrdersByDate(findOrderDate) == null) {
                System.out.println("This file order doesn't exist.");
                valid = false;
            } else if (service.getOrdersByDate(findOrderDate) != null) {
                valid = true;
            }
        }
        Order findOrder = view.displayRemoveOrder();
        searching();
        service.deleteOrder(findOrder);
        valid = true;
    }

    public void selectedFileToDisplay() {
        view.displayCurrentOrder(newOrder);
    }
    
    public void exportAllData(Order newOrder) throws Exception {
        // TITLE \\
        view.displayExportAllData();
        // SERVICE \\
        if (newOrder.getOrderDate() != null) {
            selectedFileToDisplay();
            if (service.createOrder(newOrder) == null) {
                view.displayUnavailableState();
            } 
            service.createOrder(newOrder);
        }
        if (service.saveOrdersByDate(newOrder.getOrderDate()) == false) {
            view.displayExportAllDataErrorMSG();
        } else {
            view.displaySaveProgress();
            view.displaySuccess();
        }
    }

    public void quit() {
        view.displayQuit();
    }









    private void searching() {
        System.out.println("[1] Searching...");
    }
}


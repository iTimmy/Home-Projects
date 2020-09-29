package com.sg.flooringmastery.controller;

import java.time.LocalDate;
import java.util.*;
import com.sg.flooringmastery.service.*;
import com.sg.flooringmastery.view.*;
import com.sg.flooringmastery.dto.*;

public class FlooringMasteryController {
    private FlooringMasteryView view;
    private FlooringMasteryService service;

     public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryServiceImpl service) {
         this.view = view;
         this.service = service;
     } 

    private Order newOrder = new Order();
    private boolean editMode = false;
    private LocalDate saveOrderDate;
    private boolean doShow = false;

    public void run() throws Exception {
        int select = 0;
        
        while(select != 6) {
            displayCurrentOrderSelection(newOrder);
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

    public void displayCurrentOrderSelection(Order newOrder) {
        try {
            if (newOrder != null) {
                if (doShow == true) {
                    view.displayCurrentOrder(newOrder);
                }
            }
        } catch (Exception e) {
            System.out.println("null for now");
        }
    }

    public void displayOrders() throws Exception {
        view.displayDisplayOrdersTitle();
        LocalDate userInputOrderDate = view.displayExistingInputDate();
        searching();
            List<Order> listOrders = service.getOrdersByDate(userInputOrderDate);
            if (listOrders == null) {
                view.displayDoesNotExist();
            } else {
                System.out.println("############################################################| " + userInputOrderDate + " |#############################################################");
                view.displayDisplayOrders(listOrders);
            }
    }

    public Order addOrder() throws Exception {
        doShow = true;
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
        view.displaySuccess();
        editMode = false;
        
        // will export
        if (editMode == true) {
            service.createOrder(newOrder);
        }
        return newOrder;
    }

    public void editOrder() throws Exception {
        doShow = true;
        if (newOrder.getOrderDate() == null) {
            view.displayPleaseAddOrderFirst();
        } else {
            // displays orders in file (what you have thus far)
            List<Order> listOrders = service.getOrdersByDate(newOrder.getOrderDate());
            view.displayDisplayOrders(listOrders);

            selectedFileToDisplay();
            view.triggerEdit();
            editMode = true;

            newOrder = addOrder();
            service.updateOrder(newOrder);
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
            // displays current added order if done so
            selectedFileToDisplay();
            // if you export before adding an order, it will result in an error
            if (service.createOrder(newOrder) == null) {
                view.displayUnavailableState();
            } 
        }
        if (service.saveOrdersByDate() == true) {
            view.displaySaveProgress();
            view.displaySuccess();
        } else {
            view.displayExportAllDataErrorMSG();
        }
    }

    public void quit() {
        view.displayQuit();
    }









    private void searching() {
        System.out.println("[1] Searching...");
    }
}


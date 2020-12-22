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
    private boolean doShow = false;

    public void run() throws Exception {
        int select = 0;
        
        while(select != 6) {
            displayCurrentOrderSelection();
            if (newOrder.getOrderDate() != null) {
                // displays current added order if done so
                selectedFileToDisplay();
            }
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

    public void displayCurrentOrderSelection() throws Exception {
        List<Order> currentOrders = service.getActiveOrders();
        try {
            if (currentOrders != null) {
                if (doShow == true) {
                    view.displayCurrentOrder(currentOrders);
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
                view.displayDisplayOrdersBanner(userInputOrderDate);
                view.displayDisplayOrders(listOrders);
            }
    }

    public Order addOrder() throws Exception {
        doShow = true;
        // DISPLAY PRODUCTS \\
        List<Product> listProducts = service.getAllProducts();
        List<Tax> listTaxes = service.getAllTaxes();
        // TOGGLES ADD OR EDIT MODE \\

        newOrder = view.displayAddOrder(listProducts, listTaxes);

        Order order = service.createOrder(newOrder);
        view.returnCalculations(order);
        view.displaySaveProgress();
        view.displaySuccess();
           
        return newOrder;
    }

    public void editOrder() throws Exception {
        doShow = false;
        // DISPLAY PRODUCTS \\
        List<Product> listProducts = service.getAllProducts();
        List<Tax> listTaxes = service.getAllTaxes();
        view.displayEditOrderTitle();
        LocalDate date = view.displayExistingInputDate();
            // displays orders in file (what you have thus far)
            // existing order in memory (LocalDate.now() is temporary instantiation)
            List<Order> listOrders = service.getOrdersByDate(date);

            // creates a new order
            Order editedOrder = view.displayEditOrder(listProducts, listTaxes, listOrders, date);

            // fetches the order number from the existing orders in memory
            Order existingOrder = service.getOrderByID(editedOrder.getOrderNumber());

            service.updateOrder(editedOrder, existingOrder);
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

    public void selectedFileToDisplay() throws Exception {
        view.displayAddedOrder(newOrder);
    }
    
    public void exportAllData(Order newOrder) throws Exception {
        // TITLE \\
        view.displayExportAllData();
        // SERVICE \\
        if (service.saveOrdersByDate() == true) {
            doShow = true;
            view.displaySaveProgress();
            view.displaySuccess();
        } else {
            doShow = false;
            // if you export before adding an order, it will result in an error
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


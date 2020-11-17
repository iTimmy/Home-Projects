package com.sg.flooringmastery.controller;

import java.time.LocalDate;
import java.util.*;
import com.sg.flooringmastery.service.*;
import com.sg.flooringmastery.view.*;
import com.sg.flooringmastery.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlooringMasteryController {
    private FlooringMasteryView view;
    private FlooringMasteryService service;

    @Autowired
    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryServiceImpl service) {
        this.view = view;
        this.service = service;
    } 

    private Order newOrder = new Order();
    private boolean doShow = false;

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
        // VIEW \\
        view.displayDisplayOrdersTitle();
        LocalDate userInputOrderDate = view.displayExistingInputDate();
        // SERVICE \\
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
        // VIEW \\
        List<Product> listProducts = service.getAllProducts();
        List<Tax> listTaxes = service.getAllTaxes();
        newOrder = view.displayAddOrder(listProducts, listTaxes);
        
        Order order = service.createOrder(newOrder); // calculations occur here so it has to go through the service layer before confirming to create
        view.returnCalculations(order); 
        if (view.displayConfirmation("create") == true) {
            view.displaySaveProgress();
            view.displaySuccess();
        } else {
            service.deleteOrder(order); // if user hits 'n' or any other key than 'y', the order they just created (that passed thru the service) will be deleted, not convenient but it works
        }
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
            Order existingOrder = service.getOrderByID(editedOrder.getOrderNumber());
            
            view.returnCalculations(editedOrder);
            if (view.displayConfirmation("update") == true) {
                // fetches the order number from the existing orders in memory
                service.updateOrder(editedOrder, existingOrder);
                view.displaySaveProgress();
                view.displaySuccess();
            } else {
            }
    }

    public void removeOrder() throws Exception {
        boolean valid = false;
        while(valid != true) {
            LocalDate findOrderDate = view.displayInputDate();
            List<Order> listOrders = service.getOrdersByDate(findOrderDate);
            view.displayDisplayOrders(listOrders);
                if (service.getOrdersByDate(findOrderDate) == null) {
                    //System.out.println("This file order doesn't exist.");
                    valid = false;
                } else if (service.getOrdersByDate(findOrderDate) != null) {
                    valid = true;
                }
        }
        Order findOrder = view.displayRemoveOrder();
        if (view.displayConfirmation("delete") == true) {
            service.deleteOrder(findOrder);
        }
        valid = true;
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
}


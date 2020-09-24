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
            List<Order> listOrders = service.getAllOrders(userInputOrderDate);
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
        view.displaySuccess();
        editMode = false;

        System.out.println("==================================\n" +
        newOrder.getOrderDate() + " | " +
        newOrder.getCustomerName() + " | " +
        newOrder.getTax().getState() + " | " +
        newOrder.getProduct().getProductType() + " | " +
        newOrder.getArea() +
        "\n=================================="
        );
    }

    public void editOrder() throws Exception {
        if (newOrder.getOrderDate() == null) {
            view.displayPleaseAddOrderFirst();
        } else {
            List<Order> listOrders = service.getAllOrders(newOrder.getOrderDate());
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
            if (service.getOrderByDate(findOrderDate) == false) {
                System.out.println("This file order doesn't exist.");
                valid = false;
            } else if (service.getOrderByDate(findOrderDate) == true) {
                valid = true;
            }
        }
        Order findOrder = view.displayRemoveOrder();
        searching();
        service.deleteOrder(findOrder);
        valid = true;
    }

    public void selectedFileToDisplay() {
        System.out.println("=====================================\n" +
        newOrder.getOrderDate() + " | " + 
        newOrder.getCustomerName() + " | " + 
        newOrder.getTax().getState() + " | " + 
        newOrder.getProduct().getProductType() + " | " + 
        newOrder.getArea() + "\n=====================================");
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
        if (service.saveAllOrders(newOrder.getOrderDate()) == false) {
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


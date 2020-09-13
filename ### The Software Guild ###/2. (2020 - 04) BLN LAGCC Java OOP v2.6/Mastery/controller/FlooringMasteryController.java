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
                    System.out.println("ok");
            }
        }
    }

    public void displayOrders() throws Exception {
        LocalDate userInputOrderDate = view.displayDisplayOrdersTitle();
        searching();
            service.getOrderByDate(userInputOrderDate);
            System.out.println("############################################################| " + userInputOrderDate + " |#############################################################");
            List<Order> listOrders = service.getAllOrders(userInputOrderDate);
            view.displayDisplayOrders(listOrders);
        
    }

    public void addOrder() {
        // DISPLAY PRODUCTS \\
        List<Product> listProducts = service.getAllProducts();
        // TOGGLES ADD OR EDIT MODE \\
        view.displayAddEditOrderTitle();
        if (editMode == true) {
            saveOrderDate = newOrder.getOrderDate();
        }
        newOrder = view.displayAddEditOrder(listProducts);
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
            displayOrders();
            selectedFileToDisplay();
            view.triggerEdit();
            editMode = true;
            addOrder();
        }
    }

    public void removeOrder() {
        Order findOrder = view.displayRemoveOrder();
        searching();
        service.deleteOrder(findOrder);
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
            service.createOrder(newOrder);
        }
        view.displaySaveProgress();
        if (service.saveAllOrders(newOrder.getOrderDate()) == false) {
            view.displayExportAllDataErrorMSG();
        } else if (service.saveAllOrders(newOrder.getOrderDate()) == true) {
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


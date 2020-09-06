package com.mycompany.vendingmachine.controller;

import java.util.*;
import com.mycompany.vendingmachine.view.*;
import com.mycompany.vendingmachine.service.*;
import com.mycompany.vendingmachine.dto.*;

public class VendingMachineController {

    // private VendingMachineView view = new VendingMachineView();
    // private VendingMachineService service = new VendingMachineServiceImpl();

    private VendingMachineView view;
    private VendingMachineService service;

    public VendingMachineController(VendingMachineView view, VendingMachineService service) {
        this.view = view;
        this.service = service;
    }

    public void run() throws Exception {
        int select = 0;

        while (select != 2) {
            listItems();
            view.display();
            select = userSelection();

            switch (select) {
                case 1:
                    buyItem();
                    break;
                case 2:
                    exit();
                    break;
            }
        }

    }

    private int userSelection() {
        return view.displayUserSelection();
    }

    private List<VendingMachine> listItems() {
        service.getAllItems();
        view.displayListItems(service.getAllItems());
        return service.getAllItems();
    }

    private void buyItem() throws Exception {
        double userInputMoney = view.displayUserInputMoney(); //2
        String userInputItemName = view.displayBuyItem().toUpperCase();
        int userInputItemQuantity = view.displayBuyItemQuantity();
        if (service.getItem(userInputItemName, userInputItemQuantity, userInputMoney) == 1) {
            service.moneyCalculation(MathOperator.MINUS, userInputMoney, userInputItemName);
            view.displaySuccess();
        } else if (service.getItem(userInputItemName.toUpperCase(), userInputItemQuantity, userInputMoney) == 2) {
            view.displayItemDoesNotExistMSG();
        } else if (service.getItem(userInputItemName.toUpperCase(), userInputItemQuantity, userInputMoney) == 0) {
            view.displayNotEnoughMoneyMSG();
        }
    }

    private void exit() {
        System.out.println("Exiting...");
    }

}
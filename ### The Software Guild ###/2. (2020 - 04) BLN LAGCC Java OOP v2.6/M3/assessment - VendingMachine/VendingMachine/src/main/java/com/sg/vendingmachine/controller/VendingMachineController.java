package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dto.VendingMachine;
import com.sg.vendingmachine.dto.CoinsReturned;
import com.sg.vendingmachine.dto.UserWallet;
import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.view.VendingMachineView;
import com.sg.vendingmachine.dao.VendingMachineInsufficientFundsException;
import java.util.*;
import java.io.IOException;
import java.math.BigDecimal;

public class VendingMachineController {

    // private VendingMachineView view = new VendingMachineView();
    // private VendingMachineService service = new VendingMachineServiceImpl();

    private VendingMachineView view;
    private VendingMachineService service;
    private static int select = 0;

    public VendingMachineController(VendingMachineView view, VendingMachineService service) {
        this.view = view;
        this.service = service;
    }

    public void run() throws IOException, VendingMachineInsufficientFundsException {
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

    private void buyItem() throws IOException, VendingMachineInsufficientFundsException {
        UserWallet userInputMoney = view.displayUserInputMoney(); // 2
        String userInputItemName = view.displayBuyItem();
        VendingMachine item = new VendingMachine();

        try {
            item = service.getItem(userInputItemName);
            CoinsReturned moneyCalculation;
            moneyCalculation = service.updateItems(userInputMoney, item);
            view.displayChange(moneyCalculation);
            displaySuccess(); //TODO: handle user feedback and exit when needed
        } catch (NullPointerException e) {
            view.displayItemDoesNotExistMSG();
        } catch (IOException e) {
            view.displayItemDoesNotExistMSG();
        } catch (VendingMachineInsufficientFundsException e) {
            view.displayNotEnoughMoneyMSG();
        }

    }
    
    public void displaySuccess() throws IOException, VendingMachineInsufficientFundsException {
        String enter = "";
        boolean valid = false;
        while(valid != true) {
            enter = view.displaySuccess().toUpperCase();
            if (enter.equals("Y")) {
                valid = true;
            } else if (enter.equals("N")) {
                select = 2;
                valid = true;
            } else {
                valid = false;
            }
        }
    }

    private void exit() {
        System.out.println("Exiting...");
    }

}
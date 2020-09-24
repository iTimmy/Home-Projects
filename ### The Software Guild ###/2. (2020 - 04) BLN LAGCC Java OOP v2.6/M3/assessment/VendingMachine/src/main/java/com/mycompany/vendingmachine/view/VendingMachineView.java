package com.mycompany.vendingmachine.view;

import java.util.*;
import com.mycompany.vendingmachine.dto.*;
import java.math.BigDecimal;

public class VendingMachineView {

    //UserIO io = new UserIOConsoleImpl();

    UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void display() throws Exception {
        io.println("\n           MENU\n----------------------------\n" + "1. Buy item\n" + 
        "2. Exit\n" + "_____________________________");
    }



    public List<VendingMachine> displayListItems(List<VendingMachine> listItems) {
        // null in controller but not in view
        // also, it is what jumpstarts the program... why?
        //System.out.println("view: " + listItems);
        io.println("\n\n\n#################################");
        listItems.stream().forEach((p) -> {
            io.print(p.getItemName() + " | ");
            io.print(p.getItemCost() + " | ");
            System.out.println(p.getItemQuantity());
        });
        io.println("#################################\n\n\n");
        return listItems;
    }

    public int displayUserSelection() {
        int userInput = 0;
        try {
            userInput = io.readInt("Choose wisely: ");
        } catch (Exception e) {
            String warning = io.readString("XXX| WARNING |XXX");
        }
        return userInput;
    }

    public UserWallet displayUserInputMoney() {
        UserWallet userWallet = new UserWallet();
        BigDecimal input = new BigDecimal(0);
        boolean valid = false;
        while(valid != true) {
            try {
                input = io.readBigDecimal("How much money are you willing to spend? $");
                valid = true;
            } catch (Exception e) {
                String warning = io.readString("Invalid input.");
                valid = false;
            }
        }
        userWallet.setMoney(input);
        io.println("\n");
        return userWallet;
    }

    public String displayBuyItem() {
        io.println("-------BUY-ITEM-------");
        String userInputItemName = "";
        boolean valid = false;
        while(valid != true) {
            try {
                userInputItemName = io.readString("What would you like to buy? ");
                valid = true;
            } catch (Exception e) {
                String warning = io.readString("Please provide a valid item.");
                valid = false;
            }
        }
        io.println("----------------------");
        return userInputItemName.toUpperCase();
    }

    public void displayError() {
        io.println("Error.");
    }

    public void displaySuccess() {
        io.println("\n________________________________________________\nTask completed. Returning to the menu...");
    }

    public void displayNotEnoughMoneyMSG() {
        io.println("You do not have enough money.");
    }

    public void displayItemDoesNotExistMSG() {
        io.println("This item does not exist.");
    }
}
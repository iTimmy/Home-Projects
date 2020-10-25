package com.sg.vendingmachine.view;

import com.sg.vendingmachine.dto.CoinsReturned;
import com.sg.vendingmachine.dto.VendingMachine;
import com.sg.vendingmachine.dto.UserWallet;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class VendingMachineView {

    //UserIO io = new UserIOConsoleImpl();

    UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void display() {
        io.println("\n           MENU\n----------------------------\n" + "1. Buy item\n" + 
        "2. Exit\n" + "_____________________________");
    }



    public List<VendingMachine> displayListItems(List<VendingMachine> listItems) {
        // null in controller but not in view
        // also, it is what jumpstarts the program... why?
        //System.out.println("view: " + listItems);
        io.println("\n\n\n#################################");
        listItems.stream().filter(p -> p.getItemQuantity() != 0).forEach((p) -> {
            io.print(p.getItemName() + " | ");
            io.print("$" + p.getItemCost() + " | ");
            System.out.println(p.getItemQuantity() + " Qty");
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
        userWallet.setMoney(input.setScale(2, RoundingMode.FLOOR));
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

    public String displaySuccess() {
        String enter = io.readString("\n________________________________________________\nTask completed. Return to main menu? (y/n)");
        return enter;
    }

    public void displayNotEnoughMoneyMSG() {
        io.println("You do not have enough money.");
    }

    public void displayItemDoesNotExistMSG() {
        io.println("This item either does not exist or is not currently in stock.");
    }

    public void displayChange(CoinsReturned returnChange) {
        io.println(returnChange.getStatement() + returnChange.getReturnedCoins());
    }
}
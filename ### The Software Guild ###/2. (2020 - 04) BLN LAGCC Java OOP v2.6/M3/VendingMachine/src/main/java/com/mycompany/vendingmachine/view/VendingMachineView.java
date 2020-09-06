package com.mycompany.vendingmachine.view;

import java.util.*;
import com.mycompany.vendingmachine.dto.*;

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
        int userInput = io.readInt("Choose wisely: ");
        return userInput;
    }

    public double displayUserInputMoney() {
        double input = io.readDouble("How much money are you willing to spend? $");
        io.println("\n");
        return input;
    }

    public String displayBuyItem() {
        io.println("-------BUY-ITEM-------");
        String userInputItemName = io.readString("What would you like to buy? ");
        io.println("----------------------");
        return userInputItemName;
    }

    public int displayBuyItemQuantity() {
        int itemQuantity = io.readInt("How many would you like to buy? ");
        return itemQuantity;
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
        io.println("This item or the number of items does not exist.");
    }
}
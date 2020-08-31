package view;

import java.util.*;
import dto.*;

public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void display() throws Exception {
        System.out.println("\n           MENU\n----------------------------\n" + "1. Buy item\n" + 
        "2. Exit\n" + "_____________________________");
    }



    public List<VendingMachine> displayListItems(List<VendingMachine> listItems) {
        // null in controller but not in view
        // also, it is what jumpstarts the program... why?
        //System.out.println("view: " + listItems);
        System.out.println("\n\n\n#################################");
        listItems.stream().forEach((p) -> {
            System.out.print(p.getItemName() + " | ");
            System.out.print(p.getItemCost() + " | ");
            System.out.println(p.getItemQuantity());
        });
        System.out.println("#################################\n\n\n");
        return listItems;
    }

    public int displayUserSelection() {
        System.out.print("Choose wisely: ");
        Scanner input = new Scanner(System.in);
        int userInput = input.nextInt();
        /*
         * if (userInput != 1 || userInput != 2 || userInput != 3) {
         * System.out.println("error"); }
         */
        return userInput;
    }

    public double displayUserInputMoney() {
        System.out.print("How much money are you willing to spend? $");
        Scanner scan = new Scanner(System.in);
        double input = scan.nextDouble();
        System.out.println("\n");
        return input;
    }

    public String displayBuyItem() {
        System.out.println("-------BUY-ITEM-------");
        System.out.println("What would you like to buy? ");
        Scanner input = new Scanner(System.in);
        String userInputItemName = input.nextLine();
        System.out.println("----------------------");
        return userInputItemName;
    }

    public int displayBuyItemQuantity() {
        Scanner input = new Scanner(System.in);
        System.out.println("How many would you like to buy? ");
        int itemQuantity = input.nextInt();
        return itemQuantity;
    }

    public void displayError() {
        System.out.println("Error.");
    }

    public void displaySuccess() {
        System.out.println("\n________________________________________________\nTask completed. Returning to the menu...");
    }

    public void displayNotEnoughMoneyMSG() {
        System.out.println("You do not have enough money.");
    }

    public void displayItemDoesNotExistMSG() {
        System.out.println("This item or the number of items does not exist.");
    }
}
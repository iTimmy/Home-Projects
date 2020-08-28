package controller;

import java.util.*;
import view.*;
import service.*;
import dto.*;

public class VendingMachineController {

    private VendingMachineView view = new VendingMachineView();
    private VendingMachineService service = new VendingMachineServiceImpl();

    public void run() throws Exception {
        int select = 0;

        while (select != 3) {
            listItems();
            view.display();
            select = userSelection();
            /*
             * if (select != 1 || select != 2 || select != 3) { break; }
             */

            switch (select) {
                case 1:
                    buyItem();
                    break;
                case 2:
                    viewWallet();
                    break;
                case 3:
                    exit();
                    break;
            }
        }

    }

    private int userSelection() {
        return view.displayUserSelection();
    }

    private List<VendingMachine> listItems() {
        System.out.println(service.getAllItems());
        view.displayListItems(service.getAllItems());
        return service.getAllItems();
    }

    private void buyItem() throws Exception {
        double userInputMoney = view.displayUserInputMoney(); //2
        String userInputItemName = view.displayBuyItem();
        int userInputItemQuantity = view.displayBuyItemQuantity();
        if (service.getItem(userInputItemName.toUpperCase(), userInputItemQuantity, userInputMoney) == 1) {
            view.displaySuccess();
        } else if (service.getItem(userInputItemName.toUpperCase(), userInputItemQuantity, userInputMoney) == 2) {
            view.displayItemDoesNotExistMSG();
        } else if (service.getItem(userInputItemName.toUpperCase(), userInputItemQuantity, userInputMoney) == 0) {
            view.displayNotEnoughMoneyMSG();
        }
    }

    private void viewWallet() {
        //double money = view.displayUserInputMoney();
        //service.moneyMoney(money);
        //view.displayViewWallet();
    }

    private void exit() {
        System.out.println("Exiting...");
    }

}
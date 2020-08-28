package service;

import java.util.*;
import controller.*;
import dao.*;
import dto.*;

public class VendingMachineServiceImpl implements VendingMachineService {
    private VendingMachineDao dao = new VendingMachineDaoImpl();
	private int VendingMachine;

    @Override
    public List<VendingMachine> getAllItems() {
        List<VendingMachine> listItems = dao.getAllItems();
        return listItems;
    }

    @Override
    public int getItem(String userInputItemName, int userInputItemQuantity, double userInputMoney) throws Exception {
        if (dao.getItem(userInputItemName, userInputItemQuantity, userInputMoney) == null) {
            return 2;
        } else if (dao.getItem(userInputItemName, userInputItemQuantity, userInputMoney) != null) {
            VendingMachine retrievedItem = dao.getItem(userInputItemName, userInputItemQuantity, userInputMoney);
            if (userInputMoney >= retrievedItem.getItemCost() * userInputItemQuantity) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public void updateItem(String itemName, int numOfItems, int itemQuantity, String userItemName) throws Exception {
        dao.updateItem(itemName, numOfItems, itemQuantity, userItemName);
    }

    @Override
    public void removeItem(String itemName, int itemQuantity) {
        dao.removeItem(itemName, itemQuantity);

    }
}
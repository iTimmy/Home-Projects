package dao;

import java.util.*;
import dto.*;

public interface VendingMachineDao {
    List<VendingMachine> getAllItems();

    VendingMachine getItem(String itemName, int itemQuantity, double userMoney) throws Exception;

    void updateItem(String itemName, int numOfItems, int itemQuantity, String userItemName) throws Exception;

    double updateWallet(double itemCost);

    void removeItem(String itemName, int itemQuantity);

	Object updateWallet();
}
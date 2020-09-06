package com.mycompany.vendingmachine.dao;

import java.util.*;
import com.mycompany.vendingmachine.dto.*;

public interface VendingMachineDao {
    List<VendingMachine> getAllItems();

    VendingMachine getItem(String itemName, int itemQuantity, double userMoney) throws Exception;
    
    VendingMachine getItem(String userInputItemName);

    double updateWallet(double itemCost);

    void removeItem(String itemName, int itemQuantity);

}
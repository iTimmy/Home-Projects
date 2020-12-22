package com.mycompany.vendingmachine.dao;

import java.util.*;
import com.mycompany.vendingmachine.dto.*;
import java.math.BigDecimal;

public interface VendingMachineDao {
    List<VendingMachine> getAllItems();

    VendingMachine getItem(String itemName, int itemQuantity, BigDecimal userMoney) throws Exception;
    
    VendingMachine getItem(String userInputItemName);

    BigDecimal updateWallet(BigDecimal itemCost);

    void removeItem(String itemName, int itemQuantity);

}
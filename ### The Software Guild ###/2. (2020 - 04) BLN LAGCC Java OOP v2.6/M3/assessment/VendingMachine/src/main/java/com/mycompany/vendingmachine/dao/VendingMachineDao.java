package com.mycompany.vendingmachine.dao;

import java.util.*;
import com.mycompany.vendingmachine.dto.*;
import java.math.BigDecimal;

public interface VendingMachineDao {
    List<VendingMachine> getAllItems();

    VendingMachine getItem(String itemName) throws Exception;

    void updateItems(VendingMachine item) throws Exception;
    
    BigDecimal updateWallet(BigDecimal itemCost);

    void removeItem(VendingMachine item);

}
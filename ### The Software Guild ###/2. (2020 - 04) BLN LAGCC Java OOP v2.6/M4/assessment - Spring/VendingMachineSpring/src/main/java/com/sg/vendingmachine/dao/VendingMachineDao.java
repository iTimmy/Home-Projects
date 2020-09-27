package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.VendingMachine;
import java.util.*;
import java.math.BigDecimal;

public interface VendingMachineDao {
    List<VendingMachine> getAllItems();

    VendingMachine getItem(String itemName) throws Exception;

    void updateItems(VendingMachine item) throws Exception;
    
    BigDecimal updateWallet(BigDecimal itemCost);

    void removeItem(VendingMachine item);

}
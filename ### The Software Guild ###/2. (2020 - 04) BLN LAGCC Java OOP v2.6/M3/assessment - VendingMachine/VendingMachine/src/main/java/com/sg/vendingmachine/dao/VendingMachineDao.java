package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.VendingMachine;
import java.util.*;
import java.io.IOException;
import java.math.BigDecimal;

public interface VendingMachineDao {
    List<VendingMachine> getAllItems();

    VendingMachine getItem(String itemName);

    void updateItems(VendingMachine item) throws IOException;
    
    BigDecimal updateWallet(BigDecimal itemCost);

    void removeItem(VendingMachine item) throws IOException;
}
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.VendingMachine;
import com.sg.vendingmachine.dao.*;
import java.util.*;
import java.math.BigDecimal;
import java.io.*;

public interface VendingMachineDao {
    List<VendingMachine> getAllItems();

    VendingMachine getItem(String itemName);

    void updateItems(VendingMachine item) throws IOException;
    
    BigDecimal updateWallet(BigDecimal itemCost);

    void removeItem(VendingMachine item) throws IOException;
}
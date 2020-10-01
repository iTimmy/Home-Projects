package com.mycompany.vendingmachine.service;

import java.util.*;
import java.math.BigDecimal;
import com.mycompany.vendingmachine.dto.*;

public interface VendingMachineService {
    List<VendingMachine> getAllItems();

    VendingMachine getItem(String itemName) throws Exception;

    CoinsReturned moneyCalculation(MathOperator operator, UserWallet userWallet, VendingMachine item) throws Exception;
    
    void updateItems(VendingMachine item) throws Exception;

    void removeItem(VendingMachine item);
}
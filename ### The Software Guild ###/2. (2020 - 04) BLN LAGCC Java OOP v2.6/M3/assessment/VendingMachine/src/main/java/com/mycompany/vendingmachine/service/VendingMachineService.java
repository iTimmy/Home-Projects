package com.mycompany.vendingmachine.service;

import java.util.*;
import java.math.BigDecimal;
import com.mycompany.vendingmachine.dto.*;

public interface VendingMachineService {
    List<VendingMachine> getAllItems();

    VendingMachine getItem(String itemName) throws Exception;

    BigDecimal moneyCalculation(MathOperator operator, UserWallet userWallet, VendingMachine item);
    
    void updateItems(VendingMachine item) throws Exception;

    void removeItem(VendingMachine item);
}
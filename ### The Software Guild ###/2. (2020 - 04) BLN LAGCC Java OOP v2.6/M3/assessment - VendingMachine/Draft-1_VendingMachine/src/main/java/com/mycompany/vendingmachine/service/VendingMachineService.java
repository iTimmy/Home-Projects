package com.mycompany.vendingmachine.service;

import java.util.*;
import java.math.BigDecimal;
import com.mycompany.vendingmachine.dto.*;

public interface VendingMachineService {
    List<VendingMachine> getAllItems();

    int getItem(String itemName, int itemQuantity, BigDecimal userMoney) throws Exception;

    BigDecimal moneyCalculation(MathOperator operator, BigDecimal userInputMoney, String userInputItemName);

    void removeItem(String itemName, int itemQuantity);
}
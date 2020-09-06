package com.mycompany.vendingmachine.service;

import java.util.*;
import java.math.BigDecimal;
import com.mycompany.vendingmachine.dto.*;

public interface VendingMachineService {
    List<VendingMachine> getAllItems();

    int getItem(String itemName, int itemQuantity, double userMoney) throws Exception;

    BigDecimal moneyCalculation(MathOperator operator, double userInputMoney, String userInputItemName);

    void removeItem(String itemName, int itemQuantity);
}
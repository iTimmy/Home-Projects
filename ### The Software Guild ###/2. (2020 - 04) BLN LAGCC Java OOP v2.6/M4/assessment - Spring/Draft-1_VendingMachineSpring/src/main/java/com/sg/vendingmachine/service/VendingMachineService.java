package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.CoinsReturned;
import com.sg.vendingmachine.dto.UserWallet;
import com.sg.vendingmachine.dto.VendingMachine;
import java.util.*;
import java.math.BigDecimal;

public interface VendingMachineService {
    List<VendingMachine> getAllItems();

    VendingMachine getItem(String itemName) throws Exception;

    CoinsReturned updateItems(UserWallet userWallet, VendingMachine item) throws Exception;

    void removeItem(VendingMachine item);
}
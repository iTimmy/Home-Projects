package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.CoinsReturned;
import com.sg.vendingmachine.dto.UserWallet;
import com.sg.vendingmachine.dto.VendingMachine;
import com.sg.vendingmachine.dao.VendingMachineInsufficientFundsException;
import java.util.*;
import java.math.BigDecimal;
import java.io.IOException;

public interface VendingMachineService {
    List<VendingMachine> getAllItems();

    VendingMachine getItem(String itemName);

    CoinsReturned updateItems(UserWallet userWallet, VendingMachine item) throws IOException, VendingMachineInsufficientFundsException;

    void removeItem(VendingMachine item) throws IOException;
}
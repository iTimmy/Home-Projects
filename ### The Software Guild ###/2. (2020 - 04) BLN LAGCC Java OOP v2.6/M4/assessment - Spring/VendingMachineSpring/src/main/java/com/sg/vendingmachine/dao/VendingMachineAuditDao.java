package com.sg.vendingmachine.dao;

import java.math.BigDecimal;

public interface VendingMachineAuditDao {
    void orderDate(String userInputItemName, BigDecimal userChange) throws Exception;
}
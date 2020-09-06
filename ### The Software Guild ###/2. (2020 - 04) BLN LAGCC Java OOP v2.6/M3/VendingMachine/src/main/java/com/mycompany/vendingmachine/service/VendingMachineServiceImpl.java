package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.VendingMachineDao;
import com.mycompany.vendingmachine.dao.VendingMachineAuditDao;
import com.mycompany.vendingmachine.dao.VendingMachineAuditDaoImpl;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.mycompany.vendingmachine.controller.*;
import com.mycompany.vendingmachine.dao.VendingMachineDaoImpl;
import com.mycompany.vendingmachine.dto.*;
import java.math.MathContext;

public class VendingMachineServiceImpl implements VendingMachineService {
    // private VendingMachineDao dao = new VendingMachineDaoImpl();
    
    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<VendingMachine> getAllItems() {
        List<VendingMachine> listItems = dao.getAllItems();
        return listItems;
    }

    @Override
    public int getItem(String userInputItemName, int userInputItemQuantity, double userInputMoney) throws Exception {
        if (dao.getItem(userInputItemName, userInputItemQuantity, userInputMoney) == null) {
            return 2;
        } else if (dao.getItem(userInputItemName, userInputItemQuantity, userInputMoney) != null) {
            VendingMachine retrievedItem = dao.getItem(userInputItemName, userInputItemQuantity, userInputMoney);
            if (userInputMoney >= retrievedItem.getItemCost() * userInputItemQuantity) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public void removeItem(String itemName, int itemQuantity) {
        dao.removeItem(itemName, itemQuantity);

    }

    @Override
    public BigDecimal moneyCalculation(MathOperator operator, double userInputMoney, String userInputItemName) {
        VendingMachineAuditDaoImpl audit = new VendingMachineAuditDaoImpl();
        MathContext mc = new MathContext(3);
        BigDecimal itemCost = new BigDecimal(dao.updateWallet(1.0)).round(mc);
        BigDecimalMath moneyCalculate = new BigDecimalMath();
        BigDecimal userInputMoneyBigDecimal = new BigDecimal(userInputMoney).round(mc);
        BigDecimal userChange = moneyCalculate.calculate(MathOperator.MINUS, userInputMoneyBigDecimal, itemCost.setScale(2, RoundingMode.FLOOR).round(mc));
     
        try {
            audit.orderDate(userInputItemName, userChange);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return itemCost;
    }
}
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
    private BigDecimalMath moneyCalculate = new BigDecimalMath();
    private MathContext mc = new MathContext(3);

    public VendingMachineServiceImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    public VendingMachineServiceImpl() {
        // so that i dont have to pass any parameters that proves unneccesary when testing this
    }

    @Override
    public List<VendingMachine> getAllItems() {
        List<VendingMachine> listItems = dao.getAllItems();
        return listItems;
    }

    @Override
    public VendingMachine getItem(String userInputItemName) throws Exception {
        if (dao.getItem(userInputItemName) == null) {
            return null;
        } else {
            VendingMachine retrievedItem = dao.getItem(userInputItemName);
            return retrievedItem;
        }
    }
    
    @Override
    public void updateItems(VendingMachine item) throws Exception {
        dao.updateItems(item);
    }
    @Override
    public void removeItem(VendingMachine item) {
        dao.removeItem(item);

    }

    @Override
    public BigDecimal moneyCalculation(MathOperator operator, UserWallet userWallet, VendingMachine item) {
        BigDecimal userChange = moneyCalculate.calculate(MathOperator.MINUS, userWallet.getMoney(), item.getItemCost()).round(mc);
        
        if (hasEnoughMoney(userWallet.getMoney(), item.getItemCost()) == false) {
            return null;
        } 
        
        int currentItemQuantity = item.getItemQuantity() - userWallet.getRequestItemQuantity();
        item.setItemQuantity(currentItemQuantity);
        if (currentItemQuantity <= 0) {
            removeItem(item);
        } else {
            try {
                updateItems(item);
            } catch (Exception e) {}
        }
     
        try {
            auditDao.orderDate(item.getItemName(), userChange);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(userChange);
        return userChange;
    }
    
    private boolean hasEnoughMoney(BigDecimal money, BigDecimal itemCost) {
        if (money.compareTo(itemCost) >= 0) {
            return true;
        } else {
            return false;
        }
    }
}
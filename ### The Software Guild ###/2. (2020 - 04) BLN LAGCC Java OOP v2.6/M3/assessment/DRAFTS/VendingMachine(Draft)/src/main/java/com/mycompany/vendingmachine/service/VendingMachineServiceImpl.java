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
    public int getItem(String userInputItemName, int userInputItemQuantity, BigDecimal userInputMoney) throws Exception {
        if (dao.getItem(userInputItemName, userInputItemQuantity, userInputMoney) == null) {
            return 2;
        } else if (dao.getItem(userInputItemName, userInputItemQuantity, userInputMoney) != null) {
            VendingMachine retrievedItem = dao.getItem(userInputItemName, userInputItemQuantity, userInputMoney);
            BigDecimal itemQuantity = new BigDecimal(userInputItemQuantity);
            BigDecimal calc = moneyCalculate.calculate(MathOperator.MULTIPLY, retrievedItem.getItemCost(), itemQuantity);
            
            if (userInputMoney.compareTo(calc) > 0) {
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
    public BigDecimal moneyCalculation(MathOperator operator, BigDecimal userInputMoney, String userInputItemName) {
        BigDecimal m = new BigDecimal(5);
        System.out.println(m);
        BigDecimal itemCost = new BigDecimal(2.5);
        System.out.println(itemCost);
        BigDecimal userChange = moneyCalculate.calculate(MathOperator.MINUS, userInputMoney, itemCost).round(mc);
     
        try {
            auditDao.orderDate(userInputItemName, userChange);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return itemCost;
    }
}
package service;

import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import controller.*;
import dao.*;
import dto.*;

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
    public void updateItem(String itemName, int numOfItems, int itemQuantity, String userItemName) throws Exception {
        dao.updateItem(itemName, numOfItems, itemQuantity, userItemName);
    }

    @Override
    public void removeItem(String itemName, int itemQuantity) {
        dao.removeItem(itemName, itemQuantity);

    }

    @Override
    public BigDecimal moneyCalculation(MathOperator operator, double userInputMoney, String userInputItemName) {
        VendingMachineAuditDaoImpl audit = new VendingMachineAuditDaoImpl();
        BigDecimal itemCost = new BigDecimal(dao.updateWallet(1.0));
        BigDecimalMath moneyCalculate = new BigDecimalMath();
        BigDecimal userInputMoneyBigDecimal = new BigDecimal(userInputMoney);
        BigDecimal userChange = moneyCalculate.calculate(MathOperator.MINUS, userInputMoneyBigDecimal, itemCost.setScale(2, RoundingMode.FLOOR));
        System.out.println(userChange);
        System.out.println(userInputMoneyBigDecimal);
        System.out.println(itemCost);
        System.out.println(2 - 1.25);
        try {
            audit.orderDate(userInputItemName, userChange);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return itemCost;
    }
}
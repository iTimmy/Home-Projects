package service;

import java.util.*;
import java.math.BigDecimal;
import controller.*;
import dao.*;
import dto.*;

public class VendingMachineServiceImpl implements VendingMachineService {
    private VendingMachineDao dao = new VendingMachineDaoImpl();
    private int VendingMachine;

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
        AuditDao audit = new AuditDao();
        BigDecimal itemCost = new BigDecimal(dao.updateWallet(1.0));
        BigDecimalMath moneyCalculate = new BigDecimalMath();
        // BigDecimal itemCost = new BigDecimal("2.0");
        System.out.println("itemCost: " + itemCost);
        BigDecimal userInputMoneyBigDecimal = new BigDecimal(userInputMoney);
        System.out.println("userInputMoney: " + userInputMoney);
        System.out.println("userInputMoneyBigDecimal: " + userInputMoneyBigDecimal);
        System.out.println("MathOperator.MINUS: " + MathOperator.MINUS);
        BigDecimal userChange = moneyCalculate.calculate(MathOperator.MINUS, userInputMoneyBigDecimal, itemCost);
        String orderWrite = "You receive $" + userChange + " in change.";
        try {
            audit.orderDate(orderWrite, userInputItemName);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return itemCost;
    }
}
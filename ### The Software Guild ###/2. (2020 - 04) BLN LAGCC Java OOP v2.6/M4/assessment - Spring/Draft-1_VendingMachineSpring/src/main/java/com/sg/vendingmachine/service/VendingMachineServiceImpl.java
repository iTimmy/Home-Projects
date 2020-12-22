package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.CoinsReturned;
import com.sg.vendingmachine.dto.VendingMachine;
import com.sg.vendingmachine.dto.UserWallet;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineAuditDaoImpl;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.sg.vendingmachine.dao.VendingMachineDaoImpl;
import com.sg.vendingmachine.dto.CoinsReturned;
import java.math.MathContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendingMachineServiceImpl implements VendingMachineService {
    // private VendingMachineDao dao = new VendingMachineDaoImpl();
    
    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    private BigDecimalMath moneyCalculate = new BigDecimalMath();
    private MathContext mc = new MathContext(3);

    @Autowired
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
        if (dao.getItem(userInputItemName) == null || dao.getItem(userInputItemName).getItemQuantity() <= 0) {
            return null;
        } else {
            VendingMachine retrievedItem = dao.getItem(userInputItemName);
            return retrievedItem;
        }
    }
    
    @Override
    public CoinsReturned updateItems(UserWallet userWallet, VendingMachine item) throws Exception {
        CoinsReturned cr = moneyCalculation(MathOperator.MINUS, userWallet, item);
        VendingMachine updatedItem = updateItem(item);
        dao.updateItems(updatedItem);
        return cr;
    }
    
    @Override
    public void removeItem(VendingMachine item) {
        dao.removeItem(item);

    }
    
    private VendingMachine updateItem(VendingMachine item) {
        return item;
    }

    private CoinsReturned moneyCalculation(MathOperator operator, UserWallet userWallet, VendingMachine item) throws Exception {
        BigDecimal userChange = moneyCalculate.calculate(MathOperator.MINUS, userWallet.getMoney(), item.getItemCost()).round(mc);
        
        if (hasEnoughMoney(userWallet.getMoney(), item.getItemCost()) == false) {
            return null;
        } 
        
        int currentItemQuantity = 0;
        if (item.getItemQuantity() > 0) {
            currentItemQuantity = item.getItemQuantity() - userWallet.getRequestItemQuantity();
            item.setItemQuantity(currentItemQuantity);
        }
        if (currentItemQuantity <= 0) {
            removeItem(item);
        } else {
            try {
                updateItem(item);
            } catch (Exception e) {}
        }
     
        try {
            auditDao.orderDate(item.getItemName(), userChange);
        } catch (Exception e) {
            e.printStackTrace();
        }
        auditDao.orderDate(item.getItemName(), userChange);
        // System.out.println(userChange);
        
        MathContext mc = new MathContext(2);
        CoinsReturned cr = displayChange(returnChange(userChange));
        
        String statement = "\n\n\nYou insert $" + userWallet.getMoney().toString() + " to order " + item.getItemName() +
                " for $" + item.getItemCost().toString() + ".\n$" + 
                userChange.toString() + " has been returned to you. Here you received... ";
        
        cr.setStatement(statement);
        
        return cr;
    }
    
    private boolean hasEnoughMoney(BigDecimal money, BigDecimal itemCost) {
        if (money.compareTo(itemCost) >= 0) {
            return true;
        } else {
            return false;
        }
    }
    
    private CoinsReturned returnChange(BigDecimal userChange) {
        int penny = 1;
        int nickel = 5;
        int dime = 10;
        int quarter = 25;
        
        int penniesReturned = 0;
        int nickelsReturned = 0;
        int dimesReturned = 0;
        int quartersReturned = 0;
        int dollarCoinsReturned = 0;
        
        int dollarCoin = 1;

            String afterDecimalPoint = userChange.toString().substring(userChange.toString().lastIndexOf(".") + 1);
            int beforeDecimalPoint = userChange.toString().lastIndexOf(".");
            if (beforeDecimalPoint == -1) {
               // System.out.println("");
            } // System.out.println("Substring before last separator = " + userChange.toString().substring(0, beforeDecimalPoint));
            int userChangeRight = Integer.parseInt(afterDecimalPoint);
            int userChangeLeft = Integer.parseInt(userChange.toString().substring(0, beforeDecimalPoint));
            
            while(userChangeRight > 0) {
                if (userChangeLeft >= dollarCoin) {
                    userChangeLeft = userChangeLeft - dollarCoin;
                    dollarCoinsReturned++;
                } else if (userChangeRight >= quarter) {
                    userChangeRight = userChangeRight - quarter;
                    quartersReturned++;
                } else if (userChangeRight >= dime && userChangeRight < quarter) {
                    userChangeRight = userChangeRight - dime;
                    dimesReturned++;
                } else if (userChangeRight >= nickel && userChangeRight < dime) {
                    userChangeRight = userChangeRight - nickel;
                    nickelsReturned++;
                } else if (userChangeRight >= penny && userChangeRight < nickel) {
                    userChangeRight = userChangeRight - penny;
                    penniesReturned++;
                } else if (userChangeRight == 0 && userChangeLeft == 0) {
                    break;
                }
            }
            
            CoinsReturned cr = new CoinsReturned();
            cr.setChange(userChange);
            cr.setPennies(penniesReturned);
            cr.setNickels(nickelsReturned);
            cr.setDimes(dimesReturned);
            cr.setQuarters(quartersReturned);
            cr.setDollarCoins(dollarCoinsReturned);
            
            return cr;
    }
    
    private CoinsReturned displayChange(CoinsReturned cr) {
        String sNickel = "";
        String sDime = "";
        String sQuarter = "";
        String sDollarCoin = "";
        String ies = "y";
        
        if (cr.getPennies() > 1 || cr.getPennies() == 0) {
            ies = "ies";
        }
        if (cr.getNickels() > 1 || cr.getNickels() == 0) {
            sNickel = "s";
        }
        if (cr.getDimes() > 1 || cr.getDimes() == 0) {
            sDime = "s";
        }
        if (cr.getQuarters() > 1 || cr.getQuarters() == 0) {
            sQuarter = "s";
        }
        if (cr.getDollarCoins() > 1 || cr.getDollarCoins() == 0) {
            sDollarCoin = "s";
        }
        
        String displayPennies = Integer.toString(cr.getPennies()) + " penn" + ies + "\n";
        String displayNickels = Integer.toString(cr.getNickels()) + " nickel" + sNickel + "\n";
        String displayDimes = Integer.toString(cr.getDimes()) + " dime" + sDime + "\n";
        String displayQuarters = Integer.toString(cr.getQuarters()) + " quarter" + sQuarter + "\n";
        String displayDollarCoins = Integer.toString(cr.getDollarCoins()) + " dollar coin" + sDollarCoin + "\n";
        
        String returnCoins = "\n\n******************\n" + 
                "Returning:\n==================\n" + displayDollarCoins +
                displayQuarters +
                displayDimes + 
                displayNickels + 
                displayPennies +
                "******************";
        
        cr.setReturnedCoins(returnCoins);
        
        return cr;
    }
    
}
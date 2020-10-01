/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dto.VendingMachine;
import com.mycompany.vendingmachine.service.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.mycompany.vendingmachine.dao.*;
import com.mycompany.vendingmachine.dto.CoinsReturned;
import com.mycompany.vendingmachine.dto.UserWallet;

/**
 *
 * @author Music Account
 */
public class VendingMachineServiceImplTest {
    
    VendingMachineService serviceTest = new VendingMachineServiceImpl();
    VendingMachineDao daoTest = new VendingMachineDaoImpl();
    MathContext mc = new MathContext(2);
    BigDecimalMath mathCalculate = new BigDecimalMath();
    
    
    public VendingMachineServiceImplTest() {
        
    }
 /*  
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
*/
    /**
     * Test of getAllItems method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testMoneyCalculation() throws Exception {
        UserWallet userWalletTest = new UserWallet();
        userWalletTest.setMoney(new BigDecimal(1.25));
        
        String userInputItemName = "soda";
        
        VendingMachine itemTest = new VendingMachine();
        itemTest.setItemName(userInputItemName.toUpperCase());
        itemTest.setItemCost(new BigDecimal(1.25));
        itemTest.setItemQuantity(10);
        
        BigDecimal expResult = mathCalculate.calculate(MathOperator.MINUS, userWalletTest.getMoney(), itemTest.getItemCost());
        
        VendingMachineServiceImpl instance = new VendingMachineServiceImpl();
        
        CoinsReturned cr = instance.moneyCalculation(MathOperator.MINUS, userWalletTest, itemTest);
        BigDecimal result = cr.getChange();
        
        System.out.println(expResult);
        System.out.println(result);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetAllItems() throws Exception {
        List<VendingMachine> list = serviceTest.getAllItems();
        VendingMachine itemOne = new VendingMachine();
        itemOne.setItemName("pringles");
        itemOne.setItemCost(new BigDecimal(2.5));
        itemOne.setItemQuantity(50);
        list.add(itemOne);
        
        VendingMachine itemTwo = new VendingMachine();
        itemTwo.setItemName("soda");
        itemTwo.setItemCost(new BigDecimal(2.5));
        itemTwo.setItemQuantity(50);
        list.add(itemTwo);

        assertEquals(2, list.size());
    }

    @Test
    public void testGetItem_String() throws Exception {
        String itemNameTest = "pringles";
        BigDecimal itemCostTest = new BigDecimal(2.5).round(mc);
        VendingMachine item = new VendingMachine();
        
        item.setItemName(itemNameTest.toUpperCase());
        item.setItemCost(itemCostTest);
        item.setItemQuantity(6);

        VendingMachine result = serviceTest.getItem(item.getItemName()); // 4
//        System.out.println("getItem 2: " + item.getItemQuantity()); 
//        System.out.println(item.getItemName().equals("pringles"));
//        System.out.println(itemNameTest);
//        System.out.println(result.getItemName());
        assertEquals(item.getItemName(), result.getItemName());
        assertTrue(item.getItemName().equals(result.getItemName()));
    }
    
    @Test
    public void testUpdateItems_VendingMachine() {
        List<VendingMachine> list = serviceTest.getAllItems();
        VendingMachine itemOne = new VendingMachine();
        itemOne.setItemName("pringles");
        itemOne.setItemCost(new BigDecimal(2.5));
        itemOne.setItemQuantity(50);
        list.add(itemOne);  
    }
    
    @Test
    public void testRemoveItem_VendingMachine() throws Exception {
        List<VendingMachine> list = serviceTest.getAllItems();
        VendingMachine itemOne = new VendingMachine();
        itemOne.setItemName("pringles");
        itemOne.setItemCost(new BigDecimal(2.5));
        itemOne.setItemQuantity(50);
        list.add(itemOne);
        
        VendingMachine itemTwo = new VendingMachine();
        itemTwo.setItemName("soda");
        itemTwo.setItemCost(new BigDecimal(2.5));
        itemTwo.setItemQuantity(50);
        list.add(itemTwo);
        
        serviceTest.removeItem(itemTwo);

        assertEquals(null, serviceTest.getItem(itemTwo.getItemName()));
    }
    
}

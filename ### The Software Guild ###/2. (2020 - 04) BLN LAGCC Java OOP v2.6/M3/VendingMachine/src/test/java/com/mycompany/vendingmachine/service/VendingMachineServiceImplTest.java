/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dto.VendingMachine;
import com.mycompany.vendingmachine.service.*;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Music Account
 */
public class VendingMachineServiceImplTest {
    
    VendingMachineService serviceTest;
    
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
    public void testGetAllItems() throws Exception {
        String itemNameTestOne = "pizza";
        double itemCostTestOne = 1.00;
        double userInputMoneyTestOne = 5.34;
        VendingMachine itemOne = new VendingMachine(itemNameTestOne.toUpperCase(), itemCostTestOne);
        itemOne.setItemQuantity(4);
        serviceTest.getItem(itemOne.getItemName(), itemOne.getItemQuantity(), userInputMoneyTestOne);

        /*
        String itemNameTestTwo = "pizza";
        double itemCostTestTwo = 1.75;
        double userInputMoneyTestTwo = 7.34;
        VendingMachine itemTwo = new VendingMachine(itemNameTestTwo.toUpperCase(), itemCostTestTwo);
        itemOne.setItemQuantity(6);
        testDao.getItem(itemNameTestTwo.toUpperCase(), itemOne.getItemQuantity(), userInputMoneyTestTwo);
        
        List<VendingMachine> list = testDao.getAllItems();

        assertEquals(4, list.size());
        */
    }

    /**
     * Test of getItem method, of class VendingMachineDaoImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetItem_String() throws Exception {
        /*
        String itemNameTest = "pizza";
        double itemCostTest = 1.00;
        double userMoney = 2.23;
        VendingMachine item = new VendingMachine(itemNameTest, itemCostTest);
        
        item.setItemName(itemNameTest.toUpperCase());
        item.setItemCost(itemCostTest);
        item.setItemQuantity(6);

        VendingMachine result = testDao.getItem(itemNameTest.toUpperCase(), item.getItemQuantity(), userMoney); // 4
        System.out.println("getItem 2: " + item.getItemQuantity());
        
        System.out.println(item.getItemName().equals("pizza"));
        System.out.println(result.getItemName());
        assertEquals(item.getItemName(), result.getItemName());
        assertTrue(item.getItemName().equals(result.getItemName()));
*/
    }
    
}

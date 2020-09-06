/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.VendingMachine;
import com.mycompany.vendingmachine.service.MathOperator;
import com.mycompany.vendingmachine.service.VendingMachineService;
import com.mycompany.vendingmachine.service.VendingMachineServiceImpl;
import java.util.List;
import java.io.*;
import java.math.BigDecimal;
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
public class VendingMachineDaoImplTest {
    
    VendingMachineDao testDao;
    VendingMachineService service;
    
    public VendingMachineDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        String testFile = "VendingMachine.txt";
        // Use the FileWriter to quickly blank the file
        // new FileWriter(testFile);
        testDao = new VendingMachineDaoImpl(testFile);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineDaoImpl.
     */
    @Test
    public void testGetAllItems() throws Exception {
        String itemNameTestOne = "soda";
        double itemCostTestOne = 1.25;
        double userInputMoneyTestOne = 5.34;
        VendingMachine itemOne = new VendingMachine(itemNameTestOne.toUpperCase(), itemCostTestOne);
        itemOne.setItemQuantity(4);
        testDao.getItem(itemNameTestOne.toUpperCase(), itemOne.getItemQuantity(), userInputMoneyTestOne);

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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoImpl;
import com.sg.vendingmachine.dto.VendingMachine;
import com.sg.vendingmachine.service.MathOperator;
import java.util.List;
import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author Music Account
 */
public class VendingMachineDaoImplTest {
    
    VendingMachineDao testDao;
    VendingMachineService service;
    MathContext mc = new MathContext(2);
    
    public VendingMachineDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        String testFile = "VendingMachineTest.txt";
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
        List<VendingMachine> list = testDao.getAllItems();
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
        String itemNameTest = "soda";
        BigDecimal itemCostTest = new BigDecimal(2.5).round(mc);
        VendingMachine item = new VendingMachine();
        
        item.setItemName(itemNameTest.toUpperCase());
        item.setItemCost(itemCostTest);
        item.setItemQuantity(10);
        
        List<VendingMachine> list = testDao.getAllItems();
        list.add(item);

        VendingMachine result = testDao.getItem(item.getItemName()); // 4
//      System.out.println("getItem 2: " + item.getItemQuantity()); 
//      System.out.println(item.getItemName().equals("pringles"));
//      System.out.println(itemNameTest);
//      System.out.println(result.getItemName());
        assertTrue(item.getItemName().equals("SODA"));
    }
    
    @Test
    public void testUpdateItems_VendingMachine() throws Exception {
        VendingMachine itemOne = new VendingMachine();
        itemOne.setItemName("fried dog");
        itemOne.setItemCost(new BigDecimal(2.5));
        itemOne.setItemQuantity(50);
        testDao.updateItems(itemOne);
        
        VendingMachine updatedItem = testDao.getItem(itemOne.getItemName());
        assertEquals(updatedItem.getItemQuantity(), itemOne.getItemQuantity());
    }
    
    @Test
    public void testRemoveItem_VendingMachine() throws Exception {
        List<VendingMachine> list = testDao.getAllItems();
        VendingMachine itemOne = new VendingMachine();
        itemOne.setItemName("pringles");
        itemOne.setItemCost(new BigDecimal(2.5));
        itemOne.setItemQuantity(50);
        list.add(itemOne);
        
        VendingMachine itemTwo = new VendingMachine();
        itemTwo.setItemName("CANDY");
        itemTwo.setItemCost(new BigDecimal(2.5));
        itemTwo.setItemQuantity(50);
        list.add(itemTwo);
        
        testDao.removeItem(itemTwo);

        assertEquals(null, testDao.getItem(itemTwo.getItemName()));
    }
}

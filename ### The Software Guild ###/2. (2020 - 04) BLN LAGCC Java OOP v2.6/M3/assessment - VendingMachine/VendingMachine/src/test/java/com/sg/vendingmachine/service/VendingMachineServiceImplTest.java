/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDaoStubImpl;
import com.sg.vendingmachine.dao.VendingMachineDaoImpl;
import com.sg.vendingmachine.dao.VendingMachineAuditDaoStubImpl;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.service.VendingMachineServiceImpl;
import com.sg.vendingmachine.service.BigDecimalMath;
import com.sg.vendingmachine.dto.VendingMachine;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.sg.vendingmachine.dto.CoinsReturned;
import com.sg.vendingmachine.dto.UserWallet;
import java.math.RoundingMode;

/**
 *
 * @author Music Account
 */
public class VendingMachineServiceImplTest {
    
    VendingMachineService serviceTest;
     VendingMachineDao daoTest;
    MathContext mc = new MathContext(2);
    BigDecimalMath mathCalculate = new BigDecimalMath();
    
    
    
    
    public VendingMachineServiceImplTest() {
        VendingMachineDao daoStub = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditStub = new VendingMachineAuditDaoStubImpl();
        
        serviceTest = new VendingMachineServiceImpl(daoStub, auditStub);
    }
 
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        String testFile = "VendingMachineTest.txt";
        daoTest = new VendingMachineDaoImpl(testFile);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineServiceImpl.
     */


//    @Test
//    public void testGetItem() throws Exception {
//        VendingMachine itemOne = new VendingMachine();
//        itemOne.setItemName("FRIED DOG");
//        itemOne.setItemCost(new BigDecimal(2.5).setScale(2, RoundingMode.FLOOR));
//        itemOne.setItemQuantity(50);
//        
//        UserWallet userWallet = new UserWallet();
//        userWallet.setMoney(new BigDecimal(10.0).setScale(2, RoundingMode.FLOOR));  
//        
//        VendingMachine matchItem = serviceTest.getItem(itemOne.getItemName());
//        assertEquals("FRIED DOG", matchItem.getItemName());
//    }

    @Test
    public void testGetAllItems() throws Exception {
        // ARRANGE
        VendingMachine item = new VendingMachine();
        item.setItemName("CANDY");
        item.setItemCost(new BigDecimal(2.5).round(mc));
        item.setItemQuantity(5);

        // ACT & ASSERT
        assertEquals( 1, serviceTest.getAllItems().size());
    }
    
    @Test
    public void testUpdateItems_VendingMachine() throws Exception {
        VendingMachine itemOne = new VendingMachine();
        itemOne.setItemName("fried dog");
        itemOne.setItemCost(new BigDecimal(2.5).setScale(2, RoundingMode.FLOOR));
        itemOne.setItemQuantity(50);
        
        UserWallet userWallet = new UserWallet();
        userWallet.setMoney(new BigDecimal(10.0).setScale(2, RoundingMode.FLOOR));
        
        CoinsReturned cr = serviceTest.updateItems(userWallet, itemOne);
        
        assertEquals(7, cr.getDollarCoins());
        assertEquals(2, cr.getQuarters());
        assertEquals(0, cr.getDimes());
        assertEquals(0, cr.getNickels());
        assertEquals(0, cr.getPennies());
    }
    
    @Test
    public void testRemoveItem() throws Exception {
        VendingMachine itemOne = new VendingMachine();
        itemOne.setItemName("steak");
        itemOne.setItemCost(new BigDecimal(2.5).setScale(2, RoundingMode.FLOOR));
        itemOne.setItemQuantity(50);
        
        UserWallet userWallet = new UserWallet();
        userWallet.setMoney(new BigDecimal(10.0).setScale(2, RoundingMode.FLOOR));
        
        serviceTest.updateItems(userWallet, itemOne);
        serviceTest.removeItem(itemOne);
        
        assertEquals(null, serviceTest.getItem(itemOne.getItemName()));
    }
    
}

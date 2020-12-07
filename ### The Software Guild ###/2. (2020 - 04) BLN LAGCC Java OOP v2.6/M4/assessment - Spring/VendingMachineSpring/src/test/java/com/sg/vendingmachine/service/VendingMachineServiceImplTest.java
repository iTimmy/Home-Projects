/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.service.VendingMachineServiceImpl;
import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.service.BigDecimalMath;
import com.sg.vendingmachine.dao.VendingMachineAuditDaoStubImpl;
import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDaoStubImpl;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoImpl;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Music Account
 */
public class VendingMachineServiceImplTest {
    
    VendingMachineService serviceTest;
    MathContext mc = new MathContext(2);
    BigDecimalMath mathCalculate = new BigDecimalMath();
    
    
    
    
    public VendingMachineServiceImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        serviceTest = ctx.getBean("service", VendingMachineService.class);
    }
 
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
    }
    
    @After
    public void tearDown() {
    }
    
    

    @Test
    public void testGetItem() throws Exception {
        List<VendingMachine> listItems = serviceTest.getAllItems();
        VendingMachine item = listItems.get(0); 
        assertEquals("CANDY", item.getItemName());
    }

    @Test
    public void testGetAllItems() throws Exception {
        List<VendingMachine> listItems = serviceTest.getAllItems();
        assertEquals(1, listItems.size());
    }
    
    @Test
    public void testUpdateItems_VendingMachine() throws Exception {
        List<VendingMachine> listItems = serviceTest.getAllItems();
        VendingMachine itemOne = listItems.get(0);
        
        UserWallet userWallet = new UserWallet();
        userWallet.setMoney(new BigDecimal(10.0).setScale(2, RoundingMode.FLOOR)); 
        CoinsReturned cr = serviceTest.updateItems(userWallet, itemOne);
        
        assertEquals(30, cr.getQuarters());
        assertEquals(0, cr.getDimes());
        assertEquals(0, cr.getNickels());
        assertEquals(0, cr.getPennies());
    }
    
    @Test
    public void testUpdateItem_VendingMachine() throws Exception {
        List<VendingMachine> listItems = serviceTest.getAllItems();
        VendingMachine itemOne = listItems.get(0);
        
        VendingMachine itemTwo = new VendingMachine();
        itemTwo.setItemName("CHIPS");
        itemTwo.setItemCost(new BigDecimal(4.34).setScale(2, RoundingMode.FLOOR));
        itemTwo.setItemQuantity(23);
        
        UserWallet userWallet = new UserWallet();
        userWallet.setMoney(new BigDecimal(10.0).setScale(2, RoundingMode.FLOOR));
        serviceTest.updateItems(userWallet, itemTwo);      
        assertEquals(itemTwo, listItems.get(0));
    }
    
    @Test
    public void testRemoveItem() throws Exception {
        List<VendingMachine> listItems = serviceTest.getAllItems();
        VendingMachine itemOne = listItems.get(0);
        serviceTest.removeItem(itemOne);
        assertNull(serviceTest.getItem(null));
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.*;
import com.sg.flooringmastery.service.BigDecimalMath;
import com.sg.flooringmastery.service.*;
import com.sg.flooringmastery.dao.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Music Account
 */
public class FlooringMasteryOrderDaoImplTest {
    
    FlooringMasteryOrderDao orderDaoTest;
    
    static Product productOne;
    static Product productTwo;
    static Tax taxOne;
    static Tax taxTwo;
    
    public FlooringMasteryOrderDaoImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        orderDaoTest = ctx.getBean("orderDao", FlooringMasteryOrderDao.class);
    }
    
    MathContext mc = new MathContext(2);
    BigDecimalMath mathCalculate = new BigDecimalMath();
    
    @BeforeClass
    public static void setUpClass() throws IOException {    
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        productOne = new Product();
        productOne.setProductType("Tile");
        productOne.setLaborCostPerSquareFoot(new BigDecimal(3).round(mc));
        productOne.setCostPerSquareFoot(new BigDecimal(3).round(mc));
        taxOne = new Tax();
        taxOne.setState("NY");
        taxOne.setTaxRate(new BigDecimal(3));       
        
        productTwo = new Product();
        productTwo.setProductType("Tile");
        productTwo.setLaborCostPerSquareFoot(new BigDecimal(3).round(mc));
        productTwo.setCostPerSquareFoot(new BigDecimal(3).round(mc));
        taxTwo = new Tax();
        taxTwo.setState("NY");
        taxTwo.setTaxRate(new BigDecimal(3));  
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testUpdateOrder() throws Exception {
        LocalDate date = LocalDate.of(2030, Month.DECEMBER, 5);
     
        Order existingOrder = new Order(1);
        existingOrder.setOrderDate(date);
        existingOrder.setCustomerName("A");
        existingOrder.setArea(new BigDecimal(3));
        existingOrder.setMaterialCost(new BigDecimal(3));
        existingOrder.setLaborCost(new BigDecimal(3));
        existingOrder.setOrderTax(new BigDecimal(3));
        existingOrder.setTotalCost(new BigDecimal(3));
        existingOrder.setProduct(productOne);
        existingOrder.setTax(taxOne);
        
        Order editedOrder = new Order(1);
        editedOrder.setOrderDate(date);
        editedOrder.setCustomerName("Kate");
        editedOrder.setArea(new BigDecimal(250).round(mc));
        editedOrder.setMaterialCost(new BigDecimal(3));
        editedOrder.setLaborCost(new BigDecimal(3));
        editedOrder.setOrderTax(new BigDecimal(3));
        editedOrder.setTotalCost(new BigDecimal(3));
        editedOrder.setProduct(productTwo);
        editedOrder.setTax(taxTwo);
        
        orderDaoTest.updateOrder(editedOrder, existingOrder);
        
        assertEquals(orderDaoTest.getOrderByID(1).getCustomerName(), editedOrder.getCustomerName());
    }
    
    @Test
    public void testDisplayOrders() throws Exception {   
        LocalDate date = LocalDate.of(2030, Month.DECEMBER, 5);
        
        Order orderOne = new Order();
        orderOne.setOrderDate(date);
        orderOne.setCustomerName("Timmy");
        orderOne.setArea(new BigDecimal(233).round(mc));
        orderOne.setProduct(productTwo);
        orderOne.setTax(taxOne);
       
        orderDaoTest.createOrder(orderOne);
        orderDaoTest.saveOrdersByDate();
        List<Order> ordersList = orderDaoTest.getOrdersByDate(orderOne.getOrderDate());
        ordersList.add(orderOne);
        
        Order orderTwo = new Order();
        orderTwo.setOrderDate(date);
        orderTwo.setCustomerName("Timmy");
        orderTwo.setArea(new BigDecimal(233).round(mc));
        orderTwo.setProduct(productOne);
        orderTwo.setTax(taxTwo);
       
        orderDaoTest.createOrder(orderTwo);
        orderDaoTest.saveOrdersByDate();
        ordersList.add(orderTwo);
        
        System.out.println(orderOne + "   " + orderTwo);
        System.out.println(ordersList);
        assertEquals(2, ordersList.size());
    }
    
    @Test
    public void testRemoveOrder() throws Exception {
//        LocalDate date = LocalDate.of(2030, Month.DECEMBER, 6);
//        
//        Order order = new Order();
//        order.setOrderDate(date);
//        order.setCustomerName("Timmy");
//        order.setArea(new BigDecimal(3));
//        order.setMaterialCost(new BigDecimal(3));
//        order.setLaborCost(new BigDecimal(3));
//        order.setOrderTax(new BigDecimal(3));
//        order.setTotalCost(new BigDecimal(3));
//        order.setProduct(productOne);
//        order.setTax(taxOne);
//        
//        orderDaoTest.createOrder(order);
//        assertEquals(1, orderDaoTest.getOrderByID(order.getOrderNumber()));

//        orderDaoTest.deleteOrder(order);             
//        assertEquals(orderDaoTest.getOrderByID(order.getOrderNumber()), null);
    }    
}

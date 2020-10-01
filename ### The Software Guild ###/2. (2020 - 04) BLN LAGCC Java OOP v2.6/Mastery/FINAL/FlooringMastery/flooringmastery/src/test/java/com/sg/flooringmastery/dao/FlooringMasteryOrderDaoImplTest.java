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
import java.time.LocalDate;
import java.time.Month;
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
public class FlooringMasteryOrderDaoImplTest {
    
    FlooringMasteryProductDao productDaoTest = new FlooringMasteryProductDaoImpl();
    FlooringMasteryTaxDao taxDaoTest = new FlooringMasteryTaxDaoImpl();
    
    public FlooringMasteryOrderDaoImplTest() {
    }
    
    FlooringMasteryOrderDao orderDaoTest = new FlooringMasteryOrderDaoImpl();
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
        String testFile = "DataExportTest.txt";
        // String testFileTwo = "Orders_3000-12-12.txt";
        orderDaoTest = new FlooringMasteryOrderDaoImpl(testFile/*, testFileTwo*/);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSaveOrders() throws Exception {
//        Order orderOne = new Order();
//        orderOne.setOrderDate(LocalDate.now());
//        orderOne.setCustomerName("Timmy");
//        orderOne.setArea(new BigDecimal(233).round(mc));
//       
//        Product productOne = new Product();
//        productOne.setProductType("Tile");
//        productOne.setCostPerSquareFoot(productDaoTest.getProductByName(productOne.getProductType()).getCostPerSquareFoot());
//        
//        Tax taxOne = new Tax();
//        taxOne.setState("TX");
//    
//        orderOne.setProduct(productOne);
//        orderOne.setTax(taxOne);
//   
//        orderDaoTest.createOrder(orderOne);
//        orderDaoTest.saveOrdersByDate();
    }
    
    @Test
    public void testUpdateOrder() throws Exception {
        LocalDate date = LocalDate.of(2030, Month.DECEMBER, 5);
        
        Order existingOrder = new Order();
        existingOrder.setOrderDate(date);
        existingOrder.setCustomerName("Timmy");
        existingOrder.setArea(new BigDecimal(233).round(mc));
       
        Product productOne = new Product();
        productOne.setProductType("Tile");
        productOne.setCostPerSquareFoot(productDaoTest.getProductByName(productOne.getProductType()).getCostPerSquareFoot());
        
        Tax taxOne = new Tax();
        taxOne.setState("TX");
    
        existingOrder.setProduct(productOne);
        existingOrder.setTax(taxOne);
        
        
        
        Order editedOrder = new Order();
        editedOrder.setOrderDate(date);
        editedOrder.setCustomerName("Timmy");
        editedOrder.setArea(new BigDecimal(233).round(mc));
       
        Product productTwo = new Product();
        productTwo.setProductType("Tile");
        productTwo.setCostPerSquareFoot(productDaoTest.getProductByName(productTwo.getProductType()).getCostPerSquareFoot());
        
        Tax taxTwo = new Tax();
        taxTwo.setState("TX");
    
        editedOrder.setProduct(productTwo);
        editedOrder.setTax(taxTwo);
        
        orderDaoTest.updateOrder(editedOrder, existingOrder);
        
//        System.out.println(existingOrder.getCustomerName());
//        System.out.println(editedOrder.getCustomerName());
        assertEquals(existingOrder.getCustomerName(), editedOrder.getCustomerName());
    }
    
    @Test
    public void testDisplayOrders() throws Exception {
        MathContext mc = new MathContext(4);
        BigDecimalMath calculate = new BigDecimalMath();
        
        LocalDate date = LocalDate.of(2030, Month.DECEMBER, 5);
        
        Order orderOne = new Order();
        orderOne.setOrderDate(date);
        orderOne.setCustomerName("Timmy");
        orderOne.setArea(new BigDecimal(233).round(mc));
       
        Product productOne = new Product();
        productOne.setProductType("Tile");
        productOne.setCostPerSquareFoot(productDaoTest.getProductByName(productOne.getProductType()).getCostPerSquareFoot());
        
        Tax taxOne = new Tax();
        taxOne.setState("TX");
    
        orderOne.setProduct(productOne);
        orderOne.setTax(taxOne);
   
        orderDaoTest.createOrder(orderOne);
        orderDaoTest.saveOrdersByDate();
        List<Order> ordersList = orderDaoTest.getOrdersByDate(orderOne.getOrderDate());
        ordersList.add(orderOne);
        
        Order orderTwo = new Order();
        orderTwo.setOrderDate(date);
        orderTwo.setCustomerName("Timmy");
        orderTwo.setArea(new BigDecimal(233).round(mc));
       
        Product productTwo = new Product();
        productTwo.setProductType("Tile");
        productTwo.setCostPerSquareFoot(productDaoTest.getProductByName(productTwo.getProductType()).getCostPerSquareFoot());
        
        Tax taxTwo = new Tax();
        taxTwo.setState("TX");
    
        orderTwo.setProduct(productTwo);
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
        LocalDate date = LocalDate.of(2030, Month.DECEMBER, 6);
        
        Order order = new Order();
        order.setOrderDate(date);
        order.setCustomerName("Timmy");
        order.setArea(new BigDecimal(233).round(mc));
       
        Product product = new Product();
        product.setProductType("Tile");
        product.setCostPerSquareFoot(productDaoTest.getProductByName(product.getProductType()).getCostPerSquareFoot());
        
        Tax tax = new Tax();
        tax.setState("TX");
    
        order.setProduct(product);
        order.setTax(tax);
        
        orderDaoTest.deleteOrder(order);      
        
        assertEquals(orderDaoTest.getOrderByID(order.getOrderNumber()), null);
    }    
}

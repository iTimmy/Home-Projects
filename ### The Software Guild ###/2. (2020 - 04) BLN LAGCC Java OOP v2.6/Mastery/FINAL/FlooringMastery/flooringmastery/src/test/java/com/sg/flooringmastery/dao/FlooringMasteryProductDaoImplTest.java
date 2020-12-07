/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import com.sg.flooringmastery.dao.*;
import com.sg.flooringmastery.dto.Product;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Music Account
 */
public class FlooringMasteryProductDaoImplTest {
    FlooringMasteryProductDao productDaoTest;
    
    public FlooringMasteryProductDaoImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        productDaoTest = ctx.getBean("productDao", FlooringMasteryProductDao.class);
    }
    
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
    
    
    
    @Test
    public void testGetAllProducts() {
        List<Product> listProducts = productDaoTest.getAllProducts();
        assertEquals(4, listProducts.size());
    }
    
    @Test
    public void testGetProductByName() {
        List<Product> listProducts = productDaoTest.getAllProducts();
        Product productOne = listProducts.get(0);
        Product productTwo = productDaoTest.getProductByName(productOne.getProductType());
        assertEquals(productOne, productTwo);
    }
}

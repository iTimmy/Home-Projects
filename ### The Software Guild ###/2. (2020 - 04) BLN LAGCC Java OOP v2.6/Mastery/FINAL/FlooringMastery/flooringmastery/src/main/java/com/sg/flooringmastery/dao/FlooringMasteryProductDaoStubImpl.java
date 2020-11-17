/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Music Account
 */
@Component
public class FlooringMasteryProductDaoStubImpl implements FlooringMasteryProductDao {
    public Product onlyProduct;

    @Autowired
    public FlooringMasteryProductDaoStubImpl() {
        onlyProduct = new Product();
        onlyProduct.setProductType("Vinyl");
    }

    public FlooringMasteryProductDaoStubImpl(Product testProduct){
         this.onlyProduct = testProduct;
     }
    
    @Override
    public List<Product> getAllProducts() {
        List<Product> listProducts = new ArrayList<>();
        listProducts.add(onlyProduct);
        return listProducts;
    }
    
    @Override
    public Product getProductByName(String productName) {
        if (productName.toUpperCase().equals(onlyProduct.getProductType().toUpperCase())) {
            return onlyProduct;
        } else {
            return null;
        }  
    }
}

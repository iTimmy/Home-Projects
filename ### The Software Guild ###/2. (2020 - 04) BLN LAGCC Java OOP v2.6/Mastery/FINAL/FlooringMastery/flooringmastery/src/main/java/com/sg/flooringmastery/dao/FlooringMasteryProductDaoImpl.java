package com.sg.flooringmastery.dao;

import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;

import com.sg.flooringmastery.dto.*;
import org.springframework.beans.factory.annotation.Autowired;

public class FlooringMasteryProductDaoImpl implements FlooringMasteryProductDao {

    @Autowired
    Map<String, Product> storeProduct = new HashMap<>();
    private String file = "Data/Products/Products.txt";
    File fileProducts = new File(file);

//    @Autowired
//    public FlooringMasteryProductDaoImpl() {
//        this.file = "Data/Products/Products.txt";
//    }
//    public FlooringMasteryProductDaoImpl(String textFile) {
//        this.file = textFile;
//    }
    
    @Override
    public List<Product> getAllProducts() {
        try {
            loadProducts();
        } catch (Exception e) {
            System.out.println("Error.");
        }
        List<Product> listProducts = new ArrayList<>(storeProduct.values());
        return listProducts;
    }

    @Override
    public Product getProductByName(String productName) {
        try {
            if (storeProduct.isEmpty() == true) {
                loadProducts();
            }
        } catch (Exception e) {}
        System.out.println(storeProduct.get("Tile"));
        return storeProduct.get(productName);
    }
    
    private void loadProducts() throws Exception {
        Scanner readFile = new Scanner(new BufferedReader(new FileReader(fileProducts)));
        String currentLine = "";
        while(readFile.hasNextLine()) {
            currentLine = readFile.nextLine();
            Product currentProduct = unmarshallProducts(currentLine);
            storeProduct.put(currentProduct.getProductType(), currentProduct);
        }
    }

    private Product unmarshallProducts(String currentLine) {
        String[] productTokens = currentLine.split(",");
        String productType = productTokens[0];
        BigDecimal costPerSquareFoot = new BigDecimal(productTokens[1]);
        BigDecimal laborCostPerSquareFoot = new BigDecimal(productTokens[2]);
        
        Product product = new Product();
        product.setProductType(productType);
        product.setCostPerSquareFoot(costPerSquareFoot);
        product.setLaborCostPerSquareFoot(laborCostPerSquareFoot);

        return product;
    }
}

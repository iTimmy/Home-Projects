package dao;

import java.util.*;
import java.io.*;
import java.math.BigDecimal;

import dto.*;

public class FlooringMasteryProductDaoImpl implements FlooringMasteryProductDao {

    Map<String, Product> storeProduct = new HashMap<>();
    File fileProducts = new File("Data/Products/Products.txt");

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
            } else if (storeProduct.isEmpty() == false) {
                for (Product currentProduct : storeProduct.values()) {
                    if (currentProduct.getProductType().equals(productName)) {
                        return storeProduct.get(productName);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error.");
        }
        return null;
    }
    
    private void loadProducts() throws Exception {
        Scanner readFile = new Scanner(new BufferedReader(new FileReader(fileProducts)));
        String currentLine = "";
        while(readFile.hasNextLine()) {
            currentLine = readFile.nextLine();
            Product currentProduct = unmarshallProducts(currentLine);
            storeProduct.put(currentLine, currentProduct);
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

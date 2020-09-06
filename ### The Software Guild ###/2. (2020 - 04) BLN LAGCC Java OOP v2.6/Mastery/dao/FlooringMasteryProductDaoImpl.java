package dao;

import java.util.*;
import dto.*;

public class FlooringMasteryProductDaoImpl implements FlooringMasteryProductDao {

    Map<String, Product> storeProduct = new HashMap<>();
    public static final String DELIMITER = " :: ";

    @Override
    public List<Product> getAllProducts() {
        List<Product> listProducts = new ArrayList<Product>(storeProduct.values());
        return listProducts;
    }

    @Override
    public Product getProductByName(String productName) {
        return storeProduct.get(productName);
    }
    
}

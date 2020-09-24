package dao;

import java.util.*;
import dto.*;

public interface FlooringMasteryProductDao {
    List<Product> getAllProducts();
    Product getProductByName(String productName);
}

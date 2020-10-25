package com.sg.flooringmastery.dao;

import java.util.*;
import com.sg.flooringmastery.dto.*;

public interface FlooringMasteryProductDao {
    List<Product> getAllProducts();
    Product getProductByName(String productName);
}

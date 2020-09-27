package com.sg.flooringmastery.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import com.sg.flooringmastery.dto.*;

public interface FlooringMasteryService {

    Order createOrder(Order order);
    List<Order> getAllOrders() throws Exception;
    List<Order> getOrdersByDate(LocalDate userInputDate) throws Exception;
    void updateOrder(Order orderNumber);
    void deleteOrder(Order orderNumber);
    boolean saveOrdersByDate(LocalDate userInputOrderDate) throws Exception;

    List<Product> getAllProducts();
    Product getProductByName(String productName);

    List<Tax> getAllTaxes();
    Tax getTaxByState(String state);
}

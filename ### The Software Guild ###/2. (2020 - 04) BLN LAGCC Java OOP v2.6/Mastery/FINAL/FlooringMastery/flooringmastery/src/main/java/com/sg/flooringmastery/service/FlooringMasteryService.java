package com.sg.flooringmastery.service;

import java.time.LocalDate;
import java.util.*;
import com.sg.flooringmastery.dto.*;

public interface FlooringMasteryService {

    Order createOrder(Order order) throws Exception;
    List<Order> getOrdersByDate(LocalDate userInputDate) throws Exception;
    Order getOrderByID(int orderNumber);
    void updateOrder(Order editedOrder, Order existingOrder) throws Exception;
    void deleteOrder(Order orderNumber) throws Exception;
    boolean saveOrdersByDate() throws Exception;

    List<Product> getAllProducts();
    Product getProductByName(String productName);

    List<Tax> getAllTaxes();
    Tax getTaxByState(String state);
}

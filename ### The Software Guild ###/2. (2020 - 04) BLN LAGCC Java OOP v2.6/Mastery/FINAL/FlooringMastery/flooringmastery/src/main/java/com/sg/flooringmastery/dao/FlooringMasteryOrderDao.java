package com.sg.flooringmastery.dao;

import java.time.LocalDate;
import java.util.*;
import com.sg.flooringmastery.dto.*;

public interface FlooringMasteryOrderDao {
    Order createOrder(Order order);
    List<Order> getAllOrders() throws Exception;
    List<Order> getOrdersByDate(LocalDate userInputDate) throws Exception;
    Order getOrderByID(int orderNumber);
    void updateOrder() throws Exception;
    void deleteOrder(Order order);
    boolean saveOrdersByDate(LocalDate userInputOrderDate) throws Exception;
}

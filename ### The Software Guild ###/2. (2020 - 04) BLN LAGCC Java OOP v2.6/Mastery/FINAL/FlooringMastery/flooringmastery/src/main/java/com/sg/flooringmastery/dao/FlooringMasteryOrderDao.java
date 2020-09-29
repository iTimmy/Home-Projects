package com.sg.flooringmastery.dao;

import java.time.LocalDate;
import java.util.*;
import com.sg.flooringmastery.dto.*;

public interface FlooringMasteryOrderDao {
    Order createOrder(Order order) throws Exception;
    List<Order> getAllOrders() throws Exception;
    List<Order> getOrdersByDate(LocalDate userInputDate) throws Exception;
    Order getOrderByID(int orderNumber);
    void updateOrder(Order order) throws Exception;
    void deleteOrder(Order order) throws Exception;
    boolean saveOrdersByDate() throws Exception;
}

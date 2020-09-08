package dao;

import java.time.LocalDate;
import java.util.*;
import dto.*;

public interface FlooringMasteryOrderDao {
    Order createOrder(Order order);
    List<Order> getAllOrders(boolean loadOrSave) throws Exception;
    Order getOrderByDate(LocalDate userInputDate);
    Order getOrderByID(int orderNumber);
    void updateOrder(Order orderNumber);
    void deleteOrder(Order orderNumber);
}

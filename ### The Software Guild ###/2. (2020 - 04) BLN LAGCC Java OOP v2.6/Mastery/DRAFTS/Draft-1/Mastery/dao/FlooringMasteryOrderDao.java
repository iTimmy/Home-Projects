package dao;

import java.time.LocalDate;
import java.util.*;
import dto.*;

public interface FlooringMasteryOrderDao {
    Order createOrder(Order order);
    List<Order> getAllOrders(LocalDate userInputOrderDate) throws Exception;
    boolean saveAllOrders(LocalDate userInputOrderDate) throws Exception;
    boolean getOrderByDate(LocalDate userInputDate);
    void updateOrder(boolean refreshOrNot, LocalDate userInputOrderDate, boolean loadOrSave) throws Exception;
    void deleteOrder(Order orderNumber);
}

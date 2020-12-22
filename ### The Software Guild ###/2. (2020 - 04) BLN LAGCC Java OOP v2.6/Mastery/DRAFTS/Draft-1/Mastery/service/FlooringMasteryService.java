package service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import dto.*;

public interface FlooringMasteryService {

    Order createOrder(Order order);
    List<Order> getAllOrders(LocalDate userInputOrderDate) throws Exception;
    boolean saveAllOrders(LocalDate userInputOrderDate) throws Exception;
    boolean getOrderByDate(LocalDate userInputDate);
    void updateOrder(Order orderNumber);
    void deleteOrder(Order orderNumber);

    List<Product> getAllProducts();
    Product getProductByName(String productName);

    List<Tax> getAllTaxes();
    Tax getTaxByState(String state);
}

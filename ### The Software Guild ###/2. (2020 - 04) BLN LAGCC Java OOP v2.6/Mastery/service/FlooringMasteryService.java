package service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import dto.*;

public interface FlooringMasteryService {

    Order createOrder(Order order);
    List<Order> getAllOrders(boolean loadOrSave) throws Exception;
    Order getOrderByID(LocalDate userInputDate);
    void updateOrder(Order orderNumber);
    void deleteOrder(Order orderNumber);

    List<Product> getAllProducts();
    Product getProductByName(String productName);

    List<Tax> getAllTaxes();
    Tax getTaxByFloat(BigDecimal tax);
}

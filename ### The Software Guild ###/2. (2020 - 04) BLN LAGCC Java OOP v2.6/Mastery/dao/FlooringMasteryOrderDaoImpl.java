package dao;

import java.time.LocalDate;
import java.util.*;
import dto.*;

public class FlooringMasteryOrderDaoImpl implements FlooringMasteryOrderDao {

    FlooringMasteryStorage storage = new FlooringMasteryStorage();
    public static final String DELIMITER = " :: ";

    @Override
    public Order createOrder(Order order) {
        System.out.println("Running dao layer...");
        storage.storeOrders.put(order.getOrderDate(), order);
        System.out.println(storage.storeOrders);
        return order;
    }

    @Override
    public List<Order> getAllOrders(boolean loadOrSave) throws Exception {
        storage.dataAccess(loadOrSave);
        List<Order> listOrders = new ArrayList<>(storage.storeOrders.values());
        return listOrders;
    }

    @Override
    public Order getOrderByID(LocalDate userInputDate) {
        if (userInputDate != storage.storeOrders.get(userInputDate).getOrderDate()) {
            return null;
        } else {
            System.out.println("Good!");
        }
        return storage.storeOrders.get(userInputDate);
    }

    @Override
    public void updateOrder(Order orderNumber) {

    }

    @Override
    public void deleteOrder(Order orderNumber) {

    }
}

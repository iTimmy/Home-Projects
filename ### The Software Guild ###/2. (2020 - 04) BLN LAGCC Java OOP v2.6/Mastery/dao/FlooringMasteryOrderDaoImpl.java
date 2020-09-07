package dao;

import java.time.LocalDate;
import java.util.*;
import dto.*;

public class FlooringMasteryOrderDaoImpl implements FlooringMasteryOrderDao {

    FlooringMasteryStorage storage = new FlooringMasteryStorage();

    @Override
    public Order createOrder(Order order) {
        System.out.println("Running dao layer...");
        storage.storeOrders.put(order.getOrderDate(), order);
        System.out.println(storage.storeOrders);
        System.out.println(storage.storeOrders.size());
        return order;
    }

    @Override
    public List<Order> getAllOrders(boolean loadOrSave) throws Exception {
        if (loadOrSave == false) {
            storage.dataAccess(loadOrSave);
        }
        List<Order> listOrders = new ArrayList<>(storage.storeOrders.values());
        if (loadOrSave == true) {
            if (storage.dataAccess(loadOrSave) == false) {
                return null;
            }
        }
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

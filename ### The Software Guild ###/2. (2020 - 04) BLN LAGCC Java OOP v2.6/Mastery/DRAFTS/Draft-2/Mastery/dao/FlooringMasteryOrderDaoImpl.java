package dao;

import java.time.LocalDate;
import java.util.*;
import dto.*;

public class FlooringMasteryOrderDaoImpl implements FlooringMasteryOrderDao {

    FlooringMasteryStorage storage = new FlooringMasteryStorage();
    private boolean save = true;
    private boolean load = false;
    private boolean refresh = true;
    private boolean noRefresh = false;

    @Override
    public Order createOrder(Order order) {
        storage.refreshData(noRefresh);
        storage.storeOrders.put(order.getOrderNumber(), order);
        try {
            storage.findOrderByDate(order.getOrderDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public boolean saveAllOrders(LocalDate userInputOrderDate) throws Exception {
        if (storage.storeOrders().isEmpty() == true) {
            return false;
        } else if (storage.storeOrders().isEmpty() == false) {
            storage.dataAccess(load);
            storage.findOrderByDate(userInputOrderDate);
            storage.dataAccess(save);
            storage.refreshData(refresh);

            return true;
        }
        storage.refreshData(true);
        // System.out.println("2saveAllOrders(): " + storage.storeOrders());
        return true;
    }

    @Override
    public List<Order> getAllOrders(LocalDate userInputOrderDate) throws Exception {
        updateOrder(refresh, userInputOrderDate, load);
        System.out.println(storage.storeOrders());
        List<Order> listOrders = new ArrayList<>(storage.storeOrders.values());
        return listOrders;
    }

    @Override
    public boolean getOrderByDate(LocalDate userInputDate) throws Exception {
        System.out.println("dao: " + userInputDate);
        storage.refreshData(true);
        if (storage.findOrderByDate(userInputDate) == null) {
            System.out.println("SHOULD WORK");
            return false;
        } else {
            System.out.println("SHOULD WORK RETURNING TRUE");
            return true;
        }
    }

    @Override
    public void updateOrder(boolean refreshOrNot, LocalDate userInputOrderDate, boolean loadOrSave) throws Exception {
        storage.refreshData(refreshOrNot); //true
        storage.findOrderByDate(userInputOrderDate);
        if (loadOrSave == load) {
            System.out.println("bitch");
            storage.dataAccess(load); // false
        } else if (loadOrSave == save) {
            storage.dataAccess(save); // true
        }
    }

    private void searching() {
        System.out.println("[3] Searching...");
    }

    @Override
    public void deleteOrder(Order orderNumber) {
        searching();
        try {
            updateOrder(noRefresh, orderNumber.getOrderDate(), load);
            for (Order currentOrder : storage.storeOrders.values()) {
                if (orderNumber.getOrderNumber() == (currentOrder.getOrderNumber())) {
                    storage.storeOrders.remove(orderNumber.getOrderNumber());
                    storage.dataAccess(true);
                } else {
                    System.out.println("Order does not exist.");
                }
            }
        } catch (Exception e) {
            
        }    
    }
}

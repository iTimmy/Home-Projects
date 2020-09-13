package dao;

import java.time.LocalDate;
import java.util.*;
import dto.*;

public class FlooringMasteryOrderDaoImpl implements FlooringMasteryOrderDao {

    FlooringMasteryStorage storage = new FlooringMasteryStorage();

    @Override
    public Order createOrder(Order order) {
        storage.refreshData(false);
        storage.storeOrders.put(order.getOrderNumber(), order);
        try {
            storage.findOrderByDate(order.getOrderDate());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public boolean saveAllOrders(LocalDate userInputOrderDate) throws Exception {
        if (storage.storeOrders().isEmpty() == true) {
            return false;
        } else if (storage.storeOrders().isEmpty() == false) {
            storage.dataAccess(false);
            System.out.println(storage.storeOrders());
            storage.findOrderByDate(userInputOrderDate);
            storage.dataAccess(true);
            storage.refreshData(true);
            System.out.println("s1aveAllOrders(): " + storage.storeOrders());
            return true;
        }
        storage.refreshData(true);
        System.out.println("2saveAllOrders(): " + storage.storeOrders());
        return true;
    }

    @Override
    public List<Order> getAllOrders(LocalDate userInputOrderDate) throws Exception {
        storage.refreshData(true);
        storage.findOrderByDate(userInputOrderDate);
        storage.dataAccess(false);
        List<Order> listOrders = new ArrayList<>(storage.storeOrders.values());
        return listOrders;
    }

    @Override
    public boolean getOrderByDate(LocalDate userInputDate) {
        try {
            storage.refreshData(true);
            storage.findOrderByDate(userInputDate);
        } catch (Exception e) {
    
        }
        return true;
    }

    @Override
    public void updateOrder(Order orderNumber) {

    }

    private void searching() {
        System.out.println("[3] Searching...");
    }

    @Override
    public void deleteOrder(Order orderNumber) {
        //storage.refreshData(false);
        searching();
        try {
            storage.findOrderByDate(orderNumber.getOrderDate());
            for (Order currentOrder : storage.storeOrders.values()) {
                if (orderNumber.getOrderNumber() == (currentOrder.getOrderNumber())) {
                    System.out.println("before remove: " + storage.storeOrders);
                    storage.storeOrders.remove(orderNumber.getOrderNumber());
                    storage.dataAccess(true);
                    System.out.println(storage.storeOrders);
                } else {
                    System.out.println(storage.storeOrders);
                    System.out.println("Order does not exist.");
                }
            }
        } catch (Exception e) {
            
        }     
    }
}

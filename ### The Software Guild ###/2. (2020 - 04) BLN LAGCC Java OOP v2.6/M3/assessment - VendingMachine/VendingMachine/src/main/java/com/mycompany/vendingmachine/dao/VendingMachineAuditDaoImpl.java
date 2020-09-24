package com.mycompany.vendingmachine.dao;

import java.time.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachineAuditDaoImpl implements VendingMachineAuditDao {

    private final File auditLogFile = new File("audit-log.txt");
    List<String> auditStore = new ArrayList<>();

	public void orderDate(String userInputItemName, BigDecimal userChange) throws Exception {
            PrintWriter fileWrite = new PrintWriter(new BufferedWriter(new FileWriter(auditLogFile, true)));
            LocalDate orderDateToday = LocalDate.now();
            LocalTime orderTimeToday = LocalTime.now();
            String orderTimeTodayString = orderTimeToday.toString();
            // int orderTimeTodayInt = Math.round(Integer.parseInt(orderTimeTodayString));
            String orderDateTodayString = orderDateToday.toString();
            String orderWrite = "You received " + userChange + " in change."; 

            auditStore.add(orderDateTodayString + "::" + orderTimeTodayString + " | " + orderWrite + " | " + userInputItemName);
            fileWrite.println(auditStore.toString());
            fileWrite.flush();
            fileWrite.close();
    }
}
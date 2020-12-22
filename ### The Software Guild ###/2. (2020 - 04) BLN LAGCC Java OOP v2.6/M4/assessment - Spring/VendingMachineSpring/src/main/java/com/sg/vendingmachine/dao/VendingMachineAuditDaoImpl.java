package com.sg.vendingmachine.dao;

import java.time.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

public class VendingMachineAuditDaoImpl implements VendingMachineAuditDao {

    private final String file;
    private final File auditLogFile = new File("audit-log.txt");
    List<String> auditStore = new ArrayList<>();
    
    @Autowired
    public VendingMachineAuditDaoImpl() {
        this.file = "audit-log.txt";
    }
    public VendingMachineAuditDaoImpl(String textFile) {
        this.file = textFile;
    }

	public void orderDate(String userInputItemName, BigDecimal userChange) throws IOException {
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
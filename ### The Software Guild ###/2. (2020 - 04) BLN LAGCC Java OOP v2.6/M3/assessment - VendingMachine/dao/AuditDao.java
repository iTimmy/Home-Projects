package dao;

import java.time.*;
import java.io.*;
import java.util.*;

public class AuditDao {

    private final File auditLogFile = new File("audit-log.txt");
    List<String> auditStore = new ArrayList<>();

	public void orderDate(String orderWrite, String userInputItemName) throws Exception {
        PrintWriter fileWrite = new PrintWriter(new BufferedWriter(new FileWriter(auditLogFile, true)));
        LocalDate orderDateToday = LocalDate.now();
        LocalTime orderTimeToday = LocalTime.now();
        String orderTimeTodayString = orderTimeToday.toString();
        // int orderTimeTodayInt = Math.round(Integer.parseInt(orderTimeTodayString));
        String orderDateTodayString = orderDateToday.toString();

        auditStore.add(orderDateTodayString + "::" + orderTimeTodayString + " | " + orderWrite + " | " + userInputItemName);
        fileWrite.println(auditStore.toString());
        fileWrite.flush();
        fileWrite.close();
    }
}
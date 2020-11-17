package com.sg.flooringmastery.view;

import java.time.LocalDate;
import java.time.Month;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserIODecodeImpl extends UserIOConsoleImpl {
    public String nameFormat(String str, int p) {
        String newStr = "";
        String trimFirstChar = str.substring(0, p) + str.substring(p + 1);
        String addFirstChar = str.substring(0, 1).toUpperCase();
        return newStr = addFirstChar + trimFirstChar;
    }

    public String stateFormat(String state) {
        String newState = state.toUpperCase();
        return newState;
    }

    public LocalDate dateFormat(String date) {
        LocalDate orderOfDate = LocalDate.now();
        int i = 0;

        String yearString = "";
        String dayString = "";

        String dateSplit[] = date.split("-");
        for (String s : dateSplit) {
            if (i == 0) {
                yearString = s;
            } else if (i == 2) {
                dayString = s;
            }
            i++;
        }
        String dateMonth = dateSplit[1];
        int year = Integer.parseInt(yearString);
        int day = Integer.parseInt(dayString);

        orderOfDate = LocalDate.of(year, monthFormat(dateMonth), day);
        return orderOfDate;
    }
    
    public Month monthFormat(String dateMonth) {
        Month month = Month.JANUARY;
        if (dateMonth.equals("01")) {
            month = Month.JANUARY;
        } else if (dateMonth.equals("02")) {
            month = Month.FEBRUARY;
        } else if (dateMonth.equals("03")) {
            month = Month.MARCH;
        } else if (dateMonth.equals("04")) {
            month = Month.APRIL;
        } else if (dateMonth.equals("05")) {
            month = Month.MAY;
        } else if (dateMonth.equals("06")) {
            month = Month.JUNE;
        } else if (dateMonth.equals("07")) {
            month = Month.JULY;
        } else if (dateMonth.equals("08")) {
            month = Month.AUGUST;
        } else if (dateMonth.equals("09")) {
            month = Month.SEPTEMBER;
        } else if (dateMonth.equals("10")) {
            month = Month.OCTOBER;
        } else if (dateMonth.equals("11")) {
            month = Month.NOVEMBER;
        } else if (dateMonth.equals("12")) {
            month = Month.DECEMBER;
        } else {
            return null;
        }
        return month;
    }

    public void keyPressed(java.awt.event.KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == java.awt.event.KeyEvent.VK_ESCAPE) {

        }
        if (keyCode == java.awt.event.KeyEvent.VK_ENTER) {

        }
    }
}

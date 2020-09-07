package view;

import java.time.Month;

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
}

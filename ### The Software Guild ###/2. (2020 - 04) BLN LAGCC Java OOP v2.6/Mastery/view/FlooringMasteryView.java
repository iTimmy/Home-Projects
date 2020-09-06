package view;

import java.util.*;
import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import dto.*;
import service.StateTaxes;
import service.States;

public class FlooringMasteryView {
    UserIO io = new UserIOConsoleImpl();

    /*
    public FlooringMasteryView(UserIO io) {
        this.io = io;
    } */

    int i = 0;
    String EDIT = "";
    String displayAddEditOrderTitle = "ADD";

    public void displayMenu() {
        io.println(
        "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n" +
        "*\n" +
        "* <<Flooring Program>>\n" + 
        "* 1. Display Orders\n" +
        "* 2. Add an Order\n" +
        "* 3. Edit an Order\n" +
        "* 4. Remove an Order\n" +
        "* 5. Export All Data\n" +
        "* 6. Quit\n" +
        "*\n" +
        "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *"
        );
    }

    public int displaySelection() {
        int userSelect = io.readInt("Select by number: ");
        return userSelect;
    }

    public Month monthDecode(String dateMonth) {
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
        }
        return month;
    }

    public LocalDate displayInputDate() {
        int i = 0;
        String yearString = ""; String monthString = ""; String dayString = "";
        String date = io.readString("Provide the date of the order (yyyy-MMM-dd): ");
        String dateSplit[] = date.split("-");
        for (String s : dateSplit) {
            if (i == 0) {
                yearString += s;
            } else if (i == 1) {
                monthString += s;
            } else if (i == 2) {
                dayString += s;
            }
            i++;
        }
        String dateMonth = dateSplit[1];
        monthDecode(dateMonth);
            
        int year = Integer.parseInt(yearString); int day = Integer.parseInt(dayString);
        final LocalDate orderOfDate = LocalDate.of(year, monthDecode(dateMonth), day);
        System.out.println(orderOfDate);
        return orderOfDate;
    }

    public int displayOrderNumber() {
        int orderNumber = io.readInt("Provide the order #: ");
        return orderNumber;
    }

    public void displayDisplayOrdersTitle() {
        io.println("---------- DISPLAY ORDERS ----------");
    }   

    public void displayDisplayOrders(List<Order> listOrderDetails) {
        listOrderDetails.stream().forEach((p) -> {
            io.println("____________________________________");
            io.println("Order #: " + p.getOrderNumber());
            io.println("Customer Name: " + p.getCustomerName());
            io.println("Area: " + p.getArea());
            io.println("Material Cost: " + p.getMaterialCost());
            io.println("Labor Cost: " + p.getLaborCost());
            io.println("Order Tax: " + p.getOrderTax());
            io.println("Total Cost: " + p.getTotalCost());
            io.println("____________________________________");
        });
    }

    public Order displayAddEditOrder() {
        io.println("---------- " + displayAddEditOrderTitle + " ORDER(S) ----------");
        Order newOrder = new Order();
        if (i == 0) {
            displayInputDate();
            io.print("\n");
            i = 1;
        }
        
        if (i == 1) {
            double areaSize = 0;

            String customerName = io.readString("Customer Name" + EDIT + ": ");
            String stateName = io.readString("State" + EDIT + ": ");

            // PRODUCT TYPE \\
            String tiles = "Tiles - "; double tileCost = 1.27;
            String carpet = "Carpet - "; double carpetCost = 2.67;
            String wood = "Wood - "; double woodCost = 4.45;

            String productList = io.readString(
            tiles + tileCost + "\n" +
            carpet + carpetCost + "\n" +
            wood + woodCost + "\n");

            double costPerSquareFootDouble = 0;
            double laborCostPerSquareFootDouble = 2.12;
            String productType = io.readString("Product Type" + EDIT + ": ");

            // AREA \\
            while (areaSize < 100) {
                areaSize = io.readDouble("Area" + EDIT + ": ");
                if (areaSize < 100) {
                    io.println("The minimum size is 100(sq ft.)\nPlease choose an area size that is greater.");
                } else if (areaSize > 100) {
                    io.println("Data collected.");
                }
            }

            // PRODUCT: LOGIC \\
            switch (productType) {
                case "TILES":
                    costPerSquareFootDouble = areaSize * tileCost;
                case "CARPET":
                    costPerSquareFootDouble = areaSize * carpetCost;
                case "WOOD":
                    costPerSquareFootDouble = areaSize * woodCost;
            }

            BigDecimal area = new BigDecimal(areaSize);
            BigDecimal costPerSquareFoot = new BigDecimal(costPerSquareFootDouble);

            Product newProduct = new Product();
            newProduct.setProductType(productType);
            newProduct.setCostPerSquareFoot(costPerSquareFoot);

            Tax newTax = new Tax();
            newTax.setState(stateName);

            newOrder.setCustomerName(customerName);
            newOrder.setArea(area);
            newOrder.setProduct(newProduct);
            newOrder.setTax(newTax);
        }

        if (i == 1) {
            i = 0;
            EDIT = "";
            displayAddEditOrderTitle = "ADD";
        }

        return newOrder;
    }

    public void triggerEdit() {
        EDIT = " (EDIT)";
        i = 1;
        displayAddEditOrderTitle = "EDIT";
        displayAddEditOrder();
    }

    public void displayRemoveOrder() {
        io.println("---------- REMOVE AN ORDER ----------");
        displayInputDate();
        io.println("\n");
        displayOrderNumber();
        io.println("\n");
    }

    public void displayExportAllData() {
        io.println("---------- EXPORT ALL DATA ----------");
    }

    public void displayQuit() {
        io.println("___________________\nQuitting...");
    }

	public void displayProgress() {
        io.println("Saving...");
	}

	public void displaySuccess() {
        io.println("Success!");
    }
}

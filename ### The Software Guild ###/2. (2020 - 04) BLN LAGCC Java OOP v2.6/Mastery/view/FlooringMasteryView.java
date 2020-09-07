package view;

import java.util.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.*;
import java.time.format.DateTimeFormatter;
import dto.*;
import service.StateTaxes;
import service.States;

public class FlooringMasteryView {
    UserIO io = new UserIOConsoleImpl();
    UserIODecodeImpl decode = new UserIODecodeImpl();

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
        int userSelect = 0;
        try {
            userSelect = io.readInt("Select by number: ");
        } catch (Exception e) {
            io.println("Please type a number in the given range to select an option.");
        }
        return userSelect;
    }

    public LocalDate displayInputDate() {
        // INIT \\
        int valid = 0;
        LocalDate orderOfDate = LocalDate.now();

        // LOGIC \\
        while (valid != 1) {
            try{
                int i = 0;
                String yearString = ""; String monthString = ""; String dayString = "";
                String date = io.readString("Provide a future date of your order (yyyy-MMM-dd): ");
                String dateSplit[] = date.split("-");
                for (String s : dateSplit) {
                    if (i == 0) {
                        yearString = s;
                    } else if (i == 1) {
                        monthString = s;
                    } else if (i == 2) {
                        dayString = s;
                    }
                    i++;
                }
                String dateMonth = dateSplit[1];
                int year = Integer.parseInt(yearString); int day = Integer.parseInt(dayString);

                decode.monthFormat(dateMonth);

                orderOfDate = LocalDate.of(year, decode.monthFormat(dateMonth), day);
                
                if (orderOfDate.isBefore(LocalDate.now())) {
                    io.println("Today is " + LocalDate.now() + ". You cannot reverse time.");
                    valid = 0;
                    System.out.println(valid);
                } else if (orderOfDate.isAfter(LocalDate.now())) {
                    valid = 1;
                }
            } catch (Exception e) {
                io.println("xxxxxx Invalid date format! xxxxxx ");
            }
        }
        return orderOfDate;
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

        // JUST INIT \\
        LocalDate orderDate = LocalDate.now();
        StateTaxes state = new StateTaxes();
        boolean validationState = false;
        MathContext mc = new MathContext(3);

        if (i == 0) {
            orderDate = displayInputDate();
            io.print("\n");
            i = 1;
        }
        
        if (i == 1) {
            double areaSize = 0;
            String stateName = "";

            String customerName = io.readString("Customer Name" + EDIT + ": ");
            customerName = decode.nameFormat(customerName, 0);
            
            // STATE VALIDATION \\
            //while(validationState != true) {
                try {
                    stateName = io.readString("\nState" + EDIT + ": ");
                    stateName = decode.stateFormat(stateName);
                    States validateState = state.stringToState(stateName);
                    // System.out.println(validateState);
                } catch (Exception e) {
                    io.println("Please put in a valid state.");
                    // validationState = false;
                }
            //}

            // PRODUCT TYPE \\
            String tiles = "Tiles - "; double tileCost = 1.27;
            String carpet = "Carpet - "; double carpetCost = 2.67;
            String wood = "Wood - "; double woodCost = 4.45;

            io.println(
            "\n####################\n" +
            tiles + tileCost + "\n" +
            carpet + carpetCost + "\n" +
            wood + woodCost + "\n" +
            "####################");

            double costPerSquareFootDouble = 0;
            String productType = "";

            while(!productType.toUpperCase().equals("TILES") && !productType.toUpperCase().equals("CARPET") && !productType.toUpperCase().equals("WOOD")) {
                productType = io.readString("Please choose a valid product type" + EDIT + ": ");
            }
            // AREA \\
            while (areaSize < 100) {
                areaSize = io.readDouble("\nArea ft^2" + EDIT + ": ");
                if (areaSize < 100) {
                    io.println("The minimum size is 100(sq ft.)\nPlease choose an area size that is greater.");
                } else if (areaSize > 100) {
                    io.println("\n______________\nData collected.");
                }
            }

            // PRODUCT: LOGIC \\ {
            switch (productType.toUpperCase()) {
                case "TILES":
                    costPerSquareFootDouble = areaSize * tileCost;
                case "CARPET":
                    costPerSquareFootDouble = areaSize * carpetCost;
                case "WOOD":
                    costPerSquareFootDouble = areaSize * woodCost;
            }

            BigDecimal area = new BigDecimal(areaSize).round(mc);
            BigDecimal costPerSquareFoot = new BigDecimal(costPerSquareFootDouble);
            System.out.println(area);
            System.out.println(costPerSquareFoot);

            Product newProduct = new Product();
            newProduct.setProductType(productType.toUpperCase());
            newProduct.setCostPerSquareFoot(costPerSquareFoot);

            Tax newTax = new Tax();
            newTax.setState(stateName);

            newOrder.setOrderDate(orderDate);
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
    }

    public void displayExportAllData() {
        io.println("---------- EXPORT ALL DATA ----------");
    }

    public void displayQuit() {
        io.println("___________________\nQuitting...");
    }

	public void displaySaveProgress() {
        io.println("Saving...");
	}

	public void displaySuccess() {
        io.println("Success!");
    }

	public void displayExportAllDataErrorMSG() {
        io.println("There is nothing to export!");
	}

	public void displayLoadProgress() {
        io.println("Loading...");
	}
}

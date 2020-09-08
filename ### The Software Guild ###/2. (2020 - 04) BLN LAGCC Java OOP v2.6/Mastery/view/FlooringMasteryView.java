package view;

import java.util.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.*;
import java.time.format.DateTimeFormatter;
import dto.*;
import service.BigDecimalMath;
import service.InvalidInputException;
import service.MathOperator;
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

    public void displayAddEditOrderTitle() {
        io.println("---------- " + displayAddEditOrderTitle + " ORDER(S) ----------");
    }

    public Order displayAddEditOrder(List<Product> listProducts) {
        Order newOrder = new Order();
        if (i == 0) {
            LocalDate userInputDate = displayInputDate();
            newOrder.setOrderDate(userInputDate);
            i = 1;
        }
        if (i == 1) {
            String customer = decodeCustomer();
            String customerState = decodeState();
            Product userInputProduct = displayProducts(listProducts);
            BigDecimal userInputArea = decodeArea();

            Product newProduct = new Product();
            newProduct.setProductType(userInputProduct.getProductType());
            newProduct.setCostPerSquareFoot(userInputProduct.getCostPerSquareFoot());

            Tax newTax = new Tax();
            newTax.setState(customerState);
            System.out.println(customerState);
            System.out.println(customer);
            System.out.println(userInputArea);
            System.out.println(newProduct);

            newOrder.setCustomerName(customer);
            newOrder.setArea(userInputArea);
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
    }

    public void displayRemoveOrder() {
        io.println("---------- REMOVE AN ORDER ----------");
        displayInputDate();
        displayOrderNumber();
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
    


    //################| LOGIC |################\\
    // ORDER # \\
    public int displayOrderNumber() {
        int orderNumber = io.readInt("\nWhat is your order number? ");
        return orderNumber;
    }

    // DATE \\
    public LocalDate displayInputDate() {
        // INIT \\
        int valid = 0;
        LocalDate orderOfDate = LocalDate.now();
        // LOGIC \\
        while (valid != 1) {
            try {
                String date = io.readString("\nProvide a future date of your order (yyyy-MMM-dd): ");
                orderOfDate = decode.dateFormat(date);

                if (orderOfDate.isBefore(LocalDate.now())) {
                    io.println("Today is " + LocalDate.now() + ". You cannot reverse time.");
                    valid = 0;
                } else if (orderOfDate.isAfter(LocalDate.now())) {
                    valid = 1;
                }
            } catch (Exception e) {
                io.println("xxxxxx Invalid date format! xxxxxx ");
            }
        }
        return orderOfDate;
    }

    // CUSTOMER \\
    public String decodeCustomer() {
        String customerName = io.readString("\nCustomer Name" + EDIT + ": ");
        customerName = decode.nameFormat(customerName, 0);
        return customerName;
    }

    // STATE \\
    public String decodeState() {
        // JUST INIT \\
        StateTaxes state = new StateTaxes();
        String stateName = "";
        boolean validationState = false;
        // STATE VALIDATION \\
        // while(validationState != true) {
        try {
            stateName = io.readString("\nState" + EDIT + ": ");
            stateName = decode.stateFormat(stateName);
            States validateState = state.stringToState(stateName);
            // System.out.println(validateState);
        } catch (Exception e) {
            io.println("Please put in a valid state.");
            // validationState = false;
        }
        // }
        return stateName;
    }

    // PICK PRODUCT \\
    public Product displayProducts(List<Product> listProducts) {
        io.println("\n#################################");
        io.println("Products | $/ft^2 | Labor$/ft^2" + "\n=================================");
        listProducts.stream().forEach((p) -> {
            io.print(p.getProductType() + " ||| ");
            io.print("$" + p.getCostPerSquareFoot() + " ||| ");
            io.println("$" + p.getLaborCostPerSquareFoot() + " ||| ");
        });
        io.println("#################################\n");

        // PRODUCT TYPE \\
        BigDecimalMath math = new BigDecimalMath();
        BigDecimal costPerSquareFoot = new BigDecimal(0);
        String userInputProductType = "";

        // PRODUCT: MATCH IN THE SYSTEM \\
        for (Product currentProduct : listProducts) {
            while (!userInputProductType.equals(currentProduct.getProductType())) {
                userInputProductType = io.readString("Please choose a valid product type" + EDIT + ": ");
                userInputProductType = decode.nameFormat(userInputProductType, 0);
                if (userInputProductType.equals(currentProduct.getProductType())) {
                    costPerSquareFoot = math.calculate(MathOperator.MULTIPLY, decodeArea(),
                            currentProduct.getCostPerSquareFoot());
                    return currentProduct;
                }
            }
        }
        return null;
    }

    // AREA \\
    public BigDecimal decodeArea() {
        MathContext mc = new MathContext(4);
        double areaSize = 0;
        // AREA \\
        while (areaSize < 100) {
            areaSize = io.readDouble("\nArea ft^2" + EDIT + ": ");
            if (areaSize < 100) {
                io.println("The minimum size is 100(sq ft.)\nPlease choose an area size that is greater.");
            } else if (areaSize > 100) {
                io.println("\n______________\nData collected.");
            }
        }
        BigDecimal area = new BigDecimal(areaSize).round(mc);
        return area;
    }
}

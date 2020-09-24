package view;

import java.util.*;
import java.math.*;
import java.time.*;
import dto.*;
import service.*;

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

    public void displayInvalidSelection() {
        io.println("Please type a number in the given range to select an option.");
    }

    public int displaySelection() throws Exception {
        int userSelect = 0;
        try {
            userSelect = io.readInt("Select by number: ");
            
        } catch (Exception e) {
            String warning = io.readString("XXX| WARNING |XXX");
        }
        return userSelect;
    }

    public LocalDate displayDisplayOrdersTitle() {
        io.println("---------- DISPLAY ORDERS ----------");
        LocalDate userInputOrderDate = displayInputDate();
        return userInputOrderDate;
    }   

    public void displayDisplayOrders(List<Order> listOrderDetails) {
        if (listOrderDetails.isEmpty() == true) {
            io.println("There are no orders.");
        } else {
            io.println("________________________________________________________________________________________________________________________________________");
            listOrderDetails.stream().forEach((p) -> {
                io.print("Order #: " + p.getOrderNumber() + " | ");
                io.print("Customer Name: " + p.getCustomerName() + " | ");
                io.print("Area: " + p.getArea() + " | ");
                io.print("Material Cost: " + p.getMaterialCost() + " | ");
                io.print("Labor Cost: " + p.getLaborCost() + " | ");
                io.print("Order Tax: " + p.getOrderTax() + " | ");
                io.println("Total Cost: " + p.getTotalCost() + " | ");
            });
            io.println("________________________________________________________________________________________________________________________________________");
        }
    }

    public void displayAddEditOrderTitle() {
        io.println("---------- " + displayAddEditOrderTitle + " ORDER(S) ----------");
    }

    public Order displayAddEditOrder(List<Product> listProducts, List<Tax> listTaxes) {
        Order newOrder = new Order();
        if (i == 0) {
            LocalDate userInputDate = displayInputDate();
            newOrder.setOrderDate(userInputDate);
            i = 1;
        }
        if (i == 1) {
            String customer = decodeCustomer();
            String customerState = decodeState(listTaxes);
            Product userInputProduct = displayProducts(listProducts);
            BigDecimal userInputArea = decodeArea();

            Product newProduct = new Product();
            newProduct.setProductType(userInputProduct.getProductType());
            newProduct.setCostPerSquareFoot(userInputProduct.getCostPerSquareFoot());

            Tax newTax = new Tax();
            newTax.setState(customerState);

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

    public Order displayRemoveOrder() {
        io.println("---------- REMOVE AN ORDER ----------");
        //LocalDate userInputOrderDate = displayInputDate();
        int userInputOrderNumber = displayOrderNumber();
        io.println("\n");
        Order findOrder = new Order();
        //findOrder.setOrderDate(userInputOrderDate);
        findOrder.setOrderNumber(userInputOrderNumber);
        return findOrder;
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
    


    //################| LOGIC |################\\
    // ORDER # \\
    public int displayOrderNumber() {
        int orderNumber = 0;
        boolean valid = false;
        while(valid != true) {
            try {
                orderNumber = io.readInt("\nWhat is your order number? ");
                valid = true;
            } catch (Exception e) {
                String warning = io.readString("Please provide a valid number.");
                valid = false;
            }
        }
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
    public String decodeState(List<Tax> listTaxes) {
        // JUST INIT \\
        StateTaxes state = new StateTaxes();
        String stateName = "";
        boolean validationState = false;

        io.println("\n#################################");
        io.println("State | TaxRate" + "\n=================================");
        listTaxes.stream().forEach((t) -> {
            io.print(t.getState() + " ||| ");
            // io.print("$" + t.getCostPerSquareFoot() + " ||| ");
            io.println(t.getTaxRate() + " ||| ");
        });
        io.println("#################################");

        // STATE VALIDATION \\
        while (validationState != true) {
            stateName = io.readString("State" + EDIT + ": ");
            for (Tax currentTax : listTaxes) {
                if (stateName.toUpperCase().equals(currentTax.getState())) {
                    validationState = true;
                } 
            }
            try {
                stateName = decode.stateFormat(stateName);
                States validateState = state.stringToState(stateName);
                // System.out.println(validateState);
            } catch (Exception e) {
                io.println("Please put in a valid state.");
                validationState = false;
            }
        }
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
        io.println("#################################");

        // PRODUCT TYPE \\
        BigDecimalMath math = new BigDecimalMath();
        BigDecimal costPerSquareFoot = new BigDecimal(0);
        String userInputProductType = "";
        boolean validateProduct = false;

        // PRODUCT: MATCH IN THE SYSTEM \\
        while (validateProduct != true) {
            userInputProductType = io.readString("Please choose a valid product type" + EDIT + ": ");
            userInputProductType = decode.nameFormat(userInputProductType, 0);
            for (Product currentProduct : listProducts) {
                if (userInputProductType.equals(currentProduct.getProductType())) {
                    costPerSquareFoot = math.calculate(MathOperator.MULTIPLY, decodeArea(),
                            currentProduct.getCostPerSquareFoot());
                    validateProduct = true;
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

	public void displayPleaseAddOrderFirst() {
        io.println("Could not find an order to edit. Please add an order before you edit.");
	}

	public void displayUnavailableState() {
        io.println("You're not qualified to make a purchase in your state.");
	}
}

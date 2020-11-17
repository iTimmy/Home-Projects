package com.sg.flooringmastery.view;

import java.util.*;
import java.math.*;
import java.time.*;
import com.sg.flooringmastery.dto.*;
import com.sg.flooringmastery.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlooringMasteryView {
    UserIO io;
    UserIODecodeImpl decode;

    @Autowired
    public FlooringMasteryView(UserIO io, UserIODecodeImpl decode) {
         this.io = io;
         this.decode = decode;
     } 

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

    public void displayDisplayOrdersTitle() {
        io.println("---------- DISPLAY ORDERS ----------");
    }   

    public void displayDisplayOrdersBanner(LocalDate date) {
        io.println("################################################################| " + date
        + " |##############################################################");
    }

    public void displayDisplayOrders(List<Order> listOrderDetails) {
        if (listOrderDetails.isEmpty() == true) {
            io.println("There are no orders.");
        } else {     
            io.println("____________________________________________________________________________________________________________________________________________");
            listOrderDetails.stream().forEach((p) -> {
                io.print("Order #: " + p.getOrderNumber() + " | ");
                io.print("Customer Name: " + p.getCustomerName() + " | ");
                io.print("Area: " + p.getArea() + " | ");
                io.print("Material Cost: " + p.getMaterialCost() + " | ");
                io.print("Labor Cost: " + p.getLaborCost() + " | ");
                io.print("Order Tax: " + p.getOrderTax() + " | ");
                io.println("Total Cost: " + p.getTotalCost() + " | ");
            });
            io.println("____________________________________________________________________________________________________________________________________________");
        }
    }

    public Order displayAddOrder(List<Product> listProducts, List<Tax> listTaxes) {
        io.println("---------- ADD ORDER(S) ----------");

        EDIT = "";
        
        Order newOrder = new Order();

        LocalDate userInputDate = displayInputDate();
        newOrder.setOrderDate(userInputDate);

        String customer = decodeCustomer();
        
        Tax newTax = decodeState(listTaxes);
        
        Product userInputProduct = displayProducts(listProducts);
        BigDecimal userInputArea = decodeArea();

        Product newProduct = new Product();
        newProduct.setProductType(userInputProduct.getProductType());
        newProduct.setCostPerSquareFoot(userInputProduct.getCostPerSquareFoot());
        newProduct.setLaborCostPerSquareFoot(userInputProduct.getLaborCostPerSquareFoot());

        newOrder.setArea(userInputArea);
        newOrder.setCustomerName(customer);
        newOrder.setProduct(newProduct);
        newOrder.setTax(newTax);

        io.println("\n______________\nData collected.");
        return newOrder;
    }

    public Order displayRemoveOrder() {
        io.println("---------- REMOVE AN ORDER ----------");
        //LocalDate userInputOrderDate = displayInputDate();
        int userInputOrderNumber = displayOrderNumber();
        Order findOrder = new Order();
        //findOrder.setOrderDate(userInputOrderDate);
        findOrder.setOrderNumber(userInputOrderNumber);
        return findOrder;
    }

    public void displayEditOrderTitle() {
        io.println("---------- EDIT ORDER(S) ----------");
    }

    public Order displayEditOrder(List<Product> listProducts, List<Tax> listTaxes, List<Order> listOrders, LocalDate date) {  
        EDIT = " (EDIT)";
        boolean valid = false;
        
        Order editOrder = new Order();
        editOrder.setOrderDate(date);

        io.println("\n");
        displayDisplayOrdersBanner(date);
        displayDisplayOrders(listOrders);

        int userInputOrderNumber = 0;
        while(valid != true) {
            userInputOrderNumber = displayOrderNumber();
            for (Order order : listOrders) {
                if (order.getOrderNumber() == userInputOrderNumber) {
                    valid = true;
                    break;
                }
            }
            if (valid == false) {
                io.println("The order number does not exist.");
            }
        }
        editOrder.setOrderNumber(userInputOrderNumber);
        String customer = decodeCustomer();
        Tax editTax = decodeState(listTaxes);
        Product userInputProduct = displayProducts(listProducts);
        BigDecimal userInputArea = decodeArea();

        Product editProduct = new Product();
        editProduct.setProductType(userInputProduct.getProductType());
        editProduct.setCostPerSquareFoot(userInputProduct.getCostPerSquareFoot());
        editProduct.setLaborCostPerSquareFoot(userInputProduct.getLaborCostPerSquareFoot());


        editOrder.setArea(userInputArea);
        editOrder.setCustomerName(customer);
        editOrder.setProduct(editProduct);
        editOrder.setTax(editTax);

        io.println("\n______________\nData recollected.");
        return editOrder;
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

    public LocalDate displayExistingInputDate() {
        // INIT \\
        int valid = 0;
        LocalDate orderOfDate = LocalDate.now();
        // LOGIC \\
        while (valid != 1) {
            try {
                String date = io.readString("\nProvide the date of your order (yyyy-MMM-dd): ");
                orderOfDate = decode.dateFormat(date);
                valid = 1;
            } catch (Exception e) {
                io.println("xxxxxx Invalid date format! xxxxxx ");
                valid = 0;
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
    public Tax decodeState(List<Tax> listTaxes) {
        // JUST INIT \\
        StateTaxes state = new StateTaxes();
        String stateName = "";
        BigDecimal stateTax = new BigDecimal(0);
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
            stateName = decode.stateFormat(stateName);
            States validateState = state.stringToState(stateName);
            String fetchedStateName = "";
            //BigDecimal fetchedStateTax = new BigDecimal(0);
            try {
                for (Tax currentTax : listTaxes) {
                    if (stateName.toUpperCase().equals(currentTax.getState())) {
                        validationState = true;
                        fetchedStateName = currentTax.getState();
                        stateTax = currentTax.getTaxRate();
                        break;
                    } else if (!stateName.toUpperCase().equals(currentTax.getState())) {
                        validationState = false;
                    } 
                }
                if (stateName.toUpperCase().length() != 2) {
                    io.println("Please abbreviate your state.");
                } else if (!stateName.toUpperCase().equals(fetchedStateName)
                        && stateName.toUpperCase().equals(validateState.toString())) {
                    io.println("Your state is not eligible for purchase.");
                } else if (!stateName.toUpperCase().equals(validateState.toString())) {
                    io.println("Please put in a valid state.");
                }
            } catch (Exception e) {
                io.println("Please put in a valid state.");
            }

        }
        
        Tax newState = new Tax();
        newState.setState(stateName);
        newState.setTaxRate(stateTax);
        System.out.println("stateTax: " + stateTax);
        
        return newState;
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
        String userInputProductType = "";
        boolean validateProduct = false;

        // PRODUCT: MATCH IN THE SYSTEM \\
        while (validateProduct != true) {
            userInputProductType = io.readString("Please choose a valid product type" + EDIT + ": ");
            userInputProductType = decode.nameFormat(userInputProductType, 0);
            for (Product currentProduct : listProducts) {
                if (userInputProductType.equals(currentProduct.getProductType())) {
                    validateProduct = true;
                    return currentProduct;
                }
            }
        }
        return null;
    }

    // AREA \\
    public BigDecimal decodeArea() {
        boolean isValid = false;
        MathContext mc = new MathContext(4);
        double areaSize = 0;
        // AREA \\
        while(isValid != true) {
            try {
                areaSize = io.readDouble("\nArea ft^2" + EDIT + ": ");
                if (areaSize < 100) {
                    io.println("The minimum size is 100(sq ft.)\nPlease choose an area size that is greater.");
                    isValid = false;
                } else if (areaSize > 100) {
                    isValid = true;
                }
            } catch (Exception e) {
                String warning = io.readString("Please put in a valid number.");
            }
        }

        BigDecimal area = new BigDecimal(areaSize).round(mc);
        return area;
    }

	public void displayUnavailableState() {
        io.println("You're not qualified to make a purchase in your state.");
	}

	public void displayDoesNotExist() {
        io.println("Data does not exist.");
    }
    
    public String confirmSave() {
        String confirmSave = "";
        boolean valid = false;
        while(valid != true) {
            confirmSave = io.readString("Comfirm y/n to save data: ");
            if (confirmSave.toUpperCase().equals("Y")) {
                io.println("You chose to save your data.");
                valid = true;
            } else if (confirmSave.toUpperCase().equals("N")) {
                io.println("You chose not to save your data.");
                return null;
            } else {
                io.println("Please pick between y/n.");
            }
        }
        return confirmSave;
    }

    public void returnCalculations(Order order) {
        MathContext mc = new MathContext(3);
        // CALCULATIONS \\
        BigDecimal taxPercentage = new BigDecimal(100);
        BigDecimal materialCost = roundBigDecimal(order.getArea().multiply(order.getProduct().getCostPerSquareFoot()));
        BigDecimal laborCost = roundBigDecimal(order.getArea().multiply(order.getProduct().getLaborCostPerSquareFoot()));
        BigDecimal tax = (materialCost.add(laborCost)).multiply(order.getTax().getTaxRate().divide(taxPercentage, 4, RoundingMode.HALF_UP)).setScale(2, RoundingMode.FLOOR);
        BigDecimal totalCost = roundBigDecimal((materialCost.add(laborCost)).add(tax));
        
        io.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        io.println("Customer Name: " + order.getCustomerName());
        io.println("State: " + order.getTax().getState());
        io.println("Product: " + order.getProduct().getProductType());
        io.println("Area: " + order.getArea());
        io.println("Material Cost: $" + materialCost);
        io.println("Labor Cost: $" + laborCost);
        io.println("Tax: $" + tax);
        io.println("Total Cost: $" + totalCost);
        io.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    
    private BigDecimal roundBigDecimal(BigDecimal decimal) {
        BigDecimal roundedBigDecimal = decimal.setScale(2, RoundingMode.CEILING);
        return roundedBigDecimal;
    }

//	public String displayFileStatus(boolean b) {
//            if (b == true) {
//                io.println("The file exists. This will be overwritten.");
//                return "The file exists. This will be overwritten.";
//            } else {
//                io.println("The file does not exist. This will be created as new.");
//                return "The file does not exist. This will be created as new.";
//            }
//	}

    public boolean displayConfirmation(String word) {
        String input = io.readString("Confirm if you wish to " + word + " this order. (y/n) ");
        if (input.toUpperCase().equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

}

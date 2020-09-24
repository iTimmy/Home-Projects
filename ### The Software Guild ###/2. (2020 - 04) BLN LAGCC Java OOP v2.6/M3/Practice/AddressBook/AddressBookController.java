import java.util.*;

public class AddressBookController {

    AddressBookDao dao = new AddressBookDao();

    public void menuDisplay() {
        Scanner input = new Scanner(System.in);

        System.out.println("--------------\n" +
        "Add an address\n" +
        "View an address\n" +
        "Delete an address\n" +
        "Edit an address\n" +
        "List all addresses\n" +
        "Show the number of addresses in the address book\n" +
        "Save to file\n" +
        "Read from file\n" +
        "--------------");

        String userInput = input.nextLine();

            if (userInput.equals("add")) {
                addMenu();
            } else if (userInput.equals("view")) {
                viewMenu();
            } else if (userInput.equals("delete")) {
                addMenu();
            } else if (userInput.equals("edit")) {
                addMenu();
            } else if (userInput.equals("list")) {
                addMenu();
            } else if (userInput.equals("num")) {
                addMenu();
            } else if (userInput.equals("save")) {
                addMenu();
            } else if (userInput.equals("read")) {
                addMenu();
            }
    }

    public void addMenu() {
        System.out.println("New Address: ");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        address(input);
    }

    public void viewMenu() {
        dao.getAddress();
    }

    public void deleteMenu() {
        
    }

    public void editMenu() {
        
    }

    public void numMenu() {
        
    }

    public void saveMenu() {
        
    }

    public void readMenu() {
        
    }

    public void address(String name) {
        System.out.println("Your new address is: " + name);
        dao.setAddress(name);
    }
}
package dto;

public class VendingMachine {
    private String itemName;
    private double itemCost;
    private int itemQuantity;

    public VendingMachine(String itemName, double itemCost) {
        this.itemName = itemName;
        this.itemCost = itemCost;
        // this.ItemQuantity = ItemQuantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

}
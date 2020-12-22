package dto;

public class UserWallet {
    double money;

    public UserWallet(double money) {
        this.money = money;
    }

    public UserWallet() {
        
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    
}
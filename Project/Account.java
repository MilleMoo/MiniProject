package Bank;

public class Account{
    private int id;
    private double balance;
    private double annualInterestRate;

    public int getid() {
        return id;
    }

    public double getbalance() {
        return balance;
    }

    public double getannulInterestRate() {
        return annualInterestRate;
    }

    public void setid(int ID) {
        this.id = ID;
    }
    
    public void setbalance(int ID) {
        this.balance = ID;
    }
    
    public void setannulInterestRate(int ID) {
        this.annualInterestRate = ID;
    }

    public Account(int id, double balance, double annualInterestRate) {
        this.id = id;
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
    }

    public boolean withdraw(double draw) {
        if (this.balance >= draw) {
            this.balance -= draw;
            System.out.println("Withdraw " + draw + " form account ID : " + this.id);
            return true;
        } else {
            return false;
        }
    }

    public void deposit(double Dp) {
        System.out.println("Depsit " + Dp + " to account : " + this.id);
        this.balance += Dp;
    }

}
package bank.transactions;

public class Transaction {

    private String type;
    private double amount;
    private double balanceAfter;

    public Transaction(String type, double amount, double balanceAfter) {
        this.type         = type;
        this.amount       = amount;
        this.balanceAfter = balanceAfter;
    }

    @Override
    public String toString() {
        return String.format("  %-12s $%8.2f   (balance after: $%.2f)", type, amount, balanceAfter);
    }
}
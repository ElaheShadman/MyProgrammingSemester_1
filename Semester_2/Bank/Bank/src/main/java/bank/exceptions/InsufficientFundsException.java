package bank.exceptions;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(double balance, double amount) {
        super("Not enough funds! Balance: $" + String.format("%.2f", balance)
                + ", tried to withdraw: $" + String.format("%.2f", amount));
    }
}
package bank.exceptions;

public class InvalidAmountException extends Exception {
    public InvalidAmountException(double amount) {
        super("Invalid amount: $" + String.format("%.2f", amount) + ". Must be greater than zero.");
    }
}
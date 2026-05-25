package bank.accounts;

import bank.exceptions.InvalidAmountException;
import bank.exceptions.InsufficientFundsException;

public class CheckingAccount extends Account {

    private static final double TRANSACTION_FEE = 0.50;

    public CheckingAccount(String ownerName, double initialDeposit) throws InvalidAmountException {
        super(ownerName, initialDeposit);
    }

    @Override
    public String getAccountType() { return "Checking"; }

    @Override
    public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
        double total = amount + TRANSACTION_FEE;
        if (total > getBalance()) throw new InsufficientFundsException(getBalance(), total);
        super.withdraw(amount);
        super.withdraw(TRANSACTION_FEE);
        System.out.println("  (Includes $0.50 transaction fee)");
    }

    @Override
    public void printSummary() {
        super.printSummary();
        System.out.println("  ⚠️  $0.50 fee applies per withdrawal");
    }
}
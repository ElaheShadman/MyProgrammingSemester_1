package bank.accounts;

import bank.exceptions.InsufficientFundsException;
import bank.exceptions.InvalidAmountException;
import bank.transactions.Transaction;

import java.util.ArrayList;
import java.util.List;

public abstract class Account implements Printable {

    private String id;
    private String ownerName;
    private double balance;
    private List<Transaction> history;

    private static int nextId = 1000;

    public Account(String ownerName, double initialDeposit) throws InvalidAmountException {
        if (initialDeposit < 0) {
            throw new InvalidAmountException(initialDeposit);
        }
        this.id        = "ACC" + (nextId++);
        this.ownerName = ownerName;
        this.balance   = initialDeposit;
        this.history   = new ArrayList<>();
        history.add(new Transaction("OPEN", initialDeposit, balance));
    }

    public abstract String getAccountType();

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException(amount);
        balance += amount;
        history.add(new Transaction("DEPOSIT", amount, balance));
        System.out.println("✅ Deposited $" + String.format("%.2f", amount)
                + " → new balance: $" + String.format("%.2f", balance));
    }

    public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) throw new InvalidAmountException(amount);
        if (amount > balance) throw new InsufficientFundsException(balance, amount);
        balance -= amount;
        history.add(new Transaction("WITHDRAW", amount, balance));
        System.out.println("✅ Withdrew $" + String.format("%.2f", amount)
                + " → new balance: $" + String.format("%.2f", balance));
    }

    @Override
    public void printSummary() {
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("  " + getAccountType() + " Account");
        System.out.println("  ID:      " + id);
        System.out.println("  Owner:   " + ownerName);
        System.out.println("  Balance: $" + String.format("%.2f", balance));
        System.out.println("└─────────────────────────────────────┘");
    }

    public void printHistory() {
        System.out.println("📜 Transaction history for " + id + " (" + ownerName + "):");
        System.out.println("  Type          Amount      Balance after");
        System.out.println("  ──────────────────────────────────────");
        for (Transaction t : history) {
            System.out.println(t);
        }
    }

    public String getId()        { return id; }
    public String getOwnerName() { return ownerName; }
    public double getBalance()   { return balance; }

    protected void addInterestTransaction(double amount) {
        balance += amount;
        history.add(new Transaction("INTEREST", amount, balance));
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s | $%.2f",
                getAccountType(), id, ownerName, balance);
    }
}
package bank.accounts;

import bank.exceptions.InvalidAmountException;

public class SavingsAccount extends Account implements InterestBearing {

    private static final double INTEREST_RATE = 0.03;

    public SavingsAccount(String ownerName, double initialDeposit) throws InvalidAmountException {
        super(ownerName, initialDeposit);
    }

    @Override
    public String getAccountType() { return "Savings"; }

    @Override
    public double getInterestRate() { return INTEREST_RATE; }

    @Override
    public void applyMonthlyInterest() {
        double interest = getBalance() * INTEREST_RATE;
        addInterestTransaction(interest);
        System.out.println("💰 Interest applied: $" + String.format("%.2f", interest)
                + " (3%) → new balance: $" + String.format("%.2f", getBalance()));
    }
}
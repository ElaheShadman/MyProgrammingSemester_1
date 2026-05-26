package bank.accounts;

import bank.exceptions.InvalidAmountException;

// INHERITANCE: SavingsAccount is-a Account
// INTERFACES: also implements InterestBearing (earns interest!)
public class SavingsAccount extends Account implements InterestBearing {

    private static final double INTEREST_RATE = 0.03; // 3% monthly

    public SavingsAccount(String ownerName, double initialDeposit) throws InvalidAmountException {
        super(ownerName, initialDeposit); // INHERITANCE: calling parent constructor
    }

    // INHERITANCE: implementing abstract method
    @Override
    public String getAccountType() { return "Savings"; }

    // INTERFACES: implementing InterestBearing
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

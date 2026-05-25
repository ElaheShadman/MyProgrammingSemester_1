package bank.accounts;

import bank.exceptions.InvalidAmountException;

public class BusinessAccount extends Account implements InterestBearing {

    private static final double INTEREST_RATE = 0.05;
    private String businessName;

    public BusinessAccount(String ownerName, double initialDeposit, String businessName)
            throws InvalidAmountException {
        super(ownerName, initialDeposit);
        this.businessName = businessName;
    }

    @Override
    public String getAccountType() { return "Business"; }

    @Override
    public double getInterestRate() { return INTEREST_RATE; }

    @Override
    public void applyMonthlyInterest() {
        double interest = getBalance() * INTEREST_RATE;
        addInterestTransaction(interest);
        System.out.println("💰 Interest applied: $" + String.format("%.2f", interest)
                + " (5%) → new balance: $" + String.format("%.2f", getBalance()));
    }

    @Override
    public void printSummary() {
        super.printSummary();
        System.out.println("  🏢 Business: " + businessName);
        System.out.println("  📈 5% monthly interest rate");
    }
}